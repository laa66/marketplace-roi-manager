package com.laa66.marketplaceRoiManager.model.allegro.response;

import com.laa66.marketplaceRoiManager.dto.CategoryDto;

import java.util.List;

public record ResponseCategoryCollection(List<CategoryDto> categories) {
}
