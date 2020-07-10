import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class SymbolReader {

    public static String[] readSymbolsFromXlsx(String filePath){
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new File(filePath));
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            ArrayList<String> symbols = new ArrayList<String>();

            while(rowIterator.hasNext()){
                Cell cell = rowIterator.next().cellIterator().next();
                if(cell.getCellType() != Cell.CELL_TYPE_NUMERIC) symbols.add(cell.toString());
            }

            String[] arr = new String[symbols.size()];
            for(int i = 0; i < arr.length; i++) arr[i] = symbols.get(i);
            return arr;
        } catch (IOException e) {
            return null;
        } catch (InvalidFormatException e) {
            return null;
        }
    }
}
