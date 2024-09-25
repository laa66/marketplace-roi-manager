package com.laa66.marketplaceRoiApi.util;

import com.laa66.marketplaceRoiApi.dto.ProductDto;
import com.laa66.marketplaceRoiApi.exception.CsvMappingException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.List;

public class CsvProductHelper {

    public static String TYPE = "text/csv";

    public static boolean hasCsvFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<ProductDto> csvToProductDto(MultipartFile file) {
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

    private static List<CSVRecord> parseCsv(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            return CSVFormat.EXCEL.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build()
                    .parse(reader)
                    .getRecords();
        } catch (IOException | UncheckedIOException e) {
            throw new CsvMappingException(e.getMessage(), e);
        }
    }

}
