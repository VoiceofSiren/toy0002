package com.voiceofsiren.toy0002.mapper;

import com.voiceofsiren.toy0002.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findUsers(@Param("text") String text);
}
