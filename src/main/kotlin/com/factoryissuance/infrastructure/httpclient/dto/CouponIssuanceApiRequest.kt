package com.factoryissuance.infrastructure.httpclient.dto

import java.time.LocalDateTime

/**
 * 쿠폰 서비스 발급 요청 Request
 */
data class CouponIssuanceApiRequest(
    val issuanceId: String,
    val productId: String,
    val issuanceQuantity: Int,
    val price: Long,
    val expiredFrom: LocalDateTime,
    val expiredTo: LocalDateTime,
)
