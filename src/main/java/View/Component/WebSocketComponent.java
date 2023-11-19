/*
 * Created by JFormDesigner on Sat Nov 18 18:40:30 CST 2023
 */

package View.Component;

import Controller.WebSocketCustomer;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.util.Date;

/**
 * @author rdjks
 */
public class WebSocketComponent extends JPanel {
    private WebSocketCustomer webSocketCustomer;

    public WebSocketComponent() {
        init();
    }

    private void init() {
        initComponents();
        messageShower.setEditable(false);
        initAction();
    }

    private void initAction() {
        cleanMessageButton.addActionListener(actionEvent -> {
            messageShower.setText("");
        });
        sendMessageButton.addActionListener(actionEvent -> {
            addMessage(messageInputHolder.getText(), "you");
            webSocketCustomer.send(messageInputHolder.getText());
        });
        disConnectButton.addActionListener(actionEvent -> {
            webSocketCustomer.close();
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        messageShower = new JTextPane();
        messageInputHolder = new JTextField();
        sendMessageButton = new JButton();
        cleanMessageButton = new JButton();
        disConnectButton = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 71, 185, 176, 101, 79, 143, 0, 0, 0, 0, 0, 0};
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
        add(messageInputHolder, new GridBagConstraints(1, 8, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- sendMessageButton ----
        sendMessageButton.setText("Send Message");
        add(sendMessageButton, new GridBagConstraints(4, 8, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- cleanMessageButton ----
        cleanMessageButton.setText("Clean Messages");
        add(cleanMessageButton, new GridBagConstraints(5, 8, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- disConnectButton ----
        disConnectButton.setText("Close Connect");
        add(disConnectButton, new GridBagConstraints(6, 8, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    private void addMessage(String message, String sender) {
        messageShower.setText(messageShower.getText() + "\n at  " + new Date().toString() + " --- " + sender + " send a message: \n\n" + message + "\n\n");
    }

    public void connectWebSocket(URI uri) {
        webSocketCustomer = new WebSocketCustomer(uri, message -> addMessage(message, "server"));
        webSocketCustomer.connect();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTextPane messageShower;
    private JTextField messageInputHolder;
    private JButton sendMessageButton;
    private JButton cleanMessageButton;
    private JButton disConnectButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
