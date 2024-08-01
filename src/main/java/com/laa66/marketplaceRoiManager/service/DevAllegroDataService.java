package com.laa66.marketplaceRoiManager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laa66.marketplaceRoiManager.dto.CategoryCollectionDto;
import com.laa66.marketplaceRoiManager.dto.CategoryDto;
import com.laa66.marketplaceRoiManager.model.response.ResponseCategoryCollection;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

@AllArgsConstructor
public class DevAllegroDataService implements AllegroDataService {

    private static final Logger log = LoggerFactory.getLogger(DevAllegroDataService.class);

    @Override
    public List<CategoryDto> getCategoriesTree() {
        File file = new File("src/main/resources/categories.json");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ObjectMapper mapper = new ObjectMapper();
            CategoryCollectionDto categoryCollectionDto = mapper.readValue(reader, CategoryCollectionDto.class);
            return categoryCollectionDto.getCategories();
        } catch (IOException e) {
            log.info("Cannot read categories.json file");
            throw new RuntimeException(e);
        }
    }

    /**
     * Do not use in development mode.
     * @return UnsupportedOperationException
     */
    @Override
    @Deprecated
    public ResponseCategoryCollection getCategoryChildren(String categoryId) {
        throw new UnsupportedOperationException("Method cannot be used in development mode.");
    }
}
