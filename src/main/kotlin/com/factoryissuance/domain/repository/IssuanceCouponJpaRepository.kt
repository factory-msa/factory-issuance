package com.factoryissuance.domain.repository

import com.factoryissuance.domain.entity.IssuanceCouponEntity
import org.springframework.data.jpa.repository.JpaRepository

interface IssuanceCouponJpaRepository : JpaRepository<IssuanceCouponEntity, Long>
