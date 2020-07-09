import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SymbolReader {

    private static final String FILENAME = "stocks.txt";

    public static String[] readSymbols(){
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

}
