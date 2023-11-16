/*
 * Created by JFormDesigner on Thu Nov 16 09:09:24 CST 2023
 */

package View.MainPage;

import Controller.WebPortSys.HttpRequestCustomer;
import View.Dialog.ExceptionDialog.ExceptionDialog;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

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
        postButton.addActionListener((actionEvent) -> {
            try {
                HttpResponse httpResponse = httpRequestCustomer.sendPostRequest(url.getText(), dataArea.getText(), RequestConfig.custom().setConnectionRequestTimeout(500).setConnectTimeout(500).build(), isSendedByJSON.isSelected());
                StringBuilder stringBuilder = new StringBuilder();
                Scanner scanner = new Scanner(httpResponse.getEntity().getContent());
                while (scanner.hasNextLine()) stringBuilder.append(scanner.nextLine());
                resArea.setText(stringBuilder.toString());
            } catch (Exception exception) {
                new ExceptionDialog(mainWindow).showMessage(exception.getMessage());
            }
        });
    }

    private void init() {
        resArea.setEditable(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label2 = new JLabel();
        url = new JTextField();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        dataArea = new JTextArea();
        postButton = new JButton();
        getButton = new JButton();
        putButton = new JButton();
        deleteButton = new JButton();
        isSendedByJSON = new JToggleButton();
        label3 = new JLabel();
        scrollPane2 = new JScrollPane();
        resArea = new JTextArea();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {100, 137, 182, 126, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 76, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label2 ----
        label2.setText("      Url");
        add(label2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(url, new GridBagConstraints(1, 2, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label1 ----
        label1.setText("  data area");
        add(label1, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(dataArea);
        }
        add(scrollPane1, new GridBagConstraints(1, 3, 3, 4, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- postButton ----
        postButton.setText("POST");
        add(postButton, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- getButton ----
        getButton.setText("GET");
        add(getButton, new GridBagConstraints(4, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- putButton ----
        putButton.setText("PUT");
        add(putButton, new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- deleteButton ----
        deleteButton.setText("DELETE");
        add(deleteButton, new GridBagConstraints(4, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- isSendedByJSON ----
        isSendedByJSON.setText("\u662f\u5426\u4f7f\u7528JSON\u89e3\u6790\u5668");
        add(isSendedByJSON, new GridBagConstraints(3, 7, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label3 ----
        label3.setText("    result");
        add(label3, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(resArea);
        }
        add(scrollPane2, new GridBagConstraints(1, 8, 3, 4, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label2;
    private JTextField url;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTextArea dataArea;
    private JButton postButton;
    private JButton getButton;
    private JButton putButton;
    private JButton deleteButton;
    private JToggleButton isSendedByJSON;
    private JLabel label3;
    private JScrollPane scrollPane2;
    private JTextArea resArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
