package com.factoryissuance.infrastructure.httpclient

import com.factoryissuance.api.dto.ApiResponse
import com.factoryissuance.infrastructure.httpclient.dto.ProductSearchResponse

interface ProductService {

    fun findProductById(id: String): ApiResponse<ProductSearchResponse>

}
