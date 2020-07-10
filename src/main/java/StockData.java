import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class StockData extends HashMap<String, BigDecimal> {

    private String[] symbols;

    public StockData(String[] symbols){
        this.symbols = symbols;
    }

    public void retrieveData(){
        for(String symbol : symbols){
            try {
                Stock stock = YahooFinance.get(symbol);
                if(stock == null) put(symbol, new BigDecimal(0));
                else put(symbol, stock.getQuote().getPrice());
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String[] getSymbols(){
        return symbols;
    }
}
