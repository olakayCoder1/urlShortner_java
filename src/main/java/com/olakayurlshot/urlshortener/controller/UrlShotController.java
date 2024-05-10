package com.olakayurlshot.urlshortener.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olakayurlshot.urlshortener.dto.UrlDTO;
import com.olakayurlshot.urlshortener.entity.UrlShot;
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

    @PostMapping()
    public String addUrl(@RequestBody UrlShot urlShot) {
        urlShotService.addUrl(urlShot);
        
        return "success";
    }
 
    @GetMapping()
    public List<UrlShot> getUrls() {
        return urlShotService.getUrls();
    }
    
    @GetMapping("/{id}")
    public UrlShot getUrl(@PathVariable(value = "id") Integer id) {
        return urlShotService.getUrl(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUlr(@PathVariable(value = "id") Integer id, @RequestBody UrlShot entity) {
        urlShotService.updateUlr(id, entity);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> partUpdateUlr(@PathVariable(value = "id") Integer id, @RequestBody UrlDTO entity) {
        urlShotService.partUpdateUlr(id, entity);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUrl(@PathVariable(value = "id") Integer id) {
        urlShotService.deleteUrl(id);
        return ResponseEntity.noContent().build();
    }


    
    
}
