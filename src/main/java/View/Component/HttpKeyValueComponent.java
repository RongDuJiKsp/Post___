/*
 * Created by JFormDesigner on Fri Nov 17 22:25:10 CST 2023
 */

package View.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rdjks
 */
public class HttpKeyValueComponent extends JPanel {
    private DefaultTableModel paramTableModel;
    private JTable paramTable;

    public HttpKeyValueComponent() {
        initComponents();
        init();
    }

    public HttpKeyValueComponent(Map<String, String> keyValuePairs) {
        this();
        keyValuePairs.forEach((key, value) -> {
            paramTableModel.addRow(new String[]{key, value});
        });
    }

    private void init() {
        //init table
        paramTableModel = new DefaultTableModel(new String[]{"Key", "Value"}, 0);
        paramTable = new JTable(paramTableModel);
        paramTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);


        tableScrollPane.setViewportView(paramTable);
        //init adder
        addParamButton.addActionListener(actionEvent -> {
            if (keyInputholder.getText().isEmpty()) return;
            paramTableModel.addRow(new String[]{keyInputholder.getText(), valueInputholder.getText()});
        });
        //init deleter
        deleteSelectedButton.addActionListener(actionEvent -> {
            int n = paramTable.getSelectedRows().length;
            for (int i = 0; i < n; i++)
                paramTableModel.removeRow(paramTable.getSelectedRow());
        });
        //init clear select
        clearSelectedButtton.addActionListener(actionEvent -> {
            paramTable.clearSelection();
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tableScrollPane = new JScrollPane();
        clearSelectedButtton = new JButton();
        label1 = new JLabel();
        keyInputholder = new JTextField();
        label2 = new JLabel();
        valueInputholder = new JTextField();
        deleteSelectedButton = new JButton();
        addParamButton = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {48, 51, 136, 194, 149, 195, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {22, 134, 40, 33, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
        add(tableScrollPane, new GridBagConstraints(0, 1, 6, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- clearSelectedButtton ----
        clearSelectedButtton.setText("Clear Selected");
        add(clearSelectedButtton, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label1 ----
        label1.setText("            Key:");
        add(label1, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(keyInputholder, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText("           Value:");
        add(label2, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(valueInputholder, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- deleteSelectedButton ----
        deleteSelectedButton.setText("Delect Selected");
        add(deleteSelectedButton, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));

        //---- addParamButton ----
        addParamButton.setText("Add Key_ValuePair");
        add(addParamButton, new GridBagConstraints(2, 3, 4, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public void setEditable(boolean flag) {
        clearSelectedButtton.setVisible(flag);
        label1.setVisible(flag);
        label2.setVisible(flag);
        keyInputholder.setVisible(flag);
        valueInputholder.setVisible(flag);
        deleteSelectedButton.setVisible(flag);
        addParamButton.setVisible(flag);
        if (!flag) {
            paramTable = new JTable(paramTableModel) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tableScrollPane.setViewportView(paramTable);
        }
    }

    public Map<String, String> getKeyValueData() {
        HashMap<String, String> kv = new HashMap<>();
        for (int i = 0; i < paramTableModel.getRowCount(); i++) {
            kv.put((String) paramTableModel.getValueAt(i, 0), (String) paramTableModel.getValueAt(i, 1));
        }
        return kv;
    }

    public DefaultTableModel getTableModel() {
        return paramTableModel;
    }
    public void clear(){
        paramTableModel.getDataVector().clear();
    }

    public void addKeyValue(Map<String, String> kvp) {
        kvp.forEach((k, v) -> {
            paramTableModel.addRow(new String[]{k, v});
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane tableScrollPane;
    private JButton clearSelectedButtton;
    private JLabel label1;
    private JTextField keyInputholder;
    private JLabel label2;
    private JTextField valueInputholder;
    private JButton deleteSelectedButton;
    private JButton addParamButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


}
