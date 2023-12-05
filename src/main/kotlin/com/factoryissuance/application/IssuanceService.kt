package com.factoryissuance.application

import com.factoryissuance.domain.entity.IssuanceCouponEntity
import com.factoryissuance.domain.exception.BusinessException
import com.factoryissuance.domain.repository.IdGenerator
import com.factoryissuance.domain.repository.IssuanceCouponJpaRepository
import com.factoryissuance.infrastructure.httpclient.CouponService
import com.factoryissuance.infrastructure.httpclient.dto.CouponIssuanceApiRequest
import org.springframework.stereotype.Service
import support.header.FactoryRequestHolder
import support.logging.logger
import support.time.DateUtils.Companion.toLocalDateTime

@Service
class IssuanceService(
    private val idGenerator: IdGenerator,
    private val couponService: CouponService,
    private val issuanceRepository: IssuanceCouponJpaRepository
) {

    fun issueCoupons(command: CouponIssuanceCommand): CouponIssuanceResponse {
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

            val couponNumbers = response.result!!.couponNumbers
            val globalTransactionId = FactoryRequestHolder.getGlobalTransactionId()

            saveIssuanceCoupons(couponNumbers, issuanceId, globalTransactionId)

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

    private fun saveIssuanceCoupons(couponNumbers: List<String>, issuanceId: String, globalTransactionId: String) {
        val issuanceCoupons = couponNumbers.map {
            IssuanceCouponEntity(
                issuanceId, globalTransactionId, it
            )
        }

        issuanceRepository.saveAll(issuanceCoupons)
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
