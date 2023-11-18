package com.factoryissuance.domain.repository

import com.factoryissuance.domain.entity.IssuanceRequestHistoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface IssuanceRequestJpaRepository : JpaRepository<IssuanceRequestHistoryEntity, String> {

    fun existsByTransactionId(transactionId: String): Boolean

}
