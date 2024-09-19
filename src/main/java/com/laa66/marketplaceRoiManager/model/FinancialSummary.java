package com.laa66.marketplaceRoiManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialSummary {

    private double netSellPrice;
    private double grossSellPrice;
    private double payVat;
    private double allegroCommission;
    private double incomeTax;
    private double zus;
    private double profit;

}
