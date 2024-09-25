package com.laa66.marketplaceRoiApi.dto;

import com.laa66.marketplaceRoiApi.model.FinancialSummary;
import com.laa66.marketplaceRoiApi.model.ProductDetails;

import java.util.List;

public record ProductSummaryDto(ProductDetails productDetails,
                                List<FinancialSummary> financialSummaries) {
}
