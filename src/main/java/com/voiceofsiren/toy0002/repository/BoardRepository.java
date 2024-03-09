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

        Page<BoardPageDTO> boards = new PageImpl<>(
                query.setFirstResult((int) pageable.getOffset())
                        .setMaxResults(pageable.getPageSize())
                        .getResultList(),
                pageable,
                query.getResultList().size()
        );

        return boards;
    }

}