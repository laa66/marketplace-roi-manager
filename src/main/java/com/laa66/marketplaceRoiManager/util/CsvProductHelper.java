package com.laa66.marketplaceRoiManager.util;

import com.laa66.marketplaceRoiManager.dto.ProductDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CsvProductHelper {

    public static String TYPE = "text/csv";

    public static boolean hasCsvFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<ProductDto> csvToProductDto(MultipartFile file) throws IOException {
        return parseCsv(file).stream()
                .map(csvRecord -> new ProductDto(
                        csvRecord.get("ean"),
                        csvRecord.get("currency"),
                        csvRecord.get("name"),
                        Double.parseDouble(csvRecord.get("grossPurchasePrice")),
                        Double.parseDouble(csvRecord.get("shippingPrice")),
                        Integer.parseInt(csvRecord.get("vatAmount"))
                )).toList();
    }

    private static List<CSVRecord> parseCsv(MultipartFile file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            return CSVFormat.EXCEL.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build()
                    .parse(reader)
                    .getRecords();
        }
    }

}
