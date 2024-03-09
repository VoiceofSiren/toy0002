package com.voiceofsiren.toy0002.dto;

import com.voiceofsiren.toy0002.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {

    private Long id;

    @NotNull
    private String username;

    private String password;

    private Boolean enabled;

    private List<BoardDTO> boardDTOS = new ArrayList<>();

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
    }

}
