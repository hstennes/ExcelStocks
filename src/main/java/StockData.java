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
                put(symbol, YahooFinance.get(symbol).getQuote().getPrice());
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
