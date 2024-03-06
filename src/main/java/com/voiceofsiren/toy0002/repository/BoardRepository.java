package com.voiceofsiren.toy0002.repository;

import com.voiceofsiren.toy0002.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {


    List<Board> findByTitle(String title);

    List<Board> findByContent(String content);

    List<Board> findByTitleOrContent(String title, String content);
}
