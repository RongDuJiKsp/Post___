/*
 * Created by JFormDesigner on Thu Nov 16 09:09:24 CST 2023
 */

package View.Page;

import Controller.HttpRequestCustomer;
import View.Component.HttpComponent;
import View.FunctionalComponent.SelectItemComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author rdjks
 */
public class MainPage extends JPanel {
    private final HttpRequestCustomer httpRequestCustomer;
    private final JFrame mainWindow;


    public MainPage(JFrame mainWindow) {
        httpRequestCustomer = new HttpRequestCustomer();
        this.mainWindow = mainWindow;
        initComponents();
        init();
        connectEvent();
    }

    private void connectEvent() {

    }

    private void init() {
        //init choice
        ArrayList<String> protocol = new ArrayList<>(Arrays.asList("Http", "WebSocket", "Socket.IO"));
        protocols = new SelectItemComponent(protocol, "Http", actionEvent -> {
            methods.setVisible(((JMenuItem) actionEvent.getSource()).getText().equals("Http"));
        });
        selectMethodBar.add(protocols);
        ArrayList<String> method = new ArrayList<>(Arrays.asList("Post", "Get"));
        methods = new SelectItemComponent(method, "Post");
        selectMethodBar.add(methods);
        //init Tab
        tabbedPane1.addTab("Params", new HttpComponent());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label2 = new JLabel();
        url = new JTextField();
        label1 = new JLabel();
        selectMethodBar = new JMenuBar();
        tabbedPane1 = new JTabbedPane();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {51, 77, 85, 76, 89, 82, 61, 67, 50, 68, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 67, 0, 38, 187, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label2 ----
        label2.setText("      Url:");
        add(label2, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(url, new GridBagConstraints(2, 3, 6, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label1 ----
        label1.setText("Protrol:");
        add(label1, new GridBagConstraints(9, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(selectMethodBar, new GridBagConstraints(10, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(tabbedPane1, new GridBagConstraints(1, 5, 10, 5, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label2;
    private JTextField url;
    private JLabel label1;
    private JMenuBar selectMethodBar;
    private JTabbedPane tabbedPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private SelectItemComponent protocols, methods;

}
