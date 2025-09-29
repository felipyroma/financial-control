package com.example.financialcontrol.service.impl;

import com.example.financialcontrol.service.base.StatementService;
import com.example.financialcontrol.util.Util;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("itau")
public class ItauStatementService extends StatementService {

    String regexDate = ".*?(\\b\\d{2}/\\d{2}/\\d{4}\\b).*";
    String regexValue = ".*?(-?\\d{1,3}(?:\\.\\d{3})*,\\d{2}).*";
    String regexText = "\\r?\\n";

    @Override
    public Workbook buildExcelFile(String text) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("pdf-import");

        try {
            String[] lines = text.split(regexText);
            int rowIndex = 0;
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];

                int headerEndLine = 7;
                if (line.equalsIgnoreCase("aviso!")) {
                    return workbook;
                }
                if (i >= headerEndLine && !line.contains("SALDO")) {
                    Row row = sheet.createRow(rowIndex);
                    separateFields(line, row, workbook);
                    rowIndex++;
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return workbook;
    }


    private void separateFields(String line, Row row, Workbook workbook) {
        String date = line.replaceAll(regexDate, "$1");
        String value = line.replaceAll(regexValue, "$1");
        String description = line
                .replaceAll("\\b\\d{2}/\\d{2}/\\d{4}\\b", "")  // remove date
                .replaceAll("-?\\d{1,3}(?:\\.\\d{3})*,\\d{2}", "") // remove value
                .trim();

        try {
            Double valueFormatted = Util.convertToDouble(value);
            Date dateFormatted = Util.formatDate(date);
            buildSheet(dateFormatted, description, valueFormatted, row, workbook);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
