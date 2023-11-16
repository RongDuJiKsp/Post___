package View;

import javax.swing.*;

public class MainWindow extends JFrame {
    private void init() {
        ViewConfig.initUITheme();
        setTitle("PostMTF PortTest");
        setSize(1024, 768);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public MainWindow() {
        init();
    }

}
