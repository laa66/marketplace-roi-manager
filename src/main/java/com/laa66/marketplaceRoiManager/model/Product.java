package com.laa66.marketplaceRoiManager.model;

public record Product(String ean, String currency, String name, double grossPurchasePrice, double shippingPrice,
                      int vatAmount) {
}