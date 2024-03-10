package com.voiceofsiren.toy0002.controller;

import com.voiceofsiren.toy0002.domain.Board;
import com.voiceofsiren.toy0002.domain.User;
import com.voiceofsiren.toy0002.domain.UserRole;
import com.voiceofsiren.toy0002.dto.BoardDTO;
import com.voiceofsiren.toy0002.dto.UserDTO;
import com.voiceofsiren.toy0002.dto.UserRoleDTO;
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

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
class UserApiController {

    private final UserService userService;

    private final UserRepository userRepository;
    private final UserJpaRepository userJpaRepository;


    @GetMapping("/v1/users")
    public List<User> allV1() {
        List<User> users = userRepository.findAllEntities();
        for (User user: users) {
            List<UserRole> userRoles = user.getUserRoles();
            userRoles.stream().forEach(userRole -> userRole.getRole().getName());
            List<Board> boards = user.getBoards();
            boards.stream().forEach(board -> board.getTitle());
        }
        return users;
    }


    @GetMapping("/v2/users")
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
    public UserDTO newEmployee(@RequestBody UserDTO userDTO) {
        //return userService.save(userDTO);
        return null;
    }

    @GetMapping("/users/{id}")
    public UserDTO one(@PathVariable Long id) {
        return userService.findById(id);
    }

    /*
    @PutMapping("/users/{id}")
    UserDTO replaceEmployee(@RequestBody UserDTO newUserDTO, @PathVariable Long id) {
        return userService.replace(newUserDTO, id);
    }
    */

    /*
    @DeleteMapping("/users/{id}")
    void deleteEmployee(@PathVariable Long id) {
        userService.deleteById(id);
    }
    */

}