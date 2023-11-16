package View;

import javax.swing.*;

public class MainWindow extends JFrame {
    private void init() {
        ViewConfig.initUITheme();
        setTitle("PostMTF PortTest");
        setSize(725, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public MainWindow() {
        init();
    }

}
