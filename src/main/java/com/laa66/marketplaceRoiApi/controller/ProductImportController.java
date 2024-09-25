package com.laa66.marketplaceRoiApi.controller;

import com.laa66.marketplaceRoiApi.dto.ProductDto;
import com.laa66.marketplaceRoiApi.dto.ProductSummaryDto;
import com.laa66.marketplaceRoiApi.service.ProductProcessingService;
import com.laa66.marketplaceRoiApi.util.CsvProductHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/csv")
@AllArgsConstructor
public class ProductImportController {

    private final ProductProcessingService productProcessingService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductSummaryDto> getProductsSummary(@RequestPart("file") MultipartFile file) {
        List<ProductDto> productDtoList = CsvProductHelper.csvToProductDto(file);
        return productProcessingService.processProductCollection(productDtoList);
    }

}
