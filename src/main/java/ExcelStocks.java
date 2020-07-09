
public class ExcelStocks {

    public static void main(String[] args){
        String[] symbols = SymbolReader.readSymbols();
        StockData data = new StockData(symbols);
        data.retrieveData();
        for(String symbol : symbols){
            System.out.println(symbol + ": " + data.get(symbol));
        }
    }
}
