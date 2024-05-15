package com.olakayurlshot.urlshortener.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olakayurlshot.urlshortener.dto.UrlDTO;
import com.olakayurlshot.urlshortener.entity.UrlShot;
import com.olakayurlshot.urlshortener.entity.User;
import com.olakayurlshot.urlshortener.repository.UserRepository;
import com.olakayurlshot.urlshortener.service.UrlShotService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(path = "/api/v1/urls")
public class UrlShotController {
    
    @Autowired
    private UrlShotService urlShotService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    @PreAuthorize(value = "hasRole('USER')")
    public String addUrl(
        @RequestBody UrlShot urlShot, 
        @AuthenticationPrincipal UserDetails currentUser
        ) {
        String username = currentUser.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        urlShot.setUser(user);
        urlShotService.addUrl(urlShot);
        return "success";
    }
 
    @GetMapping("/")
    @PreAuthorize(value = "hasRole('USER')")
    public List<UrlShot> getUrls(@AuthenticationPrincipal UserDetails currentUser) {
        String username = currentUser.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return urlShotService.getUrls(user);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize(value = "hasRole('USER')")
    public UrlShot getUrl(
        @PathVariable(value = "id") Integer id,
        @AuthenticationPrincipal UserDetails currentUser
    ) {
        String username = currentUser.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return urlShotService.getUrl(id,user);
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<Void> updateUlr(
        @PathVariable(value = "id") Integer id, 
        @RequestBody UrlShot entity,
        @AuthenticationPrincipal UserDetails currentUser
    ) {
        String username = currentUser.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        urlShotService.updateUlr(id,user, entity);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<Void> partUpdateUlr(
        @PathVariable(value = "id") Integer id, 
        @RequestBody UrlDTO entity,
        @AuthenticationPrincipal UserDetails currentUser
    ) {
        String username = currentUser.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        urlShotService.partUpdateUlr(id,user, entity);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<Void> deleteUrl(
        @PathVariable(value = "id") Integer id,
        @AuthenticationPrincipal UserDetails currentUser
    ) {
        String username = currentUser.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        urlShotService.deleteUrl(id,user);
        return ResponseEntity.noContent().build();
    }


}
