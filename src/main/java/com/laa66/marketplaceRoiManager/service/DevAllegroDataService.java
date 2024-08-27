package com.laa66.marketplaceRoiManager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laa66.marketplaceRoiManager.dto.CategoryCollectionDto;
import com.laa66.marketplaceRoiManager.dto.CategoryDto;
import com.laa66.marketplaceRoiManager.model.allegro.request.*;
import com.laa66.marketplaceRoiManager.model.allegro.response.ResponseCategoryCollection;
import com.laa66.marketplaceRoiManager.model.allegro.response.ResponseCommission;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URI;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class DevAllegroDataService implements AllegroDataService {

    private final static URI OFFER_FEE_PREVIEW_URL = URI.create("https://api.allegro.pl/pricing/offer-fee-preview");

    private final RestTemplate allegroApiRestTemplate;

    @Override
    public List<CategoryDto> getCategoriesTree() {
        File file = new File("src/main/resources/categories.json");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ObjectMapper mapper = new ObjectMapper();
            CategoryCollectionDto categoryCollectionDto = mapper.readValue(reader, CategoryCollectionDto.class);
            return categoryCollectionDto.getCategories();
        } catch (IOException e) {
            log.info("Cannot read categories.json file");
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseCommission postOfferCommission(String name, String categoryId, String price, String currency) {
        RequestCommission request = RequestCommission.builder()
                .offer(new Offer(
                        null,
                        new Category(categoryId),
                        new SellingMode("BUY_NOW",
                                new Price(price, currency),
                                new NetPrice()),
                        new Publication("PT24H"),
                        new Parameter[0]))
                .classifiedsPackages(new ClassifiedsPackages())
                .marketplaceId("allegro-pl")
                .build();

        HttpEntity<RequestCommission> httpEntity = new HttpEntity<>(request);
        return allegroApiRestTemplate.postForObject(OFFER_FEE_PREVIEW_URL, httpEntity, ResponseCommission.class);
    }

    /**
     * Do not use in development mode.
     * @return UnsupportedOperationException
     */
    @Override
    @Deprecated
    public ResponseCategoryCollection getCategoryChildren(String categoryId) {
        throw new UnsupportedOperationException("Method cannot be used in development mode.");
    }
}
