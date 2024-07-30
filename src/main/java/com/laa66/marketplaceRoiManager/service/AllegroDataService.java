package com.laa66.marketplaceRoiManager.service;

import com.laa66.marketplaceRoiManager.dto.CategoryDto;
import com.laa66.marketplaceRoiManager.model.response.ResponseCategories;
import org.springframework.lang.Nullable;

import java.util.Collection;

public interface AllegroDataService {
    Collection<CategoryDto> getCategoriesTree();
    ResponseCategories getCategoryChildren(@Nullable String categoryId);
}
