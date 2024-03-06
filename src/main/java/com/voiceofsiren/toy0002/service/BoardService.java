package com.voiceofsiren.toy0002.service;

import com.voiceofsiren.toy0002.domain.Board;
import com.voiceofsiren.toy0002.dto.BoardDTO;
import com.voiceofsiren.toy0002.exception.BoardNotFoundException;
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
    public BoardDTO save(BoardDTO boardDTO) {
        Board board = boardRepository.save(new Board(boardDTO));
        return new BoardDTO(board);
    }

    @Transactional(readOnly = true)
    public BoardDTO findById(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (!optionalBoard.isPresent()) {
            throw new BoardNotFoundException(id);
        }
        Board board =  optionalBoard.get();
        return new BoardDTO(board);
    }

    /**
     * PUT API를 위한 Service 계층의 로직
     */
    @Transactional(readOnly = false)
    public BoardDTO replace(BoardDTO newBoardDTO, Long id) {
        return boardRepository.findById(id)
                .map(board -> {
                    board.setTitle(newBoardDTO.getTitle());
                    board.setContent(newBoardDTO.getContent());
                    return new BoardDTO(boardRepository.save(board));
                })
                .orElseGet(() -> {
                    newBoardDTO.setId(id);
                    return new BoardDTO(boardRepository.save(new Board(newBoardDTO)));
                });
    }

    /**
     * DELETE API를 위한 Service 계층의 로직
     */
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BoardDTO> findByTitle(String title) {
        List<Board> boards = boardRepository.findByTitle(title);
        return convert(boards);

    }

    @Transactional(readOnly = true)
    public List<BoardDTO> findByContent(String content) {
        List<Board> boards = boardRepository.findByContent(content);
        return convert(boards);
    }

    @Transactional(readOnly = true)
    public List<BoardDTO> findByTitleOrContent(String title, String content) {
        List<Board> boards = boardRepository.findByTitleOrContent(title,content);
        return convert(boards);
    }

    public List<BoardDTO> convert(List<Board> boards) {
        return boards.stream()
                .map(board -> new BoardDTO(board))
                .collect(Collectors.toList());
    }



}
