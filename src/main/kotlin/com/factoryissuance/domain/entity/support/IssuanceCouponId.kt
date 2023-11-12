package com.factoryissuance.domain.entity.support

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class IssuanceCouponId(

    @Column(name = "ISSUANCE_COUPON_ID", nullable = false)
    var issuanceCouponId: String,

    @Column(name = "ISSUANCE_REQUEST_ID", nullable = false)
    var issuanceRequestId: String,
) : Serializable
