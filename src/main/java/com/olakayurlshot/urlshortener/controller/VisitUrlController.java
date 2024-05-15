package com.olakayurlshot.urlshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olakayurlshot.urlshortener.service.VisitUrlService;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping(path = "/api/v1/v")
public class VisitUrlController {

    @Autowired
    private VisitUrlService visitUrlService;

    
    @GetMapping("/{refKey}")
    public ResponseEntity<ResponseEntity<Object>> accessUlr(@PathVariable(value = "refKey") String refKey ) {
        return visitUrlService.visitUrl(refKey);
    }

    
}
