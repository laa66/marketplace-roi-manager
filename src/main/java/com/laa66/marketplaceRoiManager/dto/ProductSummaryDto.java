package com.laa66.marketplaceRoiManager.dto;

import com.laa66.marketplaceRoiManager.model.FinancialSummary;
import com.laa66.marketplaceRoiManager.model.ProductDetails;

import java.util.List;

public record ProductSummaryDto(ProductDetails productDetails,
                                List<FinancialSummary> financialSummaries) {
}
