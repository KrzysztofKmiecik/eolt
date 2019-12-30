package com.java26.eolt.service;

import com.java26.eolt.dto.UserDto;
import com.java26.eolt.entity.User;
import com.java26.eolt.exception.EntityNotFoundException;
import com.java26.eolt.mapper.UserMapper;
import com.java26.eolt.myEnum.EoltRole;
import com.java26.eolt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;



    public void create(UserDto userDto) {
        User entity = new User();
        entity.setFirstName(userDto.getFirstName());
        entity.setUsername(userDto.getUsername());
        // Role role=roleRepository.findByRole(EoltRole.ROLE_USER).orElseThrow(()->new EntityNotFoundException("nie moge utworzyc ROLI"));
     //   entity.setRole(EoltRole.ROLE_USER);
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        entity.setPassword(encodedPassword);
        userRepository.save(entity);
    }

    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.map(user);
    }

    @Transactional
    public void update(String username, UserDto userDto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setFirstName(userDto.getFirstName());
        if (!StringUtils.isEmpty(userDto.getPassword())) {
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            user.setPassword(encodedPassword);
        }
    }


}
