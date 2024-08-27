package com.laa66.marketplaceRoiManager.dto;

import com.laa66.marketplaceRoiManager.model.ProductDetails;

public record ProductSummaryDto(ProductDetails productDetails,
                                String categoryName,
                                double allegroCommission,
                                double profit,
                                boolean profitable) {
}
