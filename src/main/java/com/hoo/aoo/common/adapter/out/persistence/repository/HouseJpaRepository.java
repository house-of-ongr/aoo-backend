package com.hoo.aoo.common.adapter.out.persistence.repository;

import com.hoo.aoo.common.adapter.out.persistence.entity.HouseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseJpaRepository extends JpaRepository<HouseJpaEntity, Long>, HouseQueryDslRepository {
}
