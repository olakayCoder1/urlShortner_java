package com.olakayurlshot.urlshortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olakayurlshot.urlshortener.dto.UrlResponse;
import com.olakayurlshot.urlshortener.entity.UrlShot;
import com.olakayurlshot.urlshortener.error.ErrorMessage;
import com.olakayurlshot.urlshortener.repository.UrlShotRepository;

@Service
public class VisitUrlService {
    
    @Autowired
    private UrlShotRepository urlShotRepository;

    @SuppressWarnings("unchecked")
    public <T> ResponseEntity<T> visitUrl(String refKey) {
        UrlShot urlRecord = urlShotRepository.findOneByRefKey(refKey);
        if (urlRecord != null) {
            String redirect_url = urlRecord.getUrl();
            if (urlRecord.getVisitCount() != null) {
                urlRecord.setVisitCount(urlRecord.getVisitCount() + 1);
            }else{
                urlRecord.setVisitCount(1);
            }
            urlShotRepository.save(urlRecord);
            return (ResponseEntity<T>) ResponseEntity.ok(UrlResponse.builder().url(redirect_url).build());
        } else {
            
            ErrorMessage error = new ErrorMessage("Invalid refKey. No URL found.");

            return (ResponseEntity<T>) ResponseEntity.badRequest().body(error);
        }
    }
}
