package com.factoryissuance.api.v1

import com.factoryissuance.api.dto.ApiResponse
import com.factoryissuance.application.IssuanceFacadeService
import com.factoryissuance.application.IssuanceService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class IssuanceController(
    private val service: IssuanceFacadeService
) {

    @PostMapping("/api/v1/issuance/coupons/issue")
    fun issueCoupons(
        @RequestBody command: IssuanceService.CouponIssuanceCommand
    ): ApiResponse<IssuanceService.CouponIssuanceResponse> {

        return ApiResponse.ok(
            service.issueCoupons(command)
        )
    }

}
