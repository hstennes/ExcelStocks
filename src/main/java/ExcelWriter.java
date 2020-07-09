import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
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
            Workbook workbook = WorkbookFactory.create(new File(filePath));
            CreationHelper creationHelper = workbook.getCreationHelper();
            Sheet sheet = workbook.getSheet("Sheet1");
            Iterator<Row> rowIterator = sheet.rowIterator();

            String[] arr = data.keySet().toArray(new String[0]);
            for(String str : arr) {
                Row row = rowIterator.next();
                row.createCell(row.getLastCellNum()).setCellValue(data.get(str).doubleValue());
            }

            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                if(cellIterator.hasNext()) {
                    if(DateUtil.isCellDateFormatted(cellIterator.next())) {
                        Cell cell = row.createCell(row.getLastCellNum());
                        cell.setCellValue(new Date());
                        CellStyle dateStyle = workbook.createCellStyle();
                        dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yyyy"));
                        cell.setCellStyle(dateStyle);
                    }
                }
            }

            FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

}
