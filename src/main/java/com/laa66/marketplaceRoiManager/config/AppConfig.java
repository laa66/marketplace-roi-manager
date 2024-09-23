package com.laa66.marketplaceRoiManager.config;

import com.laa66.marketplaceRoiManager.interceptor.OAuth2HeaderAuthorizationInterceptor;
import com.laa66.marketplaceRoiManager.service.AllegroDataService;
import com.laa66.marketplaceRoiManager.service.ProductProcessingService;
import com.laa66.marketplaceRoiManager.service.impl.AllegroDataServiceImpl;
import com.laa66.marketplaceRoiManager.service.impl.DevAllegroDataService;
import com.laa66.marketplaceRoiManager.service.impl.ProductProcessingServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate allegroApiRestTemplate(OAuth2AuthorizedClientService authorizedClientService) {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        interceptors.add(new OAuth2HeaderAuthorizationInterceptor(authorizedClientService));
        restTemplate.setInterceptors(interceptors);
        log.debug("Added OAuth2HeaderAuthorizationInterceptor to RestTemplate");
        return restTemplate;
    }

    @Bean
    @Profile("prod")
    public AllegroDataService allegroDataService(RestTemplate allegroApiRestTemplate) {
        return new AllegroDataServiceImpl(allegroApiRestTemplate);
    }

    @Bean
    @Profile("dev")
    public AllegroDataService devAllegroDataService(RestTemplate allegroApiRestTemplate) {
        return new DevAllegroDataService(allegroApiRestTemplate);
    }

    @Bean
    public ProductProcessingService productProcessingService(AllegroDataService allegroDataService) {
        return new ProductProcessingServiceImpl(allegroDataService);
    }


}


