package com.laa66.marketplaceRoiManager.model.allegro.response;

import lombok.Data;

@Data
public class QuotesItem{
	private String cycleDuration;
	private Fee fee;
	private String name;
	private String type;
	private ClassifiedsPackage classifiedsPackage;
}