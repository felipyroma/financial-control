package com.example.financialcontrol.service.impl;

import com.example.financialcontrol.service.base.IBankStatementService;
import com.example.financialcontrol.util.Util;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExcelService {
    public void importExcelFile(String filePath, String excelPath, String statement, Map<String, IBankStatementService> services) throws Exception {
        String text = Util.extractTextFromPdf(filePath);
        IBankStatementService bank = services.get(statement);
        Workbook workbook = bank.buildExcelFile(text);
        Util.saveExcelFile(workbook, excelPath);

        System.out.println("Arquivo Excel gerado com sucesso: " + excelPath);
    }

    public void saveData(){

    }
}
