package com.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    public static ByteArrayOutputStream listToExcel(List<Map<String, Object>> dataList, String file) throws IOException {
        Workbook workbook;
        String fileExt = file.substring(file.lastIndexOf(".")).toLowerCase();
        if (fileExt.equals(".xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (fileExt.equals(".xls")) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = null;
        }

        if (workbook == null) {
            return null;
        }
        Sheet sheet = workbook.createSheet("Sheet1");

        if (!dataList.isEmpty()) {
            Map<String, Object> firstRow = dataList.get(0);
            int colNum = 0;
            Row headerRow = sheet.createRow(0);
            for (String key : firstRow.keySet()) {
                Cell cell = headerRow.createCell(colNum++);
                cell.setCellValue(key);
            }

            int rowNum = 1;
            for (Map<String, Object> rowData : dataList) {
                Row dataRow = sheet.createRow(rowNum++);
                colNum = 0;
                for (Object value : rowData.values()) {
                    Cell cell = dataRow.createCell(colNum++);
                    if (value != null) {
                        cell.setCellValue(value.toString());
                    }
                }
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return outputStream;
    }

    public static void exportByWeb(List<Map<String, Object>> dataList, String strFileName, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(strFileName, StandardCharsets.UTF_8.name()));

        try (OutputStream outputStream = response.getOutputStream()) {
            ByteArrayOutputStream excelStream = listToExcel(dataList, strFileName);
            if (excelStream != null) {
                outputStream.write(excelStream.toByteArray());
            }
        }
    }

    public static String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        cell.setCellType(CellType.STRING); // Ensure it's treated as a string
        return cell.getStringCellValue().trim();
    }

    public static double getNumericCellValue(Cell cell) {
        if (cell == null) {
            return 0.0;
        }
        cell.setCellType(CellType.NUMERIC); // Ensure it's treated as a numeric value
        return cell.getNumericCellValue();
    }
}
