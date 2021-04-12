package com.harshal.tempReport.utility;

import com.harshal.tempReport.model.ToDo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {
    public static ByteArrayInputStream dataToExcel(List<ToDo> toDoList) throws IOException {

        String columns[] = {"ToDo Name", "Description"};
        try(Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            CreationHelper creationHelper = workbook.getCreationHelper();
            Sheet sheet = workbook.createSheet("Report1");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            //Row for header
            Row headerRow = sheet.createRow(0);

            //Header
            for(int col = 0; col<columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowInx = 1;
            for (ToDo toDo : toDoList) {
                Row row = sheet.createRow(rowInx++);
                row.createCell(0).setCellValue(toDo.getName());
                row.createCell(1).setCellValue(toDo.getDescripton());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
