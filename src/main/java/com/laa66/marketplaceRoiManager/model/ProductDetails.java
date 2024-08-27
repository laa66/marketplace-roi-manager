package com.laa66.marketplaceRoiManager.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetails {

    private String id;
    private String name;
    private String ean;
    private String category;

    private double price;
    private String currency;
    private double allegroCommission;

}
