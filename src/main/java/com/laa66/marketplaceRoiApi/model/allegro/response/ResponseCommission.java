package com.laa66.marketplaceRoiApi.model.allegro.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResponseCommission {
    private List<CommissionsItem> commissions;
    private List<QuotesItem> quotes;
}
