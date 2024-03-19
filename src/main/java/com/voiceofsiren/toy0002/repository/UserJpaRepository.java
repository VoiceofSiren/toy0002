package com.voiceofsiren.toy0002.repository;

import com.voiceofsiren.toy0002.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User>, CustomizedUserRepository {

    User findByUsername(String username);

    @Query("select u from User u where u.username like %?1%")
    List<User> findByUsernameQuery(String username);

    @Query(value = "select u from User u where u.username like %?1%", nativeQuery = true)
    List<User> findByUsernameNativeQuery(String username);

    boolean existsByUsername(String username);

}
