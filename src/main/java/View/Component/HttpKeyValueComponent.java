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
    private DefaultTableModel keyValueTableModel;
    private JTable keyValueTable;

    public HttpKeyValueComponent() {
        init();
    }

    public HttpKeyValueComponent(Map<String, String> keyValuePairs) {
        this();
        addKeyValue(keyValuePairs);
    }

    private void init() {
        initComponents();
        initAction();
    }

    private void initAction() {
        //init table
        keyValueTableModel = new DefaultTableModel(new String[]{"Key", "Value"}, 0);
        keyValueTable = new JTable(keyValueTableModel);
        keyValueTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        tableScrollPane.setViewportView(keyValueTable);
        //init adder
        addParamButton.addActionListener(actionEvent -> onAddKeyValue());
        //init deleter
        deleteSelectedButton.addActionListener(actionEvent -> onDeleteKeyValue());
        //init clear select
        clearSelectedButton.addActionListener(actionEvent -> onClearSelected());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tableScrollPane = new JScrollPane();
        clearSelectedButton = new JButton();
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

        //---- clearSelectedButton ----
        clearSelectedButton.setText("Clear Selected");
        add(clearSelectedButton, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0,
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
        deleteSelectedButton.setText("Delete Selected");
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

    private void onAddKeyValue() {
        if (keyInputholder.getText().isEmpty()) return;
        keyValueTableModel.addRow(new String[]{keyInputholder.getText(), valueInputholder.getText()});
    }

    private void onDeleteKeyValue() {
        int n = keyValueTable.getSelectedRows().length;
        for (int i = 0; i < n; i++)
            keyValueTableModel.removeRow(keyValueTable.getSelectedRow());
    }

    private void onClearSelected() {
        keyValueTable.clearSelection();
    }

    public void setEditable(boolean flag) {
        clearSelectedButton.setVisible(flag);
        label1.setVisible(flag);
        label2.setVisible(flag);
        keyInputholder.setVisible(flag);
        valueInputholder.setVisible(flag);
        deleteSelectedButton.setVisible(flag);
        addParamButton.setVisible(flag);
        if (!flag) {
            keyValueTable = new JTable(keyValueTableModel) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tableScrollPane.setViewportView(keyValueTable);
        }
    }


    public void addKeyValue(Map<String, String> kvp) {
        kvp.forEach((k, v) -> keyValueTableModel.addRow(new String[]{k, v}));
    }

    public void clear() {
        keyValueTableModel.getDataVector().clear();
    }

    public Map<String, String> getKeyValueData() {
        HashMap<String, String> kv = new HashMap<>();
        for (int i = 0; i < keyValueTableModel.getRowCount(); i++) {
            kv.put((String) keyValueTableModel.getValueAt(i, 0), (String) keyValueTableModel.getValueAt(i, 1));
        }
        return kv;
    }

    public DefaultTableModel getTableModel() {
        return keyValueTableModel;
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane tableScrollPane;
    private JButton clearSelectedButton;
    private JLabel label1;
    private JTextField keyInputholder;
    private JLabel label2;
    private JTextField valueInputholder;
    private JButton deleteSelectedButton;
    private JButton addParamButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


}
