package com.olakayurlshot.urlshortener.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olakayurlshot.urlshortener.dto.UrlDTO;
import com.olakayurlshot.urlshortener.entity.UrlShot;
import com.olakayurlshot.urlshortener.entity.User;
import com.olakayurlshot.urlshortener.repository.UserRepository;
import com.olakayurlshot.urlshortener.service.UrlShotService;

import jakarta.validation.Valid;

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

    @SuppressWarnings("rawtypes")
    @PostMapping("/")
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity addUrl(
        @RequestBody @Valid UrlDTO entity, BindingResult bindingResult,
        @AuthenticationPrincipal UserDetails currentUser
        ) {
            if (bindingResult.hasErrors()) {
                // Convert errors to a more readable format
                Map<String, Object> errors = new HashMap<>();
                errors.put("errors", bindingResult.getFieldErrors().stream()
                        .collect(Collectors.toMap(
                                error -> error.getField(),
                                error -> error.getDefaultMessage()
                        )));
                return ResponseEntity.badRequest().body(errors);
            }
            String username = currentUser.getUsername();
            User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
            UrlShot urlShot = new UrlShot();
            urlShot.setUser(user);
            urlShot.setName(entity.getName());;
            urlShot.setUrl(entity.getUrl());
            urlShot.setDescription(entity.getDescription());
            urlShotService.addUrl(urlShot);
        return ResponseEntity.ok().body(urlShot);
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
