package com.voiceofsiren.toy0002.dto;

import com.voiceofsiren.toy0002.domain.UserRole;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleDTO {

    private Long id;

    private String name;

}
