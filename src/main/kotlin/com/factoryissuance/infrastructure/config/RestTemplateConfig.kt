package com.factoryissuance.infrastructure.config

import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig {

    @Bean
    fun httpClient(): HttpClient {
        return HttpClientBuilder.create()
            .setMaxConnTotal(100)
            .setMaxConnPerRoute(100)
            .build()
    }

    @Bean
    fun factory(httpClient: HttpClient): HttpComponentsClientHttpRequestFactory {
        var factory = HttpComponentsClientHttpRequestFactory(httpClient())
        factory.setConnectTimeout(30000)

        return factory
    }

    @Bean
    fun restTemplate(factory: HttpComponentsClientHttpRequestFactory): RestTemplate {
        return RestTemplate(factory)
    }

}
