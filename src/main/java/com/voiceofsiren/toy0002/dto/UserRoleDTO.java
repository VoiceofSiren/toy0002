package com.voiceofsiren.toy0002.dto;

import com.voiceofsiren.toy0002.domain.UserRole;
import lombok.Data;

@Data
public class UserRoleDTO {

    private Long id;
    private Long userId;
    private Long roleId;

    public UserRoleDTO(UserRole userRole) {
        this.id = userRole.getId();
        this.userId = userRole.getUser().getId();
        this.roleId = userRole.getRole().getId();
    }
}
