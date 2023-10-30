package com.factoryissuance.domain.entity

import com.factoryissuance.domain.entity.support.IssuanceCouponId
import org.springframework.data.domain.Persistable
import support.domain.BaseTimeEntity
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "ISSUANCE_COUPON")
class IssuanceCouponEntity(

    @EmbeddedId
    var issuanceCouponId: IssuanceCouponId,

    @Column(name = "COUPON_NUMBER", nullable = false)
    var couponNumber: String,
) : BaseTimeEntity(), Persistable<IssuanceCouponId> {

    @Transient
    private var isNew: Boolean = true

    override fun getId(): IssuanceCouponId? = issuanceCouponId

    override fun isNew(): Boolean = isNew

}
