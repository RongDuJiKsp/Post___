/*
 * Created by JFormDesigner on Fri Dec 29 15:40:37 CST 2023
 */

package View.Window;

import Controller.HistorySaver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author rdjks
 */
public class SaveRecordsWindow extends JDialog {
    File directory;
    HistorySaver model;
    MainWindow owner;

    public SaveRecordsWindow(MainWindow owner, HistorySaver model) {
        super(owner);
        this.owner = owner;
        initComponents();
        initTable(model);
        initSettings();
        initAction();
    }

    private void initTable(HistorySaver model) {
        historyTable.setModel(model.getDataModel());
        this.model = model;
    }

    private void initSettings() {
        chosenFolderPanel.setEditable(false);
    }

    private void initAction() {
        selectFolderButton.addActionListener(actionEvent -> onChoseFolder());
        okButton.addActionListener(actionEvent -> onDownload());
        reloadButton.addActionListener(actionEvent -> onReload());
    }

    private void onChoseFolder() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.showSaveDialog(this);
        directory = jFileChooser.getSelectedFile();
        if (directory == null) return;
        chosenFolderPanel.setText(directory.getPath());
    }

    private void onDownload() {
        new Thread(() -> {
            try {
                if (directory == null) return;
                for (int index : historyTable.getSelectedRows()) {
                    File tmpfile = model.getChosenData(index);
                    File saveFile = new File(directory.toPath() + "\\" + tmpfile.getName());
                    Files.copy(tmpfile.toPath(), saveFile.toPath());
                }
                dispose();
            } catch (IOException e) {
                new ExceptionDialog(this, e.toString());
            }
        }).start();
        setVisible(false);
    }

    private void onReload() {
        if (historyTable.getSelectedRows().length > 0) {
            int index=(historyTable.getSelectedRows()[0]);
            owner.getMainPage().updateDataWithHistory(model.getHistoryData().get(index));
        }
        dispose();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        chosenFolderPanel = new JTextField();
        selectFolderButton = new JButton();
        scrollPane1 = new JScrollPane();
        historyTable = new JTable();
        buttonBar = new JPanel();
        reloadButton = new JButton();
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
                ((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 0, 26, 23, 371, 29, 0, 30, 0};
                ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
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
                    scrollPane1.setViewportView(historyTable);
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

                //---- reloadButton ----
                reloadButton.setText("Reload");
                buttonBar.add(reloadButton, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

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
    private JTable historyTable;
    private JPanel buttonBar;
    private JButton reloadButton;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
