package com.factoryissuance.application

import com.factoryissuance.infrastructure.httpclient.ProductService
import com.factoryissuance.infrastructure.httpclient.exception.ProductNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import support.logging.logger

@Service
class IssuanceFacadeService(
    private val issuanceRequestService: IssuanceRequestService,
    private val productService: ProductService,
    private val service: IssuanceService
) {

    private val logger = logger()

    @Transactional
    fun issueCoupons(command: IssuanceService.CouponIssuanceCommand)
            : IssuanceService.CouponIssuanceResponse {

        validateTransactionId(command.transactionId)

        issuanceRequestService.save(command)

        validateProduct(command.productId)

        return service.issueCoupons(command)
    }

    private fun validateTransactionId(transactionId: String) {
        if (alreadyExistIssuanceRequestHistory(transactionId)) {
            val message = "발급 요청 이력이 이미 존재합니다. 트랜잭션 ID: `$transactionId`"
            logger.error(message)

            throw RuntimeException(message)
        }
    }

    private fun alreadyExistIssuanceRequestHistory(transactionId: String) =
        issuanceRequestService.existsByTransactionId(transactionId)

    private fun validateProduct(productId: String) {
        val product = productService.findProductById(productId).result

        if (product == null) {
            val message = "상품 정보가 존재하지 않습니다, 상품 ID: `{$productId}`"
            logger.error(message)

            throw ProductNotFoundException(message)
        }
    }
}
