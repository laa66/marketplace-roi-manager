package com.laa66.marketplaceRoiManager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryRevenueController {

    @GetMapping("/")
    public String getRevenueCalcForCategory() {
        return "Mocked response";
    }
}
