/*
 * Created by JFormDesigner on Thu Nov 16 19:16:26 CST 2023
 */

package View.Window;

import Model.RenderAble;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author rdjks
 */
@Slf4j
public class ExceptionDialog extends JDialog implements RenderAble {
    public ExceptionDialog(Window owner, String message) {
        super(owner);
        initComponents();
        initSharp(message);
        initImage();
    }

    private void initImage() {
        Path tmpPath=Path.of(System.getenv("TEMP") + "\\POSTMTFError.png");
        if (Files.exists(tmpPath)) {
            label1.setIcon(new ImageIcon(String.valueOf(tmpPath)));
            return;
        }
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("error.png")) {
            if (inputStream == null) return;
            try (FileOutputStream fileOutputStream = new FileOutputStream(String.valueOf(tmpPath))) {
                byte[] buffered = new byte[512];
                int len;
                while ((len = inputStream.read(buffered)) != -1) {
                    fileOutputStream.write(buffered, 0, len);
                }
            }
            label1.setIcon(new ImageIcon(String.valueOf(tmpPath)));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void initSharp(String message) {
        setResizable(false);
        setSize(400, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        outPutTextArea.setEditable(false);
        outPutTextArea.setText(message);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        outPutTextArea = new JTextPane();
        buttonBar = new JPanel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout(4, 4));

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 0, 0};
                ((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {65, 127, 0};
                ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0E-4};
                ((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0E-4};

                //---- label1 ----
                label1.setText("oops~~ some bad thing happened nya~");
                contentPanel.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(outPutTextArea);
                }
                contentPanel.add(scrollPane1, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    @Override
    public void render() {
        setVisible(true);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTextPane outPutTextArea;
    private JPanel buttonBar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
