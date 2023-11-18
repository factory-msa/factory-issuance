package com.factoryissuance.domain.repository

import org.springframework.stereotype.Component

@Component
class SequenceIdGenerator : IdGenerator {

    override fun next(): String {
        // TODO: MySQL AUTO_INCREMENT 쿼리로 변경
        return "I000000001"
    }

}
