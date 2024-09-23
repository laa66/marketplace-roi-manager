package com.laa66.marketplaceRoiManager.service.impl;

import com.laa66.marketplaceRoiManager.model.FinancialSummary;
import com.laa66.marketplaceRoiManager.dto.ProductDto;
import com.laa66.marketplaceRoiManager.model.ProductDetails;
import com.laa66.marketplaceRoiManager.model.SoleProprietorshipProfitCalculator;
import com.laa66.marketplaceRoiManager.model.allegro.response.Product;
import com.laa66.marketplaceRoiManager.service.AllegroDataService;
import com.laa66.marketplaceRoiManager.service.ProductProcessingService;
import lombok.AllArgsConstructor;

import java.util.Currency;
import java.util.List;
import java.util.stream.Stream;

/*
TODO extract calculation functionality to outer plain java class,
 place it inside service package
 refactor model naming, ProductDto will be used later inside incoming requests
*/
@AllArgsConstructor
public class ProductProcessingServiceImpl implements ProductProcessingService {

    final private static int DEFAULT_VAT_PL = 23;

    private final AllegroDataService allegroDataService;

    @Override
    public ProductDetails createProductDetails(ProductDto productDto) {
        Product product = allegroDataService.searchProducts(productDto.ean());
        return ProductDetails.builder()
                .id(product.getId())
                .name(product.getName())
                .ean(productDto.ean())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .grossPurchasePrice(productDto.grossPurchasePrice())
                .shippingPrice(productDto.shippingPrice())
                .vatAmount(productDto.vatAmount() == null ? DEFAULT_VAT_PL : productDto.vatAmount())
                .currency(Currency.getInstance("PLN"))
                .build();
    }

    @Override
    public List<FinancialSummary> createFinancialSummary(ProductDetails productDetails) {
        return Stream.of(1.1, 1.2, 1.3, 1.4)
                .map(marginThreshold -> {
                    SoleProprietorshipProfitCalculator calculator = new SoleProprietorshipProfitCalculator(
                            marginThreshold,
                            productDetails,
                            new FinancialSummary());
                    calculator.calculateSellPrice()
                            .calculatePayVat()
                            .addAllegroCommission(Double.parseDouble(
                                    allegroDataService.postOfferCommission(productDetails.getName(),
                                                    productDetails.getCategoryId(),
                                                    Double.toString(calculator.getFinancialSummary().getGrossSellPrice()), "PLN")
                                            .getCommissions()
                                            .getFirst()
                                            .getFee()
                                            .getAmount()))
                            .calculateIncomeTaxAndZus()
                            .calculateProfit();
                    return calculator.getFinancialSummary();
                }).toList();
    }
}
