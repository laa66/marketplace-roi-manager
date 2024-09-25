package com.laa66.marketplaceRoiApi.model.allegro.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SellingMode {
    public String format;
    public Price price;
    public NetPrice netPrice;
}
