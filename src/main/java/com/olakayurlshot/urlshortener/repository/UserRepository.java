package com.olakayurlshot.urlshortener.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.olakayurlshot.urlshortener.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    // Optional<UserDetails> findByUsername(String username);

    Optional<User> findByUsername(String username);

}