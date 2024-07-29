package com.laa66.marketplaceRoiManager.service;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@AllArgsConstructor
public class AllegroDataServiceImpl implements AllegroDataService {

    private RestTemplate allegroApiRestTemplate;

    private final static URI OFFER_FEE_PREVIEW_URL = URI.create("https://api.allegro.pl/pricing/offer-fee-preview");
    private final static URI SALE_CATEGORIES_URL = URI.create("https://api.allegro.pl/sale/categories");

    @Override
    public void getAllCategories() {
        ResponseEntity<String> forEntity = allegroApiRestTemplate.getForEntity(SALE_CATEGORIES_URL, String.class);
        String body = forEntity.getBody();
        System.out.println(body);
    }
}
