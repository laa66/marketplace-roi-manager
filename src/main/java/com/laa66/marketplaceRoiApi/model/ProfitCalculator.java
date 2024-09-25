package com.laa66.marketplaceRoiApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public abstract class ProfitCalculator {

    // value between 1.1-1.5
    private double marginThreshold;

    // 0.12
    private double incomeTaxThreshold;

    // 0.09
    private double zusThreshold;

    private ProductDetails productDetails;
    private FinancialSummary financialSummary;

    public ProfitCalculator() {

    }

    public ProfitCalculator calculateTotalPurchasePrice() {
        productDetails.setGrossPurchasePrice(productDetails.getNetPurchasePrice()
                + (.01 * productDetails.getVatThreshold() * productDetails.getNetPurchasePrice())
                + productDetails.getShippingPrice());
        return this;
    }

    public ProfitCalculator calculateSellPrice() {
        financialSummary.setNetSellPrice(marginThreshold * productDetails.getNetPurchasePrice());

        financialSummary.setGrossSellPrice(BigDecimal.valueOf(financialSummary.getNetSellPrice())
                .multiply(BigDecimal.valueOf(1 + (0.01 * productDetails.getVatThreshold())))
                .add(BigDecimal.valueOf(productDetails.getShippingPrice()))
                .doubleValue());
        return this;
    }

    public ProfitCalculator calculatePayVat() {
        BigDecimal vat = BigDecimal.valueOf(.01 * productDetails.getVatThreshold());
        financialSummary.setPayVat((vat.multiply(BigDecimal.valueOf(financialSummary.getNetSellPrice())))
                .subtract((vat.multiply(BigDecimal.valueOf(productDetails.getNetPurchasePrice()))))
                .doubleValue());
        return this;
    }

    public ProfitCalculator addAllegroCommission(double allegroCommission) {
        this.financialSummary.setAllegroCommission(allegroCommission);
        return this;
    }

    public ProfitCalculator calculateIncomeTaxAndZus() {
        double purchaseSellDifference = financialSummary.getNetSellPrice() - financialSummary.getAllegroCommission() - productDetails.getNetPurchasePrice();
        financialSummary.setIncomeTax(Math.abs(incomeTaxThreshold * purchaseSellDifference));
        financialSummary.setZus(Math.abs(zusThreshold * purchaseSellDifference));
        return this;
    }

    public ProfitCalculator calculateProfit() {
        BigDecimal grossPurchaseSellDiff = BigDecimal.valueOf(financialSummary.getGrossSellPrice())
                .subtract(BigDecimal.valueOf(productDetails.getGrossPurchasePrice()));
        financialSummary.setProfit(grossPurchaseSellDiff
                .subtract(BigDecimal.valueOf(financialSummary.getAllegroCommission()))
                .subtract(BigDecimal.valueOf(financialSummary.getPayVat()))
                .subtract(BigDecimal.valueOf(financialSummary.getZus()))
                .subtract(BigDecimal.valueOf(financialSummary.getIncomeTax()))
                .doubleValue());
        return this;
    }


}
