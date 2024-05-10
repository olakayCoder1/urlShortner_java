package com.olakayurlshot.urlshortener.repository;

import com.olakayurlshot.urlshortener.entity.UrlShot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlShotRepository extends JpaRepository<UrlShot, Integer> {
    
}
