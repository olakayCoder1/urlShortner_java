package com.olakayurlshot.urlshortener.repository;

import com.olakayurlshot.urlshortener.entity.UrlShot;
import com.olakayurlshot.urlshortener.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlShotRepository extends JpaRepository<UrlShot, Integer> {

    List<UrlShot> findByUser(User user);

    boolean existsByRefKey(String refKey);

    UrlShot findOneByRefKey(String refKey);
    
}
