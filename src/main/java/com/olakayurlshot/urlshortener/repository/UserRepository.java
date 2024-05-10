package com.olakayurlshot.urlshortener.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.olakayurlshot.urlshortener.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Optional<User> findByUsername(String username);

    Optional<UserDetails> findByUsername(String username);
}