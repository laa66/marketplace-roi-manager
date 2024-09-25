package com.laa66.marketplaceRoiApi.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CategoryCollectionDto {
    List<CategoryDto> categories;
}
