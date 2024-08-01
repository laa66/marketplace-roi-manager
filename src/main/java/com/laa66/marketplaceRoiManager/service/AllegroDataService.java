package com.laa66.marketplaceRoiManager.service;

import com.laa66.marketplaceRoiManager.dto.CategoryDto;
import com.laa66.marketplaceRoiManager.model.response.ResponseCategoryCollection;
import org.springframework.lang.Nullable;

import java.util.List;

public interface AllegroDataService {
    List<CategoryDto> getCategoriesTree();
    ResponseCategoryCollection getCategoryChildren(@Nullable String categoryId);
}
