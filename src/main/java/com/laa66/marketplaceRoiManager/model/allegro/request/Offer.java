package com.laa66.marketplaceRoiManager.model.allegro.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Offer {
    public String id;
    public Category category;
    public SellingMode sellingMode;
    public Publication publication;
    public Parameter[] parameters;
}
