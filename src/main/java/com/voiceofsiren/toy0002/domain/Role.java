package com.voiceofsiren.toy0002.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    private String name;

    @OneToMany( mappedBy = "role",
            fetch = FetchType.LAZY)
    private List<UserRole> userRoles = new ArrayList<>();

    //== 연관 관계 메서드 ==//
    public void addUserRole(UserRole userRole) {
        this.userRoles.add(userRole);
        userRole.setRole(this);
    }

}
