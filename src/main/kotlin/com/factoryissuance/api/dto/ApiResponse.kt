package com.factoryissuance.api.dto

import org.springframework.http.HttpStatus

class ApiResponse<T>(
    var code: String,
    var message: String,
    var result: T?
) {

    companion object {
        private const val SUCCESS_CODE: String = "0000"
        private const val CLIENT_FAIL_CODE: String = "4000"

        fun <T> ok(result: T?): ApiResponse<T> {
            return ApiResponse(SUCCESS_CODE, HttpStatus.OK.reasonPhrase, result)
        }

        fun <T> fail(code: String, message: String): ApiResponse<T> {
            return ApiResponse(code, message, null)
        }

        fun <T> failedByClient(message: String): ApiResponse<T> {
            return ApiResponse(CLIENT_FAIL_CODE, message, null)
        }
    }

}
