package com.voiceofsiren.toy0002.service;

import com.voiceofsiren.toy0002.domain.Board;
import com.voiceofsiren.toy0002.domain.User;
import com.voiceofsiren.toy0002.dto.BoardDTO;
import com.voiceofsiren.toy0002.dto.BoardPageDTO;
import com.voiceofsiren.toy0002.dto.UserDTO;
import com.voiceofsiren.toy0002.exception.BoardNotFoundException;
import com.voiceofsiren.toy0002.repository.BoardJpaRepository;
import com.voiceofsiren.toy0002.repository.BoardRepository;
import com.voiceofsiren.toy0002.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final BoardJpaRepository boardJpaRepository;

    private final UserJpaRepository userJpaRepository;

    @Transactional(readOnly = true)
    public List<BoardDTO> findAll() {
        List<Board> boards = boardJpaRepository.findAll();
        List<BoardDTO> boardDTOs = boards.stream()
                .map(board -> new BoardDTO(board))
                .collect(Collectors.toList());
        return boardDTOs;
    }

    @Transactional(readOnly = true)
    public Page<BoardDTO> findAll(Pageable pageable) {
        Page<Board> boards = boardJpaRepository.findAll(pageable);
        return convert(boards);
    }

    @Transactional(readOnly = false)
    public BoardDTO save(BoardDTO boardDTO) {
        Board board = boardJpaRepository.save(new Board(boardDTO));
        return new BoardDTO(board);
    }

    @Transactional(readOnly = false)
    public BoardDTO save(BoardDTO boardDTO, User user) {
        User foundUser = userJpaRepository.findByUsername(user.getUsername());
        Board newBoard = new Board(boardDTO);
        newBoard.addUser(foundUser);
        Board board = boardJpaRepository.save(newBoard);
        return new BoardDTO(board);
    }

    @Transactional(readOnly = true)
    public BoardDTO findById(Long id) {
        Optional<Board> optionalBoard = boardJpaRepository.findById(id);
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
        return boardJpaRepository.findById(id)
                .map(board -> {
                    board.setTitle(newBoardDTO.getTitle());
                    board.setContent(newBoardDTO.getContent());
                    return new BoardDTO(boardJpaRepository.save(board));
                })
                .orElseGet(() -> {
                    newBoardDTO.setId(id);
                    return new BoardDTO(boardJpaRepository.save(new Board(newBoardDTO)));
                });
    }

    /**
     * DELETE API를 위한 Service 계층의 로직
     */
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        boardJpaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BoardDTO> findByTitle(String title) {
        List<Board> boards = boardJpaRepository.findByTitle(title);
        return convert(boards);

    }

    @Transactional(readOnly = true)
    public List<BoardDTO> findByContent(String content) {
        List<Board> boards = boardJpaRepository.findByContent(content);
        return convert(boards);
    }

    @Transactional(readOnly = true)
    public List<BoardDTO> findByTitleOrContent(String title, String content) {
        List<Board> boards = boardJpaRepository.findByTitleOrContent(title,content);
        return convert(boards);
    }

    @Transactional(readOnly = true)
    public Page<BoardPageDTO> findByTitleOrContent(String title, String content, Pageable pageable) {
        Page<BoardPageDTO> boards = boardRepository.showBoardPageList(title, content, pageable);
        return boards;
    }

    public List<BoardDTO> convert(List<Board> boards) {
        return boards.stream()
                .map(board -> new BoardDTO(board))
                .collect(Collectors.toList());
    }

    public Page<BoardDTO> convert(Page<Board> boards) {
        return boards.map(board -> new BoardDTO(board));
    }

}
