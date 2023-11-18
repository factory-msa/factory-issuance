package com.factoryissuance.api.v1

import com.factoryissuance.api.dto.ApiResponse
import com.factoryissuance.application.IssuanceService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1")
@RestController
class IssuanceController(
    private val service: IssuanceService
) {

    @PostMapping("coupons/issue")
    fun issueCoupons(
        @RequestBody command: IssuanceService.CouponIssuanceCommand
    ): ApiResponse<IssuanceService.CouponIssuanceResponse> {

        return ApiResponse.ok(
            service.issueCoupons(command)
        )
    }

}
