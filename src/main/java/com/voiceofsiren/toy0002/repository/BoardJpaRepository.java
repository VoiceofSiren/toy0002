package com.voiceofsiren.toy0002.repository;

import com.voiceofsiren.toy0002.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardJpaRepository extends JpaRepository<Board, Long> {

    Page<Board> findAll(Pageable pageable);

    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

    List<Board> findByTitle(String title);

    List<Board> findByContent(String content);

    List<Board> findByTitleOrContent(String title, String content);
}
