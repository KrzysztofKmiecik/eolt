package com.java26.eolt.service;

import com.java26.eolt.entity.CustomUserDetails;
import com.java26.eolt.entity.User;
import com.java26.eolt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException("userName Not Found"));
        return optionalUser.map(CustomUserDetails::new).get();

    }
}
