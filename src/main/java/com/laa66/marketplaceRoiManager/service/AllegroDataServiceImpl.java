package com.laa66.marketplaceRoiManager.service;

import com.laa66.marketplaceRoiManager.dto.CategoryDto;
import com.laa66.marketplaceRoiManager.model.response.ResponseCategoryCollection;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.function.Supplier;

@Slf4j
@AllArgsConstructor
public class AllegroDataServiceImpl implements AllegroDataService {

    private final RestTemplate allegroApiRestTemplate;

    private final static Supplier<RuntimeException> API_RESPONSE_EXCEPTION = () -> new RuntimeException("API response exception thrown.");

    private final static URI OFFER_FEE_PREVIEW_URL = URI.create("https://api.allegro.pl/pricing/offer-fee-preview");
    private final static URI SALE_CATEGORIES_URL = URI.create("https://api.allegro.pl/sale/categories");

    @Override
    public List<CategoryDto> getCategoriesTree() {
        Queue<CategoryDto> categoryDtoQueue =
                new LinkedList<>(getCategoryChildren(null).categories());

        LinkedList<CategoryDto> globalCategories = new LinkedList<>(categoryDtoQueue);

        int count = 0;
        while (!categoryDtoQueue.isEmpty()) {
            count++;
            CategoryDto categoryDto = categoryDtoQueue.poll();
            Optional.ofNullable(categoryDto)
                    .ifPresent(polledDto -> {
                        List<CategoryDto> categories = getCategoryChildren(polledDto.getId()).categories();
                        categoryDtoQueue.addAll(categories);
                        globalCategories.addAll(categories);
                    });
            log.info("count={},queue={},list={}", count, categoryDtoQueue.size(), globalCategories.size());
        }
        return globalCategories;
    }

    @Override
    public ResponseCategoryCollection getCategoryChildren(@Nullable String categoryId) {
        ResponseCategoryCollection categories = allegroApiRestTemplate
                .getForObject(categoryId == null ? SALE_CATEGORIES_URL : UriComponentsBuilder
                                .fromUri(SALE_CATEGORIES_URL)
                                .queryParam("parent.id", categoryId)
                                .build()
                                .toUri(),
                        ResponseCategoryCollection.class);
        return Optional.ofNullable(categories)
                .orElseThrow(API_RESPONSE_EXCEPTION);
    }
}
