/*
 * Created by JFormDesigner on Fri Nov 17 22:25:10 CST 2023
 */

package View.Component;

import org.apache.http.client.utils.URIBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author rdjks
 */
public class HttpParamsComponent extends JPanel {
    public HttpParamsComponent() {
        initComponents();
        init();
    }

    private void init() {
        //init table
        paramTableModel = new DefaultTableModel(new String[]{"Key", "Value"}, 0);
        JTable jTable = new JTable(paramTableModel);
        jTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);


        tableScrollPane.setViewportView(jTable);
        //init adder
        addParamButton.addActionListener(actionEvent -> {
            if (keyInputholder.getText().isEmpty()) return;
            paramTableModel.addRow(new String[]{keyInputholder.getText(), valueInputholder.getText()});
        });
        //init deleter
        deleteSelectedButton.addActionListener(actionEvent -> {
            int n = jTable.getSelectedRows().length;
            for (int i = 0; i < n; i++)
                paramTableModel.removeRow(jTable.getSelectedRow());
        });
        //init clear select
        clearSelectedButtton.addActionListener(actionEvent -> {
            jTable.clearSelection();
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tableScrollPane = new JScrollPane();
        addParamButton = new JButton();
        label1 = new JLabel();
        keyInputholder = new JTextField();
        label2 = new JLabel();
        valueInputholder = new JTextField();
        deleteSelectedButton = new JButton();
        clearSelectedButtton = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {48, 51, 136, 194, 149, 195, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {22, 36, 231, 40, 33, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        add(tableScrollPane, new GridBagConstraints(0, 1, 6, 2, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- addParamButton ----
        addParamButton.setText("Add Param");
        add(addParamButton, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label1 ----
        label1.setText("            Key:");
        add(label1, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(keyInputholder, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText("           Value:");
        add(label2, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(valueInputholder, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- deleteSelectedButton ----
        deleteSelectedButton.setText("Delect Selected");
        add(deleteSelectedButton, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));

        //---- clearSelectedButtton ----
        clearSelectedButtton.setText("Clear Selected");
        add(clearSelectedButtton, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public URI addParamsToUrI(URI uri) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(uri);
        for (int i = 0; i < paramTableModel.getRowCount(); i++) {
            uriBuilder.addParameter((String) paramTableModel.getValueAt(i, 0), (String) paramTableModel.getValueAt(i, 1));
        }
        return uriBuilder.build();
    }

    public URI addParamsToUrI(String uri) throws URISyntaxException {
        return addParamsToUrI(new URI(uri));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane tableScrollPane;
    private JButton addParamButton;
    private JLabel label1;
    private JTextField keyInputholder;
    private JLabel label2;
    private JTextField valueInputholder;
    private JButton deleteSelectedButton;
    private JButton clearSelectedButtton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private DefaultTableModel paramTableModel;

}
