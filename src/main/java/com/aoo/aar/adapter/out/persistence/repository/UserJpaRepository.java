package com.aoo.aar.adapter.out.persistence.repository;

import com.aoo.common.adapter.out.persistence.entity.UserJpaEntity;
import com.aoo.common.adapter.out.persistence.repository.UserQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long>, UserQueryDslRepository {
    Optional<UserJpaEntity> findByPhoneNumber(String phoneNumber);
}
