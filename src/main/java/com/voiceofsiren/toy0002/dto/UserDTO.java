package com.voiceofsiren.toy0002.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    @NotNull
    private String username;

    private String password;

    private Boolean enabled;

}
