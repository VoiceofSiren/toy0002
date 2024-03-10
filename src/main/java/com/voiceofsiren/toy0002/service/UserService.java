package com.voiceofsiren.toy0002.service;

import com.voiceofsiren.toy0002.domain.Role;
import com.voiceofsiren.toy0002.domain.User;
import com.voiceofsiren.toy0002.domain.UserRole;
import com.voiceofsiren.toy0002.dto.UserDTO;
import com.voiceofsiren.toy0002.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserJpaRepository userJpaRepository;

    private final RoleRepository roleRepository;
    private final RoleJpaRepository roleJpaRepository;

    private final UserRoleJpaRepository userRoleJpaRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = false)
    public void save(UserDTO userDTO) {
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        userDTO.setEnabled(true);
        User user = convert(userDTO);

        Role role = new Role();
        role.setName("ROLE_USER");

        UserRole userRole = UserRole.createUserRole(user, role);
        user.addUserRole(userRole);
        role.addUserRole(userRole);

        userJpaRepository.save(user);
        roleJpaRepository.save(role);
        userRoleJpaRepository.save(userRole);
    }

    public User convert(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEnabled(userDTO.getEnabled());
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        List<User> users = userJpaRepository.findAll();
        return users;
    }

    public UserDTO findById(Long id) {
        Optional<User> optionalUser = userJpaRepository.findById(id);
        return optionalUser.map(user -> new UserDTO(user)).get();
    }
}
