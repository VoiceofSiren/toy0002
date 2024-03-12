package com.voiceofsiren.toy0002.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.voiceofsiren.toy0002.domain.QUser;
import com.voiceofsiren.toy0002.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class CustomizedUserRepositoryImpl implements CustomizedUserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findByUsernameCustomized(String username) {
        QUser qUser = QUser.user;
        JPAQuery<?> jpaQuery = new JPAQuery<Void>(em);
        List<User> users = jpaQuery.select(qUser)
                .from(qUser)
                .where(qUser.username.contains(username))
                .fetch();
        return users;
    }
}