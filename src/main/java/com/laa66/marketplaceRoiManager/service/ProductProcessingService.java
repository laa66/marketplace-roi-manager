package com.laa66.marketplaceRoiManager.service;

import com.laa66.marketplaceRoiManager.dto.ProductSummaryDto;
import com.laa66.marketplaceRoiManager.model.FinancialSummary;
import com.laa66.marketplaceRoiManager.model.Product;
import com.laa66.marketplaceRoiManager.model.ProductDetails;

import java.util.List;

public interface ProductProcessingService {

    default ProductSummaryDto processProduct(Product product) {
        ProductDetails productDetails = createProductDetails(product);
        List<FinancialSummary> financialSummaries = createFinancialSummary(productDetails);
        return new ProductSummaryDto(productDetails, financialSummaries);
    }

    ProductDetails createProductDetails(Product product);

    List<FinancialSummary> createFinancialSummary(ProductDetails productDetails);

}
