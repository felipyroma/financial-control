package com.financialcontrol.service.base;

import org.apache.poi.ss.usermodel.Workbook;

public interface IBankStatementService {
    public Workbook buildExcelFile(String text);
}
