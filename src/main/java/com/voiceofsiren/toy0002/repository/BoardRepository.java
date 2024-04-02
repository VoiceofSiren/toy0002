package com.voiceofsiren.toy0002.repository;

import com.voiceofsiren.toy0002.dto.BoardPageDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public Page<BoardPageDTO> showBoardPageList(String title, String content, Pageable pageable) {
        String queryStr = "SELECT new com.voiceofsiren.toy0002.dto.BoardPageDTO(b.id, b.title, u.username) FROM Board b "
                + "left JOIN User u on b.user.id = u.id "
                + "WHERE b.title LIKE :title OR b.content LIKE :content "
                + "ORDER BY b.id desc ";

        Query query = em.createQuery(queryStr, BoardPageDTO.class);
        query.setParameter("title", "%" + title + "%");
        query.setParameter("content", "%" + content + "%");
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<BoardPageDTO> results = query.getResultList();
        long total = countTotal(title, content); // 총 레코드 수를 구하는 메서드

        return new PageImpl<>(results, pageable, total);
    }

    private long countTotal(String title, String content) {
        String queryStr = "SELECT COUNT(*) FROM Board b "
                + "WHERE b.title LIKE :title OR b.content LIKE :content";

        Query query = em.createQuery(queryStr);
        query.setParameter("title", "%" + title + "%");
        query.setParameter("content", "%" + content + "%");

        return (long) query.getSingleResult();
    }

}