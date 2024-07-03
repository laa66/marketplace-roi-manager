package com.laa66.marketplaceRoiManager;

import org.springframework.boot.SpringApplication;

public class TestCompanyCalculationApplication {

	public static void main(String[] args) {
		SpringApplication.from(MarketplaceRoiManagerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
