package com.factoryissuance.domain.entity

import com.factoryissuance.domain.entity.support.IssuanceRequestCode
import support.domain.BaseTimeEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ISSUANCE_REQUEST")
class IssuanceRequestEntity(

    @Id
    @Column(name = "ISSUANCE_REQUEST_ID", nullable = false)
    var issuanceRequestId: String,

    @Column(name = "TRANSACTION_ID", nullable = false)
    var transactionId: String,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ISSUANCE_REQUEST_CODE", nullable = false)
    var issuanceRequestCode: IssuanceRequestCode,

    @Column(name = "ISSUANCE_RESULT_CODE")
    var issuanceResultCode: String,

    @Column(name = "PRICE", nullable = false)
    var price: Long,

    @Column(name = "ISSUANCE_QUANTITY", nullable = false)
    var quantity: Int
) : BaseTimeEntity() {

}
