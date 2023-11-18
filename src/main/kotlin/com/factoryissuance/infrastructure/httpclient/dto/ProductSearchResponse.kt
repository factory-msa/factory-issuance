package com.factoryissuance.infrastructure.httpclient.dto

data class ProductSearchResponse(
    val id: String,
    val productName: String,
    val productCode: String,
    val price: Long
)
