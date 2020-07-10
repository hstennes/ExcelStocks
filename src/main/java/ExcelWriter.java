import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFRow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

public class ExcelWriter {

    private String filePath;

    public ExcelWriter(String filePath){
        this.filePath = filePath;
    }

    public void writeToFile(StockData data){
        try {
            Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath));
            CreationHelper creationHelper = workbook.getCreationHelper();
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();

            int column = 0;
            for(String str : data.getSymbols()) {
                Row row = rowIterator.next();
                column = row.getLastCellNum();
                row.createCell(column).setCellValue(data.get(str).doubleValue());
            }

            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                if(cellIterator.hasNext() && cellIterator.next().getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    Cell cell = row.createCell(column);
                    cell.setCellValue(new Date());
                    CellStyle dateStyle = workbook.createCellStyle();
                    dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yyyy"));
                    cell.setCellStyle(dateStyle);
                    break;
                }
            }

            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
