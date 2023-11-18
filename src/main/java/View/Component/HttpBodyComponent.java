/*
 * Created by JFormDesigner on Sat Nov 18 17:08:19 CST 2023
 */

package View.Component;

import Model.BodyContain;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * @author rdjks
 */
public class HttpBodyComponent extends JPanel {
    public HttpBodyComponent() {
        initComponents();
        init();
    }

    private void init() {
        uploadBinFileButton.setEnabled(false);
        upLoadFileName.setEnabled(false);
        upLoadFileName.setEditable(false);
        isUsingBinButton.addActionListener(actionEvent -> {
            setIsJSONEnabled(isUsingBinButton.isSelected());
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        textEditor = new JEditorPane();
        isUsingJSONButton = new JToggleButton();
        isUsingBinButton = new JToggleButton();
        label1 = new JLabel();
        upLoadFileName = new JTextField();
        uploadBinFileButton = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {66, 77, 87, 74, 79, 87, 109, 101, 123, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {23, 98, 93, 102, 90, 36, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textEditor);
        }
        add(scrollPane1, new GridBagConstraints(0, 1, 9, 4, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- isUsingJSONButton ----
        isUsingJSONButton.setText("\u4f7f\u7528JSON");
        add(isUsingJSONButton, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- isUsingBinButton ----
        isUsingBinButton.setText("\u4f7f\u7528\u4e8c\u8fdb\u5236\u6587\u4ef6");
        add(isUsingBinButton, new GridBagConstraints(3, 5, 5, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label1 ----
        label1.setText("                  BinFileName\uff1a");
        add(label1, new GridBagConstraints(0, 6, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));
        add(upLoadFileName, new GridBagConstraints(3, 6, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));

        //---- uploadBinFileButton ----
        uploadBinFileButton.setText("Upload BinFile");
        add(uploadBinFileButton, new GridBagConstraints(6, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    private void setIsJSONEnabled(boolean isJSONEnabled) {
        uploadBinFileButton.setEnabled(isJSONEnabled);
        upLoadFileName.setEnabled(isJSONEnabled);
        textEditor.setEnabled(!isJSONEnabled);
        isUsingJSONButton.setEnabled(!isJSONEnabled);
    }

    public void setEditable(boolean flag) {
        textEditor.setEditable(false);
        isUsingBinButton.setVisible(false);
        isUsingJSONButton.setVisible(false);
        label1.setVisible(false);
        upLoadFileName.setVisible(false);
        uploadBinFileButton.setVisible(false);
    }

    public BodyContain getBody() {
        return new BodyContain(isUsingJSONButton.isSelected(), isUsingBinButton.isSelected(), new File(selectedFile.getPath()), textEditor.getText());
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JEditorPane textEditor;
    private JToggleButton isUsingJSONButton;
    private JToggleButton isUsingBinButton;
    private JLabel label1;
    private JTextField upLoadFileName;
    private JButton uploadBinFileButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    File selectedFile;
}
