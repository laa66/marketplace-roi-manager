package com.laa66.marketplaceRoiApi.model.allegro.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResponseProducts {

    private List<Product> products;

}
