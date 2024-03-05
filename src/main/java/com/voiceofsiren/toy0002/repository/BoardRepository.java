package com.voiceofsiren.toy0002.repository;

import com.voiceofsiren.toy0002.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {



}
