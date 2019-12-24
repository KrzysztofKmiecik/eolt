package com.java26.eolt.mapper;

import com.java26.eolt.dto.UserDto;
import com.java26.eolt.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto map(User user);
}
