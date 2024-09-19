package com.laa66.marketplaceRoiManager.dto;

public record ProductDto(String ean,
                         String currency,
                         String name,
                         double grossPurchasePrice,
                         double shippingPrice,
                         Integer vatAmount) {
}