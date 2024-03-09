package com.voiceofsiren.toy0002.repository;

import com.voiceofsiren.toy0002.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
