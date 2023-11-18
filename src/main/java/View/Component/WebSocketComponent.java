/*
 * Created by JFormDesigner on Sat Nov 18 18:40:30 CST 2023
 */

package View.Component;

import javax.swing.*;
import java.awt.*;

/**
 * @author rdjks
 */
public class WebSocketComponent extends JPanel {
    public WebSocketComponent() {
        initComponents();
        init();
    }

    private void init() {
        messageShower.setEditable(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        messageShower = new JTextPane();
        messageInputHolder = new JTextField();
        sendMessagebutton = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 71, 185, 176, 155, 79, 143, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 52, 54, 63, 70, 81, 91, 24, 39, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(messageShower);
        }
        add(scrollPane1, new GridBagConstraints(1, 2, 6, 5, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(messageInputHolder, new GridBagConstraints(1, 8, 4, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- sendMessagebutton ----
        sendMessagebutton.setText("Send Message");
        add(sendMessagebutton, new GridBagConstraints(5, 8, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTextPane messageShower;
    private JTextField messageInputHolder;
    private JButton sendMessagebutton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
