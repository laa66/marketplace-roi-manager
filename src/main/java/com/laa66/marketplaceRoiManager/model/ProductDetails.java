package com.laa66.marketplaceRoiManager.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetails {

    private String id;
    private String name;
    private String ean;
    private String categoryId;
    private String categoryName;

    private double grossPurchasePrice;
    private double shippingPrice;
    private int vatAmount;

    private String currency;

}
