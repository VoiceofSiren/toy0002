package com.voiceofsiren.toy0002.repository;

import com.voiceofsiren.toy0002.domain.User;

import java.util.List;

interface CustomizedUserRepository {
    List<User> findByUsernameCustomized(String username);
}