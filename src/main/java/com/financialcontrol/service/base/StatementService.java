package com.financialcontrol.service.base;

import org.apache.poi.ss.usermodel.*;

import java.util.Date;

public abstract class StatementService implements IBankStatementService {
    public void buildSheet(Date date, String description, Double value, Row row, Workbook workbook){
        CellStyle dateStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

        Cell dateCell = row.createCell(0);
        Cell descriptionCell = row.createCell(1);
        Cell valueCell = row.createCell(2);

        dateCell.setCellValue(date);
        dateCell.setCellStyle(dateStyle);
        descriptionCell.setCellValue(description);
        valueCell.setCellValue(value);
    }
}
