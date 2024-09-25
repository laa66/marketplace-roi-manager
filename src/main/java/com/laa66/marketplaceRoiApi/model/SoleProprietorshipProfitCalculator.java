package com.laa66.marketplaceRoiApi.model;

public class SoleProprietorshipProfitCalculator extends ProfitCalculator {

    public SoleProprietorshipProfitCalculator(double marginThreshold,
                                              ProductDetails productDetails,
                                              FinancialSummary financialSummary) {
        super(marginThreshold, .12, .09, productDetails, financialSummary);
    }
}
