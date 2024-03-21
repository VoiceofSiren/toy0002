package com.voiceofsiren.toy0002.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private List<UserRoleDTO> userRoles = new ArrayList<>();

    @JsonIgnore
    private List<BoardDTO> boards = new ArrayList<>();

    public UserDTO() {

    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
    }

}
