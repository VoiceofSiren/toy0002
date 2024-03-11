package com.voiceofsiren.toy0002.dto;

import com.voiceofsiren.toy0002.domain.Board;
import com.voiceofsiren.toy0002.domain.User;
import com.voiceofsiren.toy0002.domain.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDTO {

    private Long id;

    @NotNull
    private String username;

    private String password;

    private Boolean enabled;

    private List<UserRoleDTO> userRoles = new ArrayList<>();

    private List<BoardDTO> boards = new ArrayList<>();

    public UserDTO() {

    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
        this.userRoles = user.getUserRoles().stream()
                .map(userRole -> new UserRoleDTO(userRole))
                .collect(Collectors.toList());
        this.boards = user.getBoards().stream()
                .map(board -> new BoardDTO(board))
                .collect(Collectors.toList());
    }

    public UserDTO(User user, BoardDTO boardDTO) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
        this.userRoles = user.getUserRoles().stream()
                .map(userRole -> new UserRoleDTO(userRole))
                .collect(Collectors.toList());
        this.boards.add(boardDTO);
    }

}
