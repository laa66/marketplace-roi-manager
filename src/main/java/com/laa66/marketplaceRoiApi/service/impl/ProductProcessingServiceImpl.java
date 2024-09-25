package com.laa66.marketplaceRoiApi.service.impl;

import com.laa66.marketplaceRoiApi.model.FinancialSummary;
import com.laa66.marketplaceRoiApi.dto.ProductDto;
import com.laa66.marketplaceRoiApi.model.ProductDetails;
import com.laa66.marketplaceRoiApi.model.SoleProprietorshipProfitCalculator;
import com.laa66.marketplaceRoiApi.model.allegro.response.Product;
import com.laa66.marketplaceRoiApi.service.AllegroDataService;
import com.laa66.marketplaceRoiApi.service.ProductProcessingService;
import lombok.AllArgsConstructor;

import java.util.Currency;
import java.util.List;
import java.util.stream.Stream;


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
                .vatThreshold(productDto.vatThreshold() == null ? DEFAULT_VAT_PL : productDto.vatThreshold())
                .currency(Currency.getInstance("PLN"))
                .netPurchasePrice(productDto.netPurchasePrice())
                .shippingPrice(productDto.shippingPrice())
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
                    calculator.calculateTotalPurchasePrice()
                            .calculateSellPrice()
                            .calculatePayVat()
                            .addAllegroCommission(Double.parseDouble(
                                    allegroDataService.postOfferCommission(calculator.getProductDetails().getName(),
                                                    productDetails.getCategoryId(),
                                                    Double.toString(calculator.getFinancialSummary().getGrossSellPrice()), "PLN")
                                            .getCommissions()
                                            .stream()
                                            .filter(commissionItem -> commissionItem.getType().equals("commissionFee"))
                                            .toList()
                                            .getFirst()
                                            .getFee()
                                            .getAmount()))
                            .calculateIncomeTaxAndZus()
                            .calculateProfit();
                    return calculator.getFinancialSummary();
                }).toList();
    }
}
