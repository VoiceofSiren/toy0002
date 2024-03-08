package com.voiceofsiren.toy0002.service;

import com.voiceofsiren.toy0002.repository.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleJpaRepository roleRepository;

}
