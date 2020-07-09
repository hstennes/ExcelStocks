
public class ExcelStocks {

    private static final String TEST_PATH = "C:\\Users\\HPste\\Documents\\DAD_STOCK.xlsx";

    public static void main(String[] args){
        /*
        String[] symbols = SymbolReader.readSymbols();
        StockData data = new StockData(symbols);
        data.retrieveData();
        for(String symbol : symbols){
            System.out.println(symbol + ": " + data.get(symbol));
        }
         */

        String[] symbols = SymbolReader.readSymbolsFromXlsx(TEST_PATH);
        for(String str : symbols) System.out.println(str);
        StockData data = new StockData(symbols);
        data.retrieveData();
        new ExcelWriter(TEST_PATH).writeToFile(data);

        //new ExcelWriter(TEST_PATH).writeToFile(null);
    }
}
