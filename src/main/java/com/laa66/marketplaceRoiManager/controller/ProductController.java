package com.laa66.marketplaceRoiManager.controller;

import com.laa66.marketplaceRoiManager.dto.ProductSummaryDto;
import com.laa66.marketplaceRoiManager.service.AllegroDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final AllegroDataService allegroDataService;

    @PostMapping("/analyze")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProductSummaryDto getAnalyzedProducts() {
        throw new UnsupportedOperationException();
    }

}
