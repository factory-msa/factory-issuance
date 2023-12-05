package com.factoryissuance.infrastructure.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import support.header.FactoryHeaderInterceptor

@Configuration
class WebMvcConfig: WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(FactoryHeaderInterceptor())
            .addPathPatterns("/**")
    }
}
