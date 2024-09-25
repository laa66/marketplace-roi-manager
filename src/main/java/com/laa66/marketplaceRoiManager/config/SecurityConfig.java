package com.laa66.marketplaceRoiManager.config;

import com.laa66.marketplaceRoiManager.interceptor.HeaderRestTemplateInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.AuthenticationMethod;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${api.allegro.client-id}")
    private String ALLEGRO_CLIENT_ID;

    @Value("${api.allegro.client-secret}")
    private String ALLEGRO_CLIENT_SECRET;

    @Value("${api.allegro.scope}")
    private String ALLEGRO_SCOPE;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, RestTemplate oAuth2RestTemplate) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/csv")
                        //.permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2Login(oauth2 ->
                        oauth2.userInfoEndpoint(userInfo ->
                                userInfo.userService(this.oAuth2UserService(oAuth2RestTemplate))));
        return httpSecurity.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(allegroClientRegistration());
    }

    @Bean("oAuth2RestTemplate")
    public RestTemplate oAuth2RestTemplate() {
        log.debug("Using oAuth2RestTemplate bean.");
        HeaderRestTemplateInterceptor interceptor = new HeaderRestTemplateInterceptor();
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        interceptors.add(interceptor);
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    private ClientRegistration allegroClientRegistration() {
        return ClientRegistration.withRegistrationId("Allegro")
                .clientName("Allegro")
                .clientId(ALLEGRO_CLIENT_ID)
                .clientSecret(ALLEGRO_CLIENT_SECRET)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .userInfoAuthenticationMethod(AuthenticationMethod.HEADER)
                .authorizationUri("https://allegro.pl/auth/oauth/authorize")
                .tokenUri("https://allegro.pl/auth/oauth/token")
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .userInfoUri("https://api.allegro.pl/me")
                .userNameAttributeName("login")
                .scope("allegro:api:profile:read", "allegro:api:sale:offers:read")
                .build();
    }

    // OAuth2 UserInfo Endpoint additional configuration
    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService(RestTemplate oAuth2RestTemplate) {
        DefaultOAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        oAuth2UserService.setRestOperations(oAuth2RestTemplate);
        return oAuth2UserService;
    }
}
