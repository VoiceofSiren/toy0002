package com.voiceofsiren.toy0002.repository;

import com.voiceofsiren.toy0002.domain.Board;
import com.voiceofsiren.toy0002.dto.BoardDTO;
import com.voiceofsiren.toy0002.dto.BoardPageDTO;
import com.voiceofsiren.toy0002.dto.UserDTO;
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
                + "WHERE b.title LIKE :title OR b.content LIKE :content";

        Query query = em.createQuery(queryStr, BoardPageDTO.class);
        query.setParameter("title", "%" + title + "%");
        query.setParameter("content", "%" + content + "%");

        // 쿼리 결과를 한 번만 호출하여 results 변수에 저장
        List<BoardPageDTO> results = query.setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        // 쿼리 결과가 비어 있을 경우를 대비하여 size 설정
        int size = results.isEmpty() ? 0 : results.size();

        return new PageImpl<>(results, pageable, size);
    }

}