/*
 * Created by JFormDesigner on Sat Nov 18 18:40:30 CST 2023
 */

package View.Component;

import Controller.WebSocketCustomer;
import View.Window.ExceptionDialog;
import View.Window.MainWindow;
import io.socket.client.IO;
import io.socket.client.Socket;
import org.java_websocket.exceptions.WebsocketNotConnectedException;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;
import java.net.URI;

/**
 * @author rdjks
 */
public class WebSocketIOComponent extends JPanel {
    private final MainWindow owner;
    private WebSocketCustomer webSocketCustomer;
    private Socket socketIO;
    private boolean isUsingWebSocket;

    public WebSocketIOComponent(MainWindow owner) {
        this.owner=owner;
        init();
    }


    private void init() {
        initComponents();
        initAction();
        messageShower.setEditable(false);
    }

    private void initAction() {
        cleanMessageButton.addActionListener(actionEvent -> clear());
        sendMessageButton.addActionListener(actionEvent -> onSendMessage());
        disConnectButton.addActionListener(actionEvent -> onCloseConnect());
        setListeningButton.addActionListener(actionEvent -> onSetListening());
        removeListeningButton.addActionListener(actionEvent -> onRemoveListening());
    }


    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        messageShower = new JTextPane();
        iolabel1 = new JLabel();
        socketIOEventInputholder = new JTextField();
        socketIOListeningInputholder = new JTextField();
        setListeningButton = new JButton();
        removeListeningButton = new JButton();
        messageInputHolder = new JTextField();
        sendMessageButton = new JButton();
        cleanMessageButton = new JButton();
        disConnectButton = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 71, 185, 176, 101, 79, 143, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 52, 54, 63, 70, 81, 91, 35, 39, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(messageShower);
        }
        add(scrollPane1, new GridBagConstraints(1, 2, 6, 5, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- iolabel1 ----
        iolabel1.setText("     SocketIO Event Name");
        add(iolabel1, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(socketIOEventInputholder, new GridBagConstraints(3, 7, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(socketIOListeningInputholder, new GridBagConstraints(4, 7, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- setListeningButton ----
        setListeningButton.setText("set listening");
        add(setListeningButton, new GridBagConstraints(5, 7, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- removeListeningButton ----
        removeListeningButton.setText("remove listening");
        add(removeListeningButton, new GridBagConstraints(6, 7, 1, 1, 0.0, 0.0,
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

    public void setUsingWebSocket(boolean isUsingWebSocket) {
        this.isUsingWebSocket = isUsingWebSocket;
        iolabel1.setVisible(!isUsingWebSocket);
        removeListeningButton.setVisible(!isUsingWebSocket);
        setListeningButton.setVisible(!isUsingWebSocket);
        socketIOEventInputholder.setVisible(!isUsingWebSocket);
        socketIOListeningInputholder.setVisible(!isUsingWebSocket);
    }

    private void addMessage(String message, String sender, String adder) {
        try {
            Document document = messageShower.getDocument();
            String toAdd = "The " + sender + " sent a message is \n" + message + "  " + adder + "\n\n";
            document.insertString(document.getLength(), toAdd, new SimpleAttributeSet());
        } catch (Exception ignored) {

        }
    }

    private void initSocketIO() {
        socketIO.on(Socket.EVENT_CONNECT, (message) -> addMessage("server connected", "server", ""));
        socketIO.on(Socket.EVENT_DISCONNECT, (message) -> addMessage("server disconnected", "server", ""));
        socketIO.on(Socket.EVENT_CONNECT_ERROR, (message) -> addMessage("server meeting Error", "server", ""));
    }

    private void onSendMessage() {
        try {
            addMessage(messageInputHolder.getText(), "you", isUsingWebSocket ? "" : ("---emit event : " + socketIOEventInputholder.getText()));
            if (isUsingWebSocket) webSocketCustomer.send(messageInputHolder.getText());
            else socketIO.emit(socketIOEventInputholder.getText(), messageInputHolder.getText());
        } catch (WebsocketNotConnectedException e) {
            new ExceptionDialog(owner, e.toString()).render();
        }
    }

    private void onCloseConnect() {
        if (isUsingWebSocket) webSocketCustomer.close();
        else socketIO.close();
    }

    private void onSetListening() {
        String onEventName = socketIOListeningInputholder.getText();
        addMessage(" add a listener named : " + onEventName, "client", "");
        socketIO.on(onEventName, fn -> {
            StringBuilder stringBuilder = new StringBuilder();
            for (Object o : fn) stringBuilder.append((String) o);
            addMessage(stringBuilder.toString(), "server", "-----on event : " + onEventName);
        });
    }

    private void onRemoveListening() {
        socketIO.off(socketIOListeningInputholder.getText());
        addMessage(" remove a listener named" + socketIOListeningInputholder.getText(), "client", "");
    }

    public void clear() {
        messageShower.setText("");
    }

    public void clearWebSocketConnect() {
        if (webSocketCustomer != null && webSocketCustomer.isOpen()) webSocketCustomer.close();
        clear();
    }

    public void clearSocketIOConnect() {
        if (socketIO != null && socketIO.connected()) socketIO.close();
        clear();
    }

    public void clearConnect() {
        clearSocketIOConnect();
        clearWebSocketConnect();
    }

    public void connectWebSocket(URI uri) {
        clearConnect();
        webSocketCustomer = new WebSocketCustomer(uri, message -> addMessage(message, "server", ""));
        webSocketCustomer.connect();
    }

    public void connectSocketIO(URI uri) {
        clearConnect();
        socketIO = IO.socket(uri);
        addMessage("client is sending handshake", "client", "");
        initSocketIO();
        socketIO.connect();
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTextPane messageShower;
    private JLabel iolabel1;
    private JTextField socketIOEventInputholder;
    private JTextField socketIOListeningInputholder;
    private JButton setListeningButton;
    private JButton removeListeningButton;
    private JTextField messageInputHolder;
    private JButton sendMessageButton;
    private JButton cleanMessageButton;
    private JButton disConnectButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
