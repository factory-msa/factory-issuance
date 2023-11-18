package com.factoryissuance.api

import com.factoryissuance.api.dto.ApiResponse
import com.factoryissuance.domain.exception.BusinessException
import com.factoryissuance.domain.exception.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import support.logging.logger

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = logger()

    @ExceptionHandler(BusinessException::class)
    fun apiException(e: BusinessException): ApiResponse<ErrorResponse> {
        return ApiResponse.fail("4000", e.message!!)
    }

}
