package com.olakayurlshot.urlshortener.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UrlShot  {

    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    private String url;
    private String name;
    private String description;
    private Integer visitCount;

    
}
