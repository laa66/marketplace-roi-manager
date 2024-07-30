package com.laa66.marketplaceRoiManager.service;

import com.laa66.marketplaceRoiManager.dto.CategoryDto;
import com.laa66.marketplaceRoiManager.model.response.ResponseCategories;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.function.Supplier;

@AllArgsConstructor
public class AllegroDataServiceImpl implements AllegroDataService {

    private RestTemplate allegroApiRestTemplate;

    private final static Supplier<RuntimeException> API_RESPONSE_EXCEPTION = () -> new RuntimeException("API response exception thrown.");

    private final static URI OFFER_FEE_PREVIEW_URL = URI.create("https://api.allegro.pl/pricing/offer-fee-preview");
    private final static URI SALE_CATEGORIES_URL = URI.create("https://api.allegro.pl/sale/categories");

    @Override
    public Collection<CategoryDto> getCategoriesTree() {
        Queue<CategoryDto> categoryDtoQueue =
                new LinkedList<>(getCategoryChildren(null).categories());

        Set<CategoryDto> globalCategories = new TreeSet<>((dto1, dto2) -> {
            int res = String.CASE_INSENSITIVE_ORDER.compare(dto1.getName(), dto2.getName());
            return (res != 0) ? res : dto1.getName().compareTo(dto2.getName());
        });

        globalCategories.addAll(categoryDtoQueue);

        while (!categoryDtoQueue.isEmpty()) {
            CategoryDto categoryDto = categoryDtoQueue.poll();
            Optional.ofNullable(categoryDto)
                    .ifPresent(polledDto -> {
                        List<CategoryDto> categories = getCategoryChildren(polledDto.getId())
                                .categories();
                        categoryDtoQueue
                                .addAll(categories);
                        globalCategories.addAll(categories);
                    });
        }
        return globalCategories;
    }

    // TODO throwable to private service field
    @Override
    public ResponseCategories getCategoryChildren(@Nullable String categoryId) {
        System.out.println(categoryId);
        ResponseCategories categories = allegroApiRestTemplate
                .getForObject(categoryId == null ? SALE_CATEGORIES_URL : UriComponentsBuilder
                                .fromUri(SALE_CATEGORIES_URL)
                                .queryParam("parent.id", categoryId)
                                .build()
                                .toUri(),
                        ResponseCategories.class);
        return Optional.ofNullable(categories)
                .orElseThrow(API_RESPONSE_EXCEPTION);
    }
}
