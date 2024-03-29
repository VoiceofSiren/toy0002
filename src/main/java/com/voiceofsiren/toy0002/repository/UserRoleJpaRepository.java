package com.voiceofsiren.toy0002.repository;

import com.voiceofsiren.toy0002.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleJpaRepository extends JpaRepository<UserRole, Long> {

}