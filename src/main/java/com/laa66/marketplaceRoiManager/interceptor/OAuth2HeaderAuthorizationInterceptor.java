package com.laa66.marketplaceRoiManager.interceptor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class OAuth2HeaderAuthorizationInterceptor implements ClientHttpRequestInterceptor {

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        OAuth2AuthenticationToken authenticationToken = getAuthenticationToken();
        OAuth2AccessToken accessToken = getAccessToken(authenticationToken);
        setRequestHeaders(request, accessToken);
        return execution.execute(request, body);
    }

    private void setRequestHeaders(HttpRequest request, OAuth2AccessToken accessToken) {
        HttpHeaders headers = request.getHeaders();
        headers.setBearerAuth(accessToken.getTokenValue());
        headers.set("Accept", "application/vnd.allegro.public.v1+json");
    }

    private OAuth2AuthenticationToken getAuthenticationToken() {
        return (OAuth2AuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
    }

    private OAuth2AccessToken getAccessToken(OAuth2AuthenticationToken authenticationToken) {
        OAuth2AuthorizedClient oAuth2AuthorizedClient = oAuth2AuthorizedClientService.loadAuthorizedClient(authenticationToken.getAuthorizedClientRegistrationId(), authenticationToken.getName());
        return oAuth2AuthorizedClient.getAccessToken();
    }
}
