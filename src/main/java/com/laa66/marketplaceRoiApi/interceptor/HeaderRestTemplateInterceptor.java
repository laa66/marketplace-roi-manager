package com.laa66.marketplaceRoiApi.interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.MultiValueMapAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HeaderRestTemplateInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().addAll(new MultiValueMapAdapter<>(Map.of(
                "Accept", List.of("application/vnd.allegro.public.v1+json"),
                "Content-Type", List.of("application/vnd.allegro.public.v1+json")
        )));
        return execution.execute(request, body);
    }
}

