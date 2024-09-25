package com.laa66.marketplaceRoiApi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class ProfitCalculatorUnitTest {

    ProfitCalculator calculator;

    @BeforeEach
    void setup() {
        ProductDetails productDetails = new ProductDetails(
                "1",
                "prod1",
                "11",
                "cat1",
                "catName1",
                30.,
                5.,
                23,
                0.,
                0.,
                Currency.getInstance("PLN")
        );
        FinancialSummary financialSummary = new FinancialSummary();
        calculator = new SoleProprietorshipProfitCalculator(1.1, productDetails, financialSummary);
    }

    @Test
    void shouldCalculateTotalPurchasePrice() {
        ProfitCalculator profitCalculator = calculator.calculateTotalPurchasePrice();
        double grossPurchasePrice = profitCalculator.getProductDetails()
                .getGrossPurchasePrice();
        assertEquals(41.9, grossPurchasePrice);
    }

    @Test
    void shouldCalculateSellPrice() {
        ProfitCalculator profitCalculator = calculator.calculateSellPrice();
        double netSellPrice = profitCalculator.getFinancialSummary().getNetSellPrice();
        double grossSellPrice = profitCalculator.getFinancialSummary().getGrossSellPrice();
        assertEquals(33, netSellPrice);
        assertEquals(45.59, grossSellPrice);
    }

    @Test
    void shouldCalculatePayVat() {
        ProfitCalculator profitCalculator = calculator.calculateTotalPurchasePrice()
                .calculateSellPrice()
                .calculatePayVat();

        double payVat = profitCalculator.getFinancialSummary().getPayVat();
        assertEquals(0.69, payVat);
    }

    @Test
    void shouldAddAllegroCommission() {
        ProfitCalculator profitCalculator = calculator.addAllegroCommission(4.00);
        assertEquals(4.00, profitCalculator.getFinancialSummary().getAllegroCommission());
    }

    @Test
    void shouldCalculateIncomeTaxAndZus() {
        ProfitCalculator profitCalculator = calculator.calculateTotalPurchasePrice()
                .calculateSellPrice()
                .calculatePayVat()
                .addAllegroCommission(4.00)
                .calculateIncomeTaxAndZus();
        assertEquals(0.12, profitCalculator.getFinancialSummary().getIncomeTax());
        assertEquals(0.09, profitCalculator.getFinancialSummary().getZus());
    }

    @Test
    void shouldCalculateProfit() {
        ProfitCalculator profitCalculator = calculator.calculateTotalPurchasePrice()
                .calculateSellPrice()
                .calculatePayVat()
                .addAllegroCommission(4.00)
                .calculateIncomeTaxAndZus()
                .calculateProfit();
        assertEquals(-1.21, profitCalculator.getFinancialSummary().getProfit());
    }

}