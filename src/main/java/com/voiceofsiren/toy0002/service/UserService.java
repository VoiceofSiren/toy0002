package com.voiceofsiren.toy0002.service;

import com.voiceofsiren.toy0002.domain.User;
import com.voiceofsiren.toy0002.dto.UserDTO;
import com.voiceofsiren.toy0002.repository.UserJpaRepository;
import com.voiceofsiren.toy0002.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserJpaRepository userJpaRepository;

    private final PasswordEncoder passwordEncoder;

    public void save(UserDTO userDTO) {
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        userDTO.setEnabled(true);
        userRepository.save(convert(userDTO));
    }

    public User convert(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEnabled(userDTO.getEnabled());
        return user;
    }
}
