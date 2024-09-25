package com.laa66.marketplaceRoiApi.model;

import lombok.Builder;
import lombok.Data;

import java.util.Currency;

@Data
@Builder
public class ProductDetails {

    private String id;
    private String name;
    private String ean;
    private String categoryId;
    private String categoryName;

    private double netPurchasePrice;
    private double shippingPrice;
    private int vatThreshold;
    private double vatAmount;
    private double grossPurchasePrice;

    private Currency currency;

}
