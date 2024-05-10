package com.olakayurlshot.urlshortener.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.olakayurlshot.urlshortener.dto.UrlDTO;
import com.olakayurlshot.urlshortener.entity.UrlShot;
import com.olakayurlshot.urlshortener.repository.UrlShotRepository;
import com.olakayurlshot.urlshortener.service.UrlShotService;

@Service
public class UrlShotImpl implements UrlShotService {

    @Autowired
    private UrlShotRepository urlShotRepository;

    @Override
    public void addUrl(UrlShot url) {
        urlShotRepository.save(url);
    }

    @Override
    public List<UrlShot> getUrls() {
        return urlShotRepository.findAll();
    }

    @Override
    public UrlShot getUrl(Integer id) {
        UrlShot shortUrl = urlShotRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid url id "+ id)
        );                          
        return shortUrl;
    }

    @Override
    public void deleteUrl(Integer id) {
        UrlShot urlShot = urlShotRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid url id "+ id)
        );
        urlShotRepository.delete(urlShot);
    }

    @Override
    public void updateUlr(Integer id, UrlShot urlShot) {
        urlShotRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid url id "+ id)
        );
        urlShot.setId(id);
        urlShotRepository.save(urlShot);
    }

    @Override
    public void partUpdateUlr(Integer id, UrlDTO entity) {
        UrlShot shortUrl = urlShotRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid url id "+ id)
        ); 

        shortUrl.setName(entity.getName());
        urlShotRepository.save(shortUrl);
    }





    
    
}
