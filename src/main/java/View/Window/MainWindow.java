package View.Window;

import View.Page.MainPage;
import View.ViewConfig;
import lombok.Getter;

import javax.swing.*;

@Getter
public class MainWindow extends JFrame {
    private MainPage mainPage;

    private void init() {
        setTitle("PostMTF PortTest");
        ViewConfig.initUITheme();
        setSize(1024, 768);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainPage = new MainPage(this);
        add(mainPage);
        setVisible(true);
    }

    public MainWindow() {
        init();
    }

}
