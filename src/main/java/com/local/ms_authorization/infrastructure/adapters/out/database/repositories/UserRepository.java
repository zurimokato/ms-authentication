package com.local.ms_authorization.infrastructure.adapters.out.database.repositories;

import com.local.ms_authorization.infrastructure.adapters.out.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
