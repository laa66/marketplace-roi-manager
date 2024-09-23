package com.laa66.marketplaceRoiManager.service;

import com.laa66.marketplaceRoiManager.dto.ProductSummaryDto;
import com.laa66.marketplaceRoiManager.model.FinancialSummary;
import com.laa66.marketplaceRoiManager.dto.ProductDto;
import com.laa66.marketplaceRoiManager.model.ProductDetails;

import java.util.Collection;
import java.util.List;

public interface ProductProcessingService {

    default List<ProductSummaryDto> processProductCollection(Collection<ProductDto> productDtoCollection) {
        return productDtoCollection.stream()
                .map(this::processProduct)
                .toList();
    }

    default ProductSummaryDto processProduct(ProductDto productDto) {
        ProductDetails productDetails = createProductDetails(productDto);
        List<FinancialSummary> financialSummaries = createFinancialSummary(productDetails);
        return new ProductSummaryDto(productDetails, financialSummaries);
    }

    ProductDetails createProductDetails(ProductDto productDto);

    List<FinancialSummary> createFinancialSummary(ProductDetails productDetails);

}
