package com.voiceofsiren.toy0002.controller;

import com.querydsl.core.types.Predicate;
import com.voiceofsiren.toy0002.domain.Board;
import com.voiceofsiren.toy0002.domain.QUser;
import com.voiceofsiren.toy0002.domain.User;
import com.voiceofsiren.toy0002.domain.UserRole;
import com.voiceofsiren.toy0002.dto.BoardDTO;
import com.voiceofsiren.toy0002.dto.UserDTO;
import com.voiceofsiren.toy0002.dto.UserRoleDTO;
import com.voiceofsiren.toy0002.mapper.UserMapper;
import com.voiceofsiren.toy0002.repository.UserJpaRepository;
import com.voiceofsiren.toy0002.repository.UserRepository;
import com.voiceofsiren.toy0002.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
class UserApiController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/users/{id}") // Done!!
    public UserDTO one(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/query/users")
    public List<User> allQuery(@RequestParam(required = false) String method,
                               @RequestParam(required = false) String text) {
        List<User> users = null;

        if (method.equals("query")) {
            users = userService.findByUsernameQuery(text);
        } else if (method.equals("nativeQuery")) {
            users = userService.findByUsernameNativeQuery(text);
        } else if (method.equals("queryDsl")) {
            QUser user = QUser.user;
            Predicate predicate = user.username.contains(text);
            Iterable<User> userIterable = userService.findAll(predicate);
            users = StreamSupport.stream(userIterable.spliterator(), false)
                    .collect(Collectors.toList());
        } else if (method.equals("queryDslCustom")) {
            users = userService.findByUsernameCustomized(text);
        } else if (method.equals("mybatis")) {
            users = userMapper.findUsers(text);
        } else {
            users = userService.findAll();
        }
        return users;
    }

    @GetMapping("/v1/users")
    public List<User> allV1() {
        List<User> users = userService.findAllEntities();
        for (User user: users) {
            List<UserRole> userRoles = user.getUserRoles();
            userRoles.stream().forEach(userRole -> userRole.getRole().getName());
            List<Board> boards = user.getBoards();
            boards.stream().forEach(board -> board.getTitle());
        }
        return users;
    }


    @GetMapping("/v2/users") // Done!!
    public List<UserDTOv2> allV2() {
        List<UserDTOv2> usersV2 = userService.findAllEntities().stream()
                .map(user -> new UserDTOv2(user))
                .collect(Collectors.toList());
        return usersV2;
    }

    @Data
    static class UserDTOv2 {
        private Long id;

        @NotNull
        private String username;
        private String password;
        private Boolean enabled;
        private List<UserRoleDTO> userRoleDTOS = new ArrayList<>();
        private List<BoardDTO> boards = new ArrayList<>();

        public UserDTOv2(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.enabled = user.getEnabled();

            this.userRoleDTOS = user.getUserRoles().stream()
                    .map(userRole -> new UserRoleDTO(userRole))
                    .collect(Collectors.toList());
            this.boards = user.getBoards().stream()
                    .map(board -> new BoardDTO(board))
                    .collect(Collectors.toList());

        }
    }

    @PostMapping("/users")
    public UserDTO newUser(@RequestBody UserDTO userDTO) {
        //return userService.save(userDTO);
        return null;
    }




    @PutMapping("/users/{id}") // Done!!
    public UserDTO replaceUser(@RequestBody UserDTO newUserDTO,
                               @PathVariable Long id) {
        return userService.replace(newUserDTO, id);
    }



    @DeleteMapping("/users/{id}") // Done!!
    void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

}