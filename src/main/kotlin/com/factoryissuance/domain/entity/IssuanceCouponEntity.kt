package com.factoryissuance.domain.entity

import support.domain.BaseTimeEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ISSUANCE_COUPON")
class IssuanceCouponEntity(

    @Column(name = "ISSUANCE_REQUEST_ID", nullable = false)
    var issuanceRequestId: String, // globalTransactionId,

    @Column(name = "COUPON_NUMBER", nullable = false)
    var couponNumber: String,

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) : BaseTimeEntity()
