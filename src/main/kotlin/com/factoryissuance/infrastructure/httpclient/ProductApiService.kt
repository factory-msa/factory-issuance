package com.factoryissuance.infrastructure.httpclient

import com.factoryissuance.api.dto.ApiResponse
import com.factoryissuance.infrastructure.httpclient.dto.ProductSearchResponse
import com.factoryissuance.infrastructure.httpclient.exception.ProductNotFoundException
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import support.logging.logger

@Component
class ProductApiService(private val restTemplate: RestTemplate) : ProductService {

    private val logger = logger()

    override fun findProductById(id: String): ApiResponse<ProductSearchResponse> {
        val uri = "http://localhost:10002/api/v1/products/$id"

        return try {
            restTemplate.exchange(uri, HttpMethod.GET, null, TYPE_REF).body!!
        } catch (ex: ProductNotFoundException) {
            logger.error("Product not found! productId: $id")
            ApiResponse.ok(null)
        } catch (ex: RuntimeException) {
            logger.error("Product Api Failed! error: ${ex.message}")
            throw IllegalStateException(ex.message)
        }
    }

    companion object {
        private val TYPE_REF =
            object : ParameterizedTypeReference<ApiResponse<ProductSearchResponse>>() {}
    }
}
