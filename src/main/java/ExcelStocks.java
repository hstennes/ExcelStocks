import javax.swing.*;

public class ExcelStocks {

    private static final String TEST_PATH = "C:\\Users\\HPste\\Documents\\DAD_STOCK.xlsx";

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }
}
