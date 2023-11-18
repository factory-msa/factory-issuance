package com.factoryissuance.infrastructure.httpclient.dto

/**
 * 쿠폰 발급 요청 결과 Response Dto
 */
data class CouponIssuanceApiResponse(
    val couponNumbers: List<String>
)
