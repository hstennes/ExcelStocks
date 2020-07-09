import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class SymbolReader {

    private static final String FILENAME = "stocks.txt";

    public static String[] readSymbolsFromTxt(){
        try {
            File file = new File(FILENAME);
            Scanner myReader = new Scanner(file);
            String[] split = myReader.nextLine().split(",");
            String[] trim = new String[split.length];
            for(int i = 0; i < trim.length; i++) trim[i] = split[i].trim();
            return trim;
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public static String[] readSymbolsFromXlsx(String filePath){
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new File(filePath));
            Sheet sheet = workbook.getSheet("Sheet1");
            Iterator<Row> rowIterator = sheet.rowIterator();
            ArrayList<String> symbols = new ArrayList<String>();

            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                String cellString = row.cellIterator().next().toString();
                symbols.add(cellString);
            }

            String[] arr = new String[symbols.size() - 1];
            for(int i = 0; i < arr.length; i++) arr[i] = symbols.get(i);
            return arr;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
