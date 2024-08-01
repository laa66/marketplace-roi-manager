package com.laa66.marketplaceRoiManager.dto;

import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@NoArgsConstructor(force = true)
public class CategoryCollectionDto {
    List<CategoryDto> categories;
}
