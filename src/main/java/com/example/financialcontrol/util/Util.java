package com.example.financialcontrol.util;

import com.example.financialcontrol.service.base.IBankStatementService;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class Util {

    public static String extractTextFromPdf(String filePath) throws Exception {
        try (PDDocument document = Loader.loadPDF(new File(filePath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        }
    }

    public static void saveExcelFile(Workbook workbook, String excelPath) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(excelPath)) {
            workbook.write(fos);
        }
        workbook.close();
    }

    public static Date formatDate(String date) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(date);
    }

    public static Double convertToDouble(String value) throws Exception {
        String valueFormatted = value.replaceAll("\\.", "").replace(",", ".");
        return Double.parseDouble(valueFormatted);
    }
}
