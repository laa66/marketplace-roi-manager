package com.laa66.marketplaceRoiManager.model.allegro.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetPrice {
    public String amount;
    public String currency;
}
