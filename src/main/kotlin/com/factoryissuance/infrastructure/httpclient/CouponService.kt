package com.factoryissuance.infrastructure.httpclient

import com.factoryissuance.api.dto.ApiResponse
import com.factoryissuance.infrastructure.httpclient.dto.CouponIssuanceApiRequest
import com.factoryissuance.infrastructure.httpclient.dto.CouponIssuanceApiResponse

interface CouponService {

    fun issueCoupons(request: CouponIssuanceApiRequest): ApiResponse<CouponIssuanceApiResponse>
}
