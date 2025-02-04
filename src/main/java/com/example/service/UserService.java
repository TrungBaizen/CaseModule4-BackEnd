package com.example.service;

import com.example.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    void save(User user);

    Iterable<User> findAll();

    User findByUsername(String username);

    User getCurrentUser();

    Optional<User> findById(Long id);

    UserDetails loadUserById(Long id);
    public Page<User> getAllUsers(Pageable pageable);
    boolean checkLogin(User user);

    boolean isRegister(User user);
    void updateTokenRemainingTime(Long userId, Long remainingTime);
    Optional<User> findByIdentityCode(Long id);
    User updateEnabled(String username , boolean enabled);
}