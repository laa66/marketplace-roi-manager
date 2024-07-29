package com.laa66.marketplaceRoiManager.controller;

import com.laa66.marketplaceRoiManager.service.AllegroDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CategoryRevenueController {

    private final AllegroDataService allegroDataService;

    @GetMapping("/calculate")
    public void getRevenueCalcForCategory() {
        allegroDataService.calculateOfferFeeAndCommission();
    }
}
