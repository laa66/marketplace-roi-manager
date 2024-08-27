package com.laa66.marketplaceRoiManager.service;

import com.laa66.marketplaceRoiManager.model.ProductDetails;

public interface ProductProcessingService {

    ProductDetails buildProductDetails(String ean, String price, String currency);


}
