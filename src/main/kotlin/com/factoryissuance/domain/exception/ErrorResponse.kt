package com.factoryissuance.domain.exception

data class ErrorResponse(
    val message: String? = null,
    val errors: MutableList<Error> = mutableListOf()
) {
    private constructor(message: String) : this(message, mutableListOf())

    companion object {
        fun of(message: String?) = of(message, mutableListOf())
        fun of(message: String?, errors: MutableList<Error>) = ErrorResponse(message, errors)
    }
}

data class Error(
    var field: String? = null,
    var `class`: String? = null,
    var value: Any? = null
)
