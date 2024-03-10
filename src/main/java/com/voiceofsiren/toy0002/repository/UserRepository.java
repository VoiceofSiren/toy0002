package com.voiceofsiren.toy0002.repository;

import com.voiceofsiren.toy0002.domain.User;
import com.voiceofsiren.toy0002.dto.UserDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }


    @Transactional(readOnly = true)
    public List<User> findAllEntities() {
        return em.createQuery("" +
                "select u from User u " +
                    "join u.boards b " +
                    "join u.userRoles ur ", User.class)
                .getResultList();
    }
}
