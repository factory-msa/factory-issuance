package com.factoryissuance.application

import com.factoryissuance.domain.entity.IssuanceRequestHistoryEntity
import com.factoryissuance.domain.entity.support.IssuanceRequestCode
import com.factoryissuance.domain.repository.IssuanceRequestJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class IssuanceRequestService(
    private val repository: IssuanceRequestJpaRepository
) {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun save(command: IssuanceService.CouponIssuanceCommand) {
        val entity = IssuanceRequestHistoryEntity(
            command.transactionId,
            IssuanceRequestCode.ISS001,
            command.price,
            command.issuanceQuantity
        )

        repository.save(entity)
    }

    fun existsByTransactionId(transactionId: String): Boolean {
        return repository.existsByTransactionId(transactionId)
    }

}
