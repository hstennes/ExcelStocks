
public class ExcelStocks {

    private static final String TEST_PATH = "C:\\Users\\HPste\\Documents\\DAD_STOCK.xlsx";

    public static void main(String[] args){
        String[] symbols = SymbolReader.readSymbolsFromXlsx(TEST_PATH);
        StockData data = new StockData(symbols);
        data.retrieveData();
        new ExcelWriter(TEST_PATH).writeToFile(data);
    }
}
