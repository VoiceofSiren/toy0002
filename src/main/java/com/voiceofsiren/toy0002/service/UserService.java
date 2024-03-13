package com.voiceofsiren.toy0002.service;

import com.querydsl.core.types.Predicate;
import com.voiceofsiren.toy0002.domain.Board;
import com.voiceofsiren.toy0002.domain.Role;
import com.voiceofsiren.toy0002.domain.User;
import com.voiceofsiren.toy0002.domain.UserRole;
import com.voiceofsiren.toy0002.dto.BoardDTO;
import com.voiceofsiren.toy0002.dto.UserDTO;
import com.voiceofsiren.toy0002.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserJpaRepository userJpaRepository;

    private final RoleRepository roleRepository;
    private final RoleJpaRepository roleJpaRepository;

    private final UserRoleJpaRepository userRoleJpaRepository;

    private final BoardJpaRepository boardJpaRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<User> findByUsernameQuery(String text) {
        return userJpaRepository.findByUsernameQuery(text);
    }

    @Transactional(readOnly = true)
    public List<User> findByUsernameNativeQuery(String text) {
        return userJpaRepository.findByUsernameNativeQuery(text);
    }

    @Transactional(readOnly = false)
    public void save(UserDTO userDTO) {
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        userDTO.setEnabled(true);
        User user = convert(userDTO);

        Role role = roleJpaRepository.findByName("ROLE_USER");

        UserRole userRole = UserRole.createUserRole(user, role);
        user.addUserRole(userRole);
        role.addUserRole(userRole);

        userJpaRepository.save(user);
        roleJpaRepository.save(role);
        userRoleJpaRepository.save(userRole);

        //== 사용자 등록 시 가입 인사글 자동 생성 ==//
        Board board = new Board();
        board.setTitle("안녕하세요.");
        board.setContent("반갑습니다.");
        board.setUser(user);
        Board savedBoard = boardJpaRepository.save(board);
        user.getBoards().add(savedBoard);
        //=====================================//
    }

    public User convert(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEnabled(userDTO.getEnabled());
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        List<User> users = userJpaRepository.findAll();
        return users;
    }

    @Transactional(readOnly = true)
    public Iterable<User> findAll(Predicate predicate) {
        Iterable<User> users = userJpaRepository.findAll(predicate);
        return users;
    }

    @Transactional(readOnly = true)
    public List<User> findAllEntities() {
        List<User> users = userRepository.findAllEntities();
        for (User user: users) {
            // Proxy 강제 초기화
            user.getBoards().stream().forEach(board -> board.getTitle());
            user.getUserRoles().stream().forEach(userRole -> userRole.getId());
        }
        return users;
    }

    @Transactional(readOnly = true)
    public List<User> findByUsernameCustomized(String username) {
        return userJpaRepository.findByUsernameCustomized(username);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User user = userJpaRepository.findById(id).get();
        List<Board> boards = user.getBoards();
        // Proxy 강제 초기화
        boards.forEach(board -> board.getTitle());
        List<UserRole> userRoles = user.getUserRoles();
        // Proxy 강제 초기화
        for (UserRole userRole: userRoles) {
            userRole.getUser().getUsername();
        }
        return new UserDTO(user);
    }

    public UserDTO replace(UserDTO newUserDTO, Long id) {
        User foundUser = userJpaRepository.findById(id).get();
        foundUser.getBoards().clear();
        foundUser.getBoards().addAll(newUserDTO.getBoards().stream()
                .map(boardDTO -> new Board(boardDTO))
                .collect(Collectors.toList()));
        for (BoardDTO boardDTO: newUserDTO.getBoards()) {
            Board board = new Board(boardDTO);
            board.setUser(foundUser);
            foundUser.addBoard(board);
        }
        User savedUser = userJpaRepository.save(foundUser);
        return new UserDTO(savedUser);

    }

    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }



}
