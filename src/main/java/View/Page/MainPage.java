/*
 * Created by JFormDesigner on Thu Nov 16 09:09:24 CST 2023
 */

package View.Page;

import Model.HttpMethod;
import Model.Protocol;
import View.Component.HttpRequestTabComponent;
import View.Component.HttpResponseTabComponent;
import View.Component.WebSocketIOComponent;
import View.FunctionalComponent.SelectItemComponent;
import View.Window.ExceptionDialog;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author rdjksp
 */
public class MainPage extends JPanel {
    private Thread runningThread;
    private final JFrame mainWindow;
    private SelectItemComponent protocols, methods;
    private HttpRequestTabComponent httpRequestTabComponent;
    private HttpResponseTabComponent httpResponseTabComponent;
    private WebSocketIOComponent webSocketIOComponent;
    private boolean isRequestTab;

    public MainPage(JFrame mainWindow) {
        this.mainWindow = mainWindow;
        init();
    }

    private void updateTabs(boolean isRequestTab) {
        this.isRequestTab = isRequestTab;
        httpRequestTabComponent.setVisible(isRequestTab);
        httpResponseTabComponent.setVisible(!isRequestTab);
    }

    private void sendError(String error) {
        new ExceptionDialog(mainWindow).showMessage(new String(error.getBytes(), StandardCharsets.UTF_8));
    }

    private void init() {
        initComponents();
        initTabs();
        initSender();
        initChoicer();
    }

    private void initTabs() {
        //init tab panel
        GridBagConstraints tabsLocal = new GridBagConstraints(1, 5, 10, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0);

        //init http Tab
        httpRequestTabComponent = new HttpRequestTabComponent();
        add(httpRequestTabComponent, tabsLocal);
        //init httpRepTab
        httpResponseTabComponent = new HttpResponseTabComponent();
        add(httpResponseTabComponent, tabsLocal);
        //connect changer
        isRequestTab = true;
        httpResponseTabComponent.setVisible(false);
        toChangeReqResButton.addActionListener(actionEvent -> {
            updateTabs(!isRequestTab);
        });
        //init WebSocket Tab
        webSocketIOComponent = new WebSocketIOComponent();
        webSocketIOComponent.setVisible(false);
        add(webSocketIOComponent, tabsLocal);
    }

    private void initChoicer() {
        ArrayList<String> protocol = new ArrayList<>(Arrays.asList(Protocol.Http.getValue(), Protocol.WebSocket.getValue(), Protocol.SocketIO.getValue()));
        protocols = new SelectItemComponent(protocol, Protocol.Http.getValue(), actionEvent -> {
            boolean isSelectedHttp = ((JMenuItem) actionEvent.getSource()).getText().equals(Protocol.Http.getValue());
            methods.setVisible(isSelectedHttp);
            httpRequestTabComponent.setVisible(isSelectedHttp);
            httpResponseTabComponent.setVisible(isSelectedHttp);
            toChangeReqResButton.setEnabled(isSelectedHttp);
            webSocketIOComponent.setVisible(!isSelectedHttp);
            webSocketIOComponent.clearWebSocketConnect();
            if (isSelectedHttp) updateTabs(isRequestTab);
            if (((JMenuItem) actionEvent.getSource()).getText().equals(Protocol.WebSocket.getValue()))
                webSocketIOComponent.setUsingWebSocket(true);
            else if (((JMenuItem) actionEvent.getSource()).getText().equals(Protocol.SocketIO.getValue()))
                webSocketIOComponent.setUsingWebSocket(false);
        });
        selectMethodBar.add(protocols);
        ArrayList<String> method = new ArrayList<>(Arrays.asList(HttpMethod.Post.getValue(), HttpMethod.Get.getValue()));
        methods = new SelectItemComponent(method, HttpMethod.Post.getValue());
        selectMethodBar.add(methods);
    }

    private void initSender() {
        sendButton.addActionListener(actionEvent -> {
            if (protocols.getText().equals(Protocol.Http.getValue())) {
                if (methods.getText().equals(HttpMethod.Post.getValue()))
                    sendPost();
                else if (methods.getText().equals(HttpMethod.Get.getValue()))
                    sendGet();
                updateTabs(false);
            } else if (protocols.getText().equals(Protocol.WebSocket.getValue())) connectWebSocket();
            else if (protocols.getText().equals(Protocol.SocketIO.getValue())) connectSocketIO();
        });
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label2 = new JLabel();
        url = new JTextField();
        label1 = new JLabel();
        toChangeReqResButton = new JButton();
        selectMethodBar = new JMenuBar();
        sendButton = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {51, 77, 85, 76, 89, 82, 61, 67, 50, 65, 70, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 24, 29, 0, 38, 91, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label2 ----
        label2.setText("      Url:");
        add(label2, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(url, new GridBagConstraints(2, 3, 6, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label1 ----
        label1.setText("Protrol:");
        add(label1, new GridBagConstraints(9, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- toChangeReqResButton ----
        toChangeReqResButton.setText("Change Req/Res Page");
        add(toChangeReqResButton, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(selectMethodBar, new GridBagConstraints(9, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- sendButton ----
        sendButton.setText("Send");
        add(sendButton, new GridBagConstraints(10, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    synchronized private boolean isAvailableToSendARequest() {
        return runningThread == null || runningThread.getState() == Thread.State.TERMINATED;
    }

    private void sendPost() {
        if (!isAvailableToSendARequest()) {
            sendError("存在正在进行的连接！无法进行post请求的发送！");
            return;
        }
        runningThread = new Thread(() -> {
            try {
                httpResponseTabComponent.parseHttpResponse(httpRequestTabComponent.sendPost(url.getText()));
            } catch (URISyntaxException | IOException e) {
                sendError(e.getLocalizedMessage());
            }

        });
        runningThread.start();
    }

    private void sendGet() {
        if (!isAvailableToSendARequest()) {
            sendError("存在正在进行的连接！无法进行Get请求的发送！");
            return;
        }
        runningThread = new Thread(() -> {
            try {
                httpResponseTabComponent.parseHttpResponse(httpRequestTabComponent.sendGet(url.getText()));
            } catch (URISyntaxException | IOException e) {
                sendError(e.getLocalizedMessage());
            }

        });
        runningThread.start();
    }

    private void connectWebSocket() {
        try {
            webSocketIOComponent.connectWebSocket(new URI(url.getText()));
        } catch (Exception e) {
            sendError(e.getLocalizedMessage());
        }
    }

    private void connectSocketIO() {
        try {
            webSocketIOComponent.connectSocketIO(new URI(url.getText()));
        } catch (Exception e) {
            sendError(e.getLocalizedMessage());
        }
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label2;
    private JTextField url;
    private JLabel label1;
    private JButton toChangeReqResButton;
    private JMenuBar selectMethodBar;
    private JButton sendButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


}
