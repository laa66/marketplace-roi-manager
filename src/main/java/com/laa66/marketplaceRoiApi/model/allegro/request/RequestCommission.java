package com.laa66.marketplaceRoiApi.model.allegro.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestCommission {
    Offer offer;
    ClassifiedsPackages classifiedsPackages;
    String marketplaceId;
}
