package com.laa66.marketplaceRoiManager.model.allegro.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ClassifiedsPackages {
    BasePackage basePackage;
    ArrayList<ExtraPackage> extraPackages;
}
