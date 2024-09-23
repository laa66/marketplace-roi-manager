package com.laa66.marketplaceRoiManager.controller;

import com.laa66.marketplaceRoiManager.dto.ProductDto;
import com.laa66.marketplaceRoiManager.dto.ProductSummaryDto;
import com.laa66.marketplaceRoiManager.service.ProductProcessingService;
import com.laa66.marketplaceRoiManager.util.CsvProductHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/csv")
@AllArgsConstructor
public class ProductImportController {

    private final ProductProcessingService productProcessingService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductSummaryDto getProductsSummary(@RequestPart("file") MultipartFile file) throws IOException {
        List<ProductDto> productDtos = CsvProductHelper.csvToProductDto(file);
        System.out.println(productDtos);
        return null;
    }

}
