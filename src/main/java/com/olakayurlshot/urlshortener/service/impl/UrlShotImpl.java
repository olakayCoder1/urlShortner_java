package com.olakayurlshot.urlshortener.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.olakayurlshot.urlshortener.dto.UrlDTO;
import com.olakayurlshot.urlshortener.entity.UrlShot;
import com.olakayurlshot.urlshortener.entity.User;
import com.olakayurlshot.urlshortener.error.ApiRequestException;
import com.olakayurlshot.urlshortener.repository.UrlShotRepository;
import com.olakayurlshot.urlshortener.service.UrlShotService;


@Service
public class UrlShotImpl implements UrlShotService {

    @Autowired
    private UrlShotRepository urlShotRepository;

    @Override
    public void addUrl(UrlShot url) {
        String refKey = generateRefKey();
        url.setRefKey(refKey);
        urlShotRepository.save(url);
    }


    public String generateRefKey() {
        String generatedKey;
        do {
            generatedKey = generateUniqueKey();
        } while (urlShotRepository.existsByRefKey(generatedKey));
        return generatedKey;
    }

    private String generateUniqueKey() {
        StringBuilder builder = new StringBuilder();
        String baseString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        builder.append(Long.toString(System.currentTimeMillis(), 36).substring(0, 2));

        for (int i = 0; i < 4; i++) {
            int randomIndex = (int) (Math.random() * baseString.length());
            builder.append(baseString.charAt(randomIndex));
        }

        return builder.toString();
    }



    @Override
    public List<UrlShot> getUrls(User user) {
        return urlShotRepository.findByUser(user);
    }

    @Override
    public UrlShot getUrl(Integer id,User user) {
        
        UrlShot shortUrl = urlShotRepository.findById(id).orElseThrow(
            () -> new ApiRequestException("Invalid url id "+ id)
            // () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid url id "+ id)
        ); 
               
        if (!shortUrl.getUser().getUsername().equals(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to access this URL.");
        }                  
        return shortUrl;
    }

    @Override
    public void deleteUrl(Integer id,User user) {
        UrlShot urlShot = urlShotRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid url id "+ id)
        );
        urlShotRepository.delete(urlShot);
    }

    @Override
    public void updateUlr(Integer id,User user, UrlShot urlShot) {
        urlShotRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid url id "+ id)
        );
        urlShot.setId(id);
        urlShotRepository.save(urlShot);
    }

    @Override
    public void partUpdateUlr(Integer id,User user, UrlDTO entity) {
        UrlShot shortUrl = urlShotRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid url id "+ id)
        ); 

        shortUrl.setName(entity.getName());
        urlShotRepository.save(shortUrl);
    }



    
    
}
