/*
 * Created by JFormDesigner on Fri Dec 29 15:40:37 CST 2023
 */

package View.Component;

import Controller.HistorySaver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author rdjks
 */
public class SaveRecordsComponent extends JDialog {
    public SaveRecordsComponent(Window owner, HistorySaver model) {
        super(owner);
        initComponents();
        initTable(model);
    }

    void initTable(HistorySaver model) {
        histroyTable.setModel(model.getDataModel());
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        chosenFolderPanel = new JTextField();
        selectFolderButton = new JButton();
        scrollPane1 = new JScrollPane();
        histroyTable = new JTable();
        buttonBar = new JPanel();
        okButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 101, 99, 90, 83, 150, 0};
                ((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 0, 26, 23, 371, 30, 0};
                ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                contentPanel.add(chosenFolderPanel, new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- selectFolderButton ----
                selectFolderButton.setText("Select Folder");
                contentPanel.add(selectFolderButton, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(histroyTable);
                }
                contentPanel.add(scrollPane1, new GridBagConstraints(1, 3, 5, 2, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
            }
            dialogPane.add(contentPanel, BorderLayout.NORTH);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).rowHeights = new int[] {15, 0};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                buttonBar.add(okButton, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JTextField chosenFolderPanel;
    private JButton selectFolderButton;
    private JScrollPane scrollPane1;
    private JTable histroyTable;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
