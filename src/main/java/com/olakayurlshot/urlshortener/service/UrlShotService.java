package com.olakayurlshot.urlshortener.service;


import java.util.List;

import com.olakayurlshot.urlshortener.dto.UrlDTO;
import com.olakayurlshot.urlshortener.entity.UrlShot;

public interface UrlShotService {

    void addUrl(UrlShot url);

    List<UrlShot> getUrls();

    UrlShot getUrl(Integer id);

    void deleteUrl(Integer id);

    void updateUlr(Integer id, UrlShot urlShot);

    void partUpdateUlr(Integer id, UrlDTO entity);
    
}
