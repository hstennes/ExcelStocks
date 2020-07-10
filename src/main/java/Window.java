import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {

    public static final int WIDTH = 300, HEIGHT = 300;

    private String filepath;

    public Window(){
        super("Excel stocks thing");
        setSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        createGUI();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createGUI(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        final JLabel fileLabel = new JLabel("(No file selected)");
        fileLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton chooseVideo = new JButton("Choose file");
        chooseVideo.setAlignmentX(Component.CENTER_ALIGNMENT);
        chooseVideo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                if(fc.showOpenDialog(Window.this) == JFileChooser.APPROVE_OPTION) {
                    filepath = fc.getSelectedFile().getAbsolutePath();
                    fileLabel.setText(filepath);
                }
            }
        });

        final JLabel doneLabel = new JLabel("Done!");
        doneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        doneLabel.setVisible(false);

        final JButton runButton = new JButton("Run");
        runButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] symbols = SymbolReader.readSymbolsFromXlsx(filepath);
                if(symbols == null) {
                    invalidFile();
                    return;
                }
                runButton.setEnabled(false);
                StockData data = new StockData(symbols);
                data.retrieveData();
                new ExcelWriter(filepath).writeToFile(data);
                doneLabel.setVisible(true);
            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(chooseVideo);
        mainPanel.add(fileLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        mainPanel.add(runButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(doneLabel);

        add(mainPanel);
    }

    public void invalidFile(){
        JOptionPane.showMessageDialog(this,
                "Stock data could not be added to the selected file",
                "Invalid file",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
