/*
 * Created by JFormDesigner on Sat Nov 18 17:08:19 CST 2023
 */

package View.Component;

import Controller.SimpleFunction;
import Model.BodyContain;
import View.Window.ExceptionDialog;
import View.Window.MainWindow;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rdjks
 */
public class HttpBodyComponent extends JPanel {
    File selectedFile;
    final JFileChooser jFileChooser;
    private final MainWindow owner;

    public HttpBodyComponent(MainWindow owner) {
        this.owner = owner;
        jFileChooser = new JFileChooser();
        init();
    }

    private void init() {
        initComponents();
        initAction();
        initCompStatus();
    }

    private void initAction() {
        isUsingBinButton.addActionListener(actionEvent -> onSelectIsUseBinFile());
        isUsingJSONButton.addActionListener(actionEvent -> onSelectIsUseJson());
        uploadBinFileButton.addActionListener(actionEvent -> onUploadFile());
        formatJSONButton.addActionListener(actionEvent -> onFormatJSON());
    }

    private void initCompStatus() {
        uploadBinFileButton.setEnabled(false);
        upLoadFileName.setEnabled(false);
        upLoadFileName.setEditable(false);
        formatJSONButton.setEnabled(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        textEditor = new JEditorPane();
        isUsingJSONButton = new JToggleButton();
        formatJSONButton = new JButton();
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
        isUsingJSONButton.setText("Use JSON");
        add(isUsingJSONButton, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- formatJSONButton ----
        formatJSONButton.setText("format json");
        add(formatJSONButton, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- isUsingBinButton ----
        isUsingBinButton.setText("Use Bin File");
        add(isUsingBinButton, new GridBagConstraints(3, 5, 5, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label1 ----
        label1.setText("                  BinFileName:");
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

    private void setIsTextEnabled(boolean isTextEnabled) {
        uploadBinFileButton.setEnabled(!isTextEnabled);
        upLoadFileName.setEnabled(!isTextEnabled);
        textEditor.setEnabled(isTextEnabled);
        isUsingJSONButton.setEnabled(isTextEnabled);
        formatJSONButton.setEnabled(isTextEnabled);
    }

    private void onSelectIsUseBinFile() {
        setIsTextEnabled(!isUsingBinButton.isSelected());
    }

    private void onSelectIsUseJson() {
        formatJSONButton.setEnabled(isUsingJSONButton.isSelected());
    }

    private void onUploadFile() {
        jFileChooser.showOpenDialog(this);
        selectedFile = jFileChooser.getSelectedFile();
        if (selectedFile == null) return;
        upLoadFileName.setText(selectedFile.getName());
    }

    private void onFormatJSON() {
        Document doc = textEditor.getDocument();
        if (doc instanceof PlainDocument) {
            doc.putProperty(PlainDocument.tabSizeAttribute, 1);
        }
        try {
            JSONObject jsonObject = JSONObject.parseObject(textEditor.getText());
            textEditor.setText(jsonObject.toJSONString(JSONWriter.Feature.PrettyFormat, JSONWriter.Feature.WriteMapNullValue));
        } catch (Exception e) {
            new ExceptionDialog(owner, e.toString()).render();
        }
    }

    public void setEditable(boolean flag) {
        textEditor.setEditable(flag);
        isUsingBinButton.setVisible(flag);
        isUsingJSONButton.setVisible(flag);
        label1.setVisible(flag);
        upLoadFileName.setVisible(flag);
        uploadBinFileButton.setVisible(flag);
        formatJSONButton.setEnabled(!flag);
    }

    public BodyContain getBody() {
        return new BodyContain(isUsingJSONButton.isSelected(), isUsingBinButton.isSelected(), selectedFile, textEditor.getText());
    }

    public void setBody(BodyContain bodyContain) {
        isUsingBinButton.setSelected(bodyContain.isUsingBin());
        isUsingJSONButton.setSelected(bodyContain.isUsingJSON());
        formatJSONButton.setEnabled(bodyContain.isUsingJSON());
        selectedFile = bodyContain.getSelectedFile();
        setIsTextEnabled(!isUsingBinButton.isSelected());
        try {
            textEditor.setText(new String(bodyContain.getStringEntity().getBytes("GBK"), StandardCharsets.UTF_8));
        } catch (UnsupportedEncodingException ignored) {

        }
    }

    public void setBodyBuffered(InputStream inputStream, String contentType) {
        try (ByteArrayOutputStream copiedStream = SimpleFunction.cloneInputStream(inputStream)) {
            try {
                Matcher charset = Pattern.compile("charset=\\w+-?\\w+").matcher(contentType);
                textEditor.setContentType(contentType);
                if (charset.find())
                    textEditor.read(new InputStreamReader(new ByteArrayInputStream(copiedStream.toByteArray()), charset.group().split("=")[1]), textEditor.getDocument());
                else
                    textEditor.read(new InputStreamReader(new ByteArrayInputStream(copiedStream.toByteArray()), StandardCharsets.UTF_8), textEditor.getDocument());
            } catch (IOException e) {
                Matcher charset = Pattern.compile("charset=\\w+-?\\w+").matcher(contentType);
                textEditor.setContentType("text/plain");
                if (charset.find())
                    textEditor.setText(copiedStream.toString(charset.group().split("=")[1]));
                else
                    textEditor.setText(copiedStream.toString(StandardCharsets.UTF_8));
            }
        } catch (IOException ioException) {
            textEditor.setText("Sorry qwq , " + ioException + " , but you can save as bin file");
        }
    }

    public void setBodyUnbuffered(InputStream inputStream, String contentType) {
        try {
            Matcher charset = Pattern.compile("charset=\\w+-?\\w+").matcher(contentType);
            textEditor.setContentType(contentType);
            if (charset.find())
                textEditor.read(new InputStreamReader(inputStream, charset.group().split("=")[1]), textEditor.getDocument());
            else
                textEditor.read(new InputStreamReader(inputStream, StandardCharsets.UTF_8), textEditor.getDocument());
        } catch (Exception e) {
            textEditor.setText("Sorry qwq , " + e);
        }
    }

    public void clear() {
        textEditor.setText("");
        upLoadFileName.setText("");
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JEditorPane textEditor;
    private JToggleButton isUsingJSONButton;
    private JButton formatJSONButton;
    private JToggleButton isUsingBinButton;
    private JLabel label1;
    private JTextField upLoadFileName;
    private JButton uploadBinFileButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
