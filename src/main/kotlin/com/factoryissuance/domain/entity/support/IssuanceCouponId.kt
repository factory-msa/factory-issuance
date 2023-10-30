package com.factoryissuance.domain.entity.support

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Id

@Embeddable
class IssuanceCouponId(

    @Id
    @Column(name = "ISSUANCE_COUPON_ID", nullable = false)
    var issuanceCouponId: String,

    @Id
    @Column(name = "ISSUANCE_REQUEST_ID", nullable = false)
    var issuanceRequestId: String,
) : Serializable
