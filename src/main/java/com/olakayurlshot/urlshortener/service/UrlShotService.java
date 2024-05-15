package com.olakayurlshot.urlshortener.service;


import java.util.List;

import com.olakayurlshot.urlshortener.dto.UrlDTO;
import com.olakayurlshot.urlshortener.entity.UrlShot;
import com.olakayurlshot.urlshortener.entity.User;

public interface UrlShotService {

    void addUrl(UrlShot url);

    List<UrlShot> getUrls(User user);

    UrlShot getUrl(Integer id,User user);

    void deleteUrl(Integer id,User user);

    void updateUlr(Integer id,User user, UrlShot urlShot);

    void partUpdateUlr(Integer id,User user, UrlDTO entity);
    
}
