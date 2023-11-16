/*
 * Created by JFormDesigner on Thu Nov 16 19:16:26 CST 2023
 */

package View.Dialog.ExceptionDialog;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author rdjks
 */
public class ExceptionDialog extends JDialog {
    public ExceptionDialog(Window owner) {
        super(owner);
        initComponents();
        outPutTextArea.setEditable(false);
    }
    public void showMessage(String message){
        outPutTextArea.setText(message);
        setVisible(true);
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        outPutTextArea = new JTextArea();
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
                ((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {35, 181, 0};
                ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0E-4};
                ((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0E-4};

                //---- label1 ----
                label1.setText("\u53d1\u751f\u4e86\u9519\u8bef\uff1a");
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

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTextArea outPutTextArea;
    private JPanel buttonBar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
