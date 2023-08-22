package com.example.demo;

import com.example.demo.repository.Entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username).map(user -> new CustomUserCredential(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        )).orElseThrow(() -> new RuntimeException("ユーザーが見つかりませんでした"));
    }
}
