package com.factoryissuance

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class FactoryIssuanceApplication

fun main(args: Array<String>) {
    runApplication<FactoryIssuanceApplication>(*args)
}
