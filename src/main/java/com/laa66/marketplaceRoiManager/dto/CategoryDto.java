package com.laa66.marketplaceRoiManager.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
@NoArgsConstructor(force = true)
public class CategoryDto {

    String id;

    @JsonProperty(value = "leaf")
    boolean isLeaf;

    String name;

    CategoryDto parent;
}
