package com.voiceofsiren.toy0002.service;

import com.voiceofsiren.toy0002.domain.Board;
import com.voiceofsiren.toy0002.dto.BoardDTO;
import com.voiceofsiren.toy0002.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<BoardDTO> findAll() {
        List<Board> boards = boardRepository.findAll();
        return convert(boards);
    }

    public List<BoardDTO> convert(List<Board> boards) {
        return boards.stream()
                .map(board -> new BoardDTO(board))
                .collect(Collectors.toList());
    }
}
