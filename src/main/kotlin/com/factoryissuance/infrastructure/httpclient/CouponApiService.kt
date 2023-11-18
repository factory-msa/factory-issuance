package com.factoryissuance.infrastructure.httpclient

import com.factoryissuance.api.dto.ApiResponse
import com.factoryissuance.infrastructure.httpclient.dto.CouponIssuanceApiRequest
import com.factoryissuance.infrastructure.httpclient.dto.CouponIssuanceApiResponse
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import support.logging.logger

@Component
class CouponApiService(private val restTemplate: RestTemplate) : CouponService {

    private val logger = logger()

    override fun issueCoupons(request: CouponIssuanceApiRequest): ApiResponse<CouponIssuanceApiResponse> {
        val uri = "http://localhost:10001/api/v1/issue-coupons"

        return try {
            restTemplate.exchange(uri, HttpMethod.POST, HttpEntity(request), TYPE_REF).body!!
        } catch (ex: RuntimeException) {
            logger.error("쿠폰 API 호출에 실패하였습니다. 호출 URL: $uri, 실패사유: ${ex.message}")

            // TODO: 예외 타입 변경
            throw IllegalStateException(ex.message)
        }
    }

    companion object {
        private val TYPE_REF =
            object : ParameterizedTypeReference<ApiResponse<CouponIssuanceApiResponse>>() {}
    }

}
