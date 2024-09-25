package com.laa66.marketplaceRoiApi.dto;

public record ProductDto(String ean,
                         String currency,
                         String name,
                         double netPurchasePrice,
                         double shippingPrice,
                         Integer vatThreshold) {
}