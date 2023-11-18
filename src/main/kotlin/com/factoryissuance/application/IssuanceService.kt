package com.factoryissuance.application

import com.factoryissuance.domain.exception.BusinessException
import com.factoryissuance.domain.repository.IdGenerator
import com.factoryissuance.infrastructure.httpclient.CouponService
import com.factoryissuance.infrastructure.httpclient.ProductService
import com.factoryissuance.infrastructure.httpclient.dto.CouponIssuanceApiRequest
import com.factoryissuance.infrastructure.httpclient.exception.ProductNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import support.logging.logger
import support.time.DateUtils.Companion.toLocalDateTime

@Service
class IssuanceService(
    private val issuanceRequestService: IssuanceRequestService,
    private val productService: ProductService,
    private val couponService: CouponService,
    private val idGenerator: IdGenerator
) {

    @Transactional
    fun issueCoupons(command: CouponIssuanceCommand): CouponIssuanceResponse {
        validateTransactionId(command.transactionId)

        issuanceRequestService.save(command)

        validateProduct(command.productId)

        val issuanceId = idGenerator.next()

        return try {
            val response = couponService.issueCoupons(
                CouponIssuanceApiRequest(
                    issuanceId,
                    command.productId,
                    command.issuanceQuantity,
                    command.price,
                    toLocalDateTime(command.couponExpiredStart),
                    toLocalDateTime(command.couponExpiredEnd)
                )
            )
            CouponIssuanceResponse.success(issuanceId, response.result!!.couponNumbers)
        } catch (ex: RuntimeException) {
            logger.error(
                """
                쿠폰 발급에 실패하였습니다. 발급요청 ID: `$issuanceId`, 트랜잭션 ID: `${command.transactionId}`
                실패 사유: ${ex.message}
                """.trimIndent()
            )

            throw BusinessException(ex.message!!)
        }
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

    companion object {
        private val logger = logger()
    }

    /**
     * 쿠폰 발급 요청 Request Dto
     */
    class CouponIssuanceCommand(
        val transactionId: String,
        val productId: String,
        val issuanceQuantity: Int,
        val price: Long,
        val couponExpiredStart: String,
        val couponExpiredEnd: String
    ) {
        override fun toString(): String {
            return """
                트랜잭션 ID: {$transactionId}, 
                상품 ID: {$productId}, 
                발급 수량: {$issuanceQuantity},
                금액: {$price},
                쿠폰 유효기간: {$couponExpiredStart ~ $couponExpiredEnd}
                """.trimIndent()
        }
    }

    /**
     * 쿠폰 발급 요청 결과 Response Dto
     */
    class CouponIssuanceResponse(
        val issuanceId: String,
        val couponNumbers: List<String>
    ) {
        companion object {
            fun success(issuanceId: String, coupons: List<String>): CouponIssuanceResponse {
                return CouponIssuanceResponse(issuanceId, coupons)
            }
        }
    }

}
