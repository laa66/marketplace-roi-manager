package com.laa66.marketplaceRoiManager.controller;

import com.laa66.marketplaceRoiManager.dto.CategoriesDto;
import com.laa66.marketplaceRoiManager.dto.CategoryDto;
import com.laa66.marketplaceRoiManager.service.AllegroDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryRevenueController {

    private final AllegroDataService allegroDataService;

    @GetMapping("/categories")
    @ResponseStatus(code = HttpStatus.OK)
    public CategoriesDto getAllCategories() {
        List<CategoryDto> categoriesTree = allegroDataService.getCategoriesTree();
        System.out.println(categoriesTree);
        return null;
    }
}
