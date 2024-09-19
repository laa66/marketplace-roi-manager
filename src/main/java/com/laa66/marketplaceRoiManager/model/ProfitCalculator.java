package com.laa66.marketplaceRoiManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
        financialSummary.setGrossSellPrice(financialSummary.getNetSellPrice() * .01 * productDetails.getVatThreshold()
                + productDetails.getShippingPrice());
        return this;
    }

    public ProfitCalculator calculatePayVat() {
        double vat = .01 * productDetails.getVatThreshold();
        financialSummary.setPayVat((vat * financialSummary.getNetSellPrice()) * (vat * productDetails.getNetPurchasePrice()));
        return this;
    }

    public ProfitCalculator addAllegroCommission(double allegroCommission) {
        this.financialSummary.setAllegroCommission(allegroCommission);
        return this;
    }

    public ProfitCalculator calculateIncomeTaxAndZus() {
        double purchaseSellDifference = financialSummary.getNetSellPrice() - financialSummary.getAllegroCommission() - productDetails.getNetPurchasePrice();
        financialSummary.setIncomeTax(incomeTaxThreshold * purchaseSellDifference);
        financialSummary.setZus(zusThreshold * purchaseSellDifference);
        return this;
    }

    public ProfitCalculator calculateProfit() {
        double grossPurchaseSellDiff = financialSummary.getGrossSellPrice() - productDetails.getGrossPurchasePrice();
        financialSummary.setProfit(grossPurchaseSellDiff
                - financialSummary.getAllegroCommission()
                - financialSummary.getPayVat()
                - financialSummary.getZus()
                - financialSummary.getIncomeTax());
        return this;
    }


}
