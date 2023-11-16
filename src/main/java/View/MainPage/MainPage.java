/*
 * Created by JFormDesigner on Thu Nov 16 09:09:24 CST 2023
 */

package View.MainPage;

import javax.swing.*;
import java.awt.*;

/**
 * @author rdjks
 */
public class MainPage extends JPanel {
    public MainPage() {
        initComponents();
        init();
        connectEvent();
    }

    private void connectEvent() {

    }

    private void init() {

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label2 = new JLabel();
        url = new JTextField();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        dataArea = new JTextArea();
        POST = new JButton();
        GET = new JButton();
        PUT = new JButton();
        DELETE = new JButton();
        isSendedByJSON = new JToggleButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {100, 187, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 76, 0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label2 ----
        label2.setText("      Url");
        add(label2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(url, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
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
        add(scrollPane1, new GridBagConstraints(1, 3, 1, 4, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- POST ----
        POST.setText("POST");
        add(POST, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- GET ----
        GET.setText("GET");
        add(GET, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- PUT ----
        PUT.setText("PUT");
        add(PUT, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- DELETE ----
        DELETE.setText("DELETE");
        add(DELETE, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- isSendedByJSON ----
        isSendedByJSON.setText("\u662f\u5426\u4f7f\u7528JSON\u89e3\u6790\u5668");
        add(isSendedByJSON, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label2;
    private JTextField url;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTextArea dataArea;
    private JButton POST;
    private JButton GET;
    private JButton PUT;
    private JButton DELETE;
    private JToggleButton isSendedByJSON;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
