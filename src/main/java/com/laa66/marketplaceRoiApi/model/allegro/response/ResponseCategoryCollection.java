package com.laa66.marketplaceRoiApi.model.allegro.response;

import com.laa66.marketplaceRoiApi.dto.CategoryDto;

import java.util.List;

public record ResponseCategoryCollection(List<CategoryDto> categories) {
}
