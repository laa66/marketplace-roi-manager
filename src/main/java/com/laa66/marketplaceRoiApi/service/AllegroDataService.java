package com.laa66.marketplaceRoiApi.service;

import com.laa66.marketplaceRoiApi.dto.CategoryDto;
import com.laa66.marketplaceRoiApi.model.allegro.response.Product;
import com.laa66.marketplaceRoiApi.model.allegro.response.ResponseCategoryCollection;
import com.laa66.marketplaceRoiApi.model.allegro.response.ResponseCommission;
import org.springframework.lang.Nullable;

import java.util.List;

public interface AllegroDataService {
    List<CategoryDto> getCategoriesTree();
    ResponseCategoryCollection getCategoryChildren(@Nullable String categoryId);
    ResponseCommission postOfferCommission(String name, String categoryId, String price, String currency);
    Product searchProducts(String ean);
}
