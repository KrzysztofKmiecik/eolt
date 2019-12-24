package com.java26.eolt.mapper;

import com.java26.eolt.dto.UserDto;
import com.java26.eolt.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-16T21:06:51+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto map(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setUsername( user.getUsername() );
        userDto.setPassword( user.getPassword() );

        return userDto;
    }
}
