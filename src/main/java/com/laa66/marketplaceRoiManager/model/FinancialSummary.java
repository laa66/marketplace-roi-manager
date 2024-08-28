package com.laa66.marketplaceRoiManager.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FinancialSummary {

    double sellPrice;
    double payVat;
    double allegroCommission;
    double incomeTax;
    double zus;
    double profit;

}
