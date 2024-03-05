package com.voiceofsiren.toy0002.service;

import com.voiceofsiren.toy0002.domain.Board;
import com.voiceofsiren.toy0002.dto.BoardDTO;
import com.voiceofsiren.toy0002.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
    @Transactional(readOnly = false)
    public void save(BoardDTO boardDTO) {
        boardRepository.save(new Board(boardDTO));
    }

    @Transactional(readOnly = true)
    public BoardDTO findById(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (!optionalBoard.isPresent()) {
            throw new IllegalStateException("해당 게시글이 존재하지 않습니다.");
        }
        Board board =  optionalBoard.orElse(new Board(new BoardDTO(0L, "제목 없음", "내용 없음")));
        return new BoardDTO(board);
    }

    public List<BoardDTO> convert(List<Board> boards) {
        return boards.stream()
                .map(board -> new BoardDTO(board))
                .collect(Collectors.toList());
    }

}
