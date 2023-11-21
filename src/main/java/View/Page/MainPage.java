/*
 * Created by JFormDesigner on Thu Nov 16 09:09:24 CST 2023
 */

package View.Page;

import Controller.HttpRequestCustomer;
import Controller.SimpleFunction;
import Model.BodyContain;
import Model.HttpMethod;
import Model.Protocol;
import View.Component.HttpBodyComponent;
import View.Component.HttpKeyValueComponent;
import View.Component.WebSocketIOComponent;
import View.FunctionalComponent.SelectItemComponent;
import View.Window.ExceptionDialog;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * @author rdjksp
 */
public class MainPage extends JPanel {
    private final HttpRequestCustomer httpRequestCustomer;
    private final JFrame mainWindow;
    private SelectItemComponent protocols, methods;
    private JTabbedPane httpRequestTab, httpResponseTab;
    private GridBagConstraints tabsLocal;
    private HttpKeyValueComponent httpRequestParamsComponent, httpRequestHeadComponent, httpResponseHeadComponent;
    private HttpBodyComponent httpRequestBodyComponent, httpResponseBodyComponent;
    private WebSocketIOComponent webSocketIOComponent;
    private boolean isRequestTab;
    private Thread runningThread;
    private byte[] lastResponseBody;

    public MainPage(JFrame mainWindow) {
        httpRequestCustomer = new HttpRequestCustomer();
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
        initComponents();
        initTabs();
        initSender();
        initChoicer();
    }

    private void initTabs() {
        //init tab panel
        tabsLocal = new GridBagConstraints(1, 5, 10, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0);

        //init http Tab
        httpRequestTab = new JTabbedPane();
        httpRequestParamsComponent = new HttpKeyValueComponent();
        httpRequestTab.addTab("Params", httpRequestParamsComponent);
        httpRequestBodyComponent = new HttpBodyComponent();
        httpRequestTab.addTab("Body", httpRequestBodyComponent);
        httpRequestHeadComponent = new HttpKeyValueComponent(Map.ofEntries(
                Map.entry("crossOrigin", "*"),
                Map.entry("User-Agent", "PostmtfRuntime/1.0.0"),
                Map.entry("Accept", "*/*"),
                Map.entry("Accept-Encoding", "gzip, deflate, br"),
                Map.entry("Connection", "keep-alive")
        ));
        httpRequestTab.addTab("Head", httpRequestHeadComponent);
        add(httpRequestTab, tabsLocal);
        //init httpRepTab
        httpResponseTab = new JTabbedPane();
        httpResponseHeadComponent = new HttpKeyValueComponent();
        httpResponseHeadComponent.setEditable(false);
        httpResponseTab.addTab("ResponseHead", httpResponseHeadComponent);
        httpResponseBodyComponent = new HttpBodyComponent();
        httpResponseBodyComponent.setEditable(false);
        httpResponseTab.addTab("ResponseBody", httpResponseBodyComponent);
        add(httpResponseTab, tabsLocal);
        //connect changer
        isRequestTab = true;
        httpResponseTab.setVisible(false);
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
            httpResponseTab.setVisible(isSelectedHttp);
            httpRequestTab.setVisible(isSelectedHttp);
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

    private void updateTabs(boolean isRequestTab) {
        this.isRequestTab = isRequestTab;
        httpRequestTab.setVisible(isRequestTab);
        httpResponseTab.setVisible(!isRequestTab);
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

    private void sendPost() {
        if (!(runningThread == null || runningThread.getState() == Thread.State.TERMINATED)) {
            new ExceptionDialog(mainWindow).showMessage(new String("存在正在进行的连接！".getBytes(), StandardCharsets.UTF_8));
            return;
        }
        runningThread = new Thread(() -> {
            try {
                BodyContain bodyContain = httpRequestBodyComponent.getBody();
                Map<String, String> headerContain = httpRequestHeadComponent.getKeyValueData(), paramContain = httpRequestParamsComponent.getKeyValueData();
                URIBuilder uriBuilder = new URIBuilder(url.getText());
                paramContain.forEach(uriBuilder::addParameter);
                if (!bodyContain.isUsingBin())
                    parseHttpResponse(httpRequestCustomer.sendPostRequest(uriBuilder.build(), bodyContain.getStringEntity(), null, bodyContain.isUsingJSON(), headerContain));
                else {
                    headerContain.put("content-type", Files.probeContentType(bodyContain.getSelectedFile().toPath()));
                    parseHttpResponse(httpRequestCustomer.sendPostRequest(uriBuilder.build(), new FileEntity(bodyContain.getSelectedFile()), null, headerContain));
                }

            } catch (URISyntaxException | IOException e) {
                new ExceptionDialog(mainWindow).showMessage(e.getLocalizedMessage());
                throw new RuntimeException(e);
            }

        });
        runningThread.start();
    }

    private void sendGet() {
        if (!(runningThread == null || runningThread.getState() == Thread.State.TERMINATED)) {
            new ExceptionDialog(mainWindow).showMessage(new String("存在正在进行的连接！".getBytes(), StandardCharsets.UTF_8));
            return;
        }
        runningThread = new Thread(() -> {
            try {
                Map<String, String> headerContain = httpRequestHeadComponent.getKeyValueData(), paramContain = httpRequestParamsComponent.getKeyValueData();
                URIBuilder uriBuilder = new URIBuilder(url.getText());
                paramContain.forEach(uriBuilder::addParameter);
                parseHttpResponse(httpRequestCustomer.sendGetRequest(uriBuilder.build(), null, headerContain));
            } catch (URISyntaxException | IOException e) {
                new ExceptionDialog(mainWindow).showMessage(e.getLocalizedMessage());
                throw new RuntimeException(e);
            }

        });
        runningThread.start();
    }

    private void connectWebSocket() {
        try {
            webSocketIOComponent.connectWebSocket(new URI(url.getText()));
        } catch (Exception e) {
            new ExceptionDialog(mainWindow).showMessage(e.getLocalizedMessage());
        }
    }

    private void connectSocketIO() {
        try {
            webSocketIOComponent.connectSocketIO(new URI(url.getText()));
        } catch (Exception e) {
            new ExceptionDialog(mainWindow).showMessage(e.getLocalizedMessage());
        }
    }

    private void parseHttpResponse(HttpResponse httpResponse) throws IOException {
        if (httpResponse == null) return;
        //save headers
        httpResponseHeadComponent.getTableModel().getDataVector().clear();
        for (Header header : httpResponse.getAllHeaders()) {
            httpResponseHeadComponent.getTableModel().addRow(new String[]{header.getName(), header.getValue()});
        }
        //save body
        if (httpResponse.containsHeader("content-type")) {
            ByteArrayOutputStream byteArrayOutputStream = SimpleFunction.cloneInputStream(httpResponse.getEntity().getContent());
            httpResponseBodyComponent.setBody(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), httpResponse.getFirstHeader("content-type").getValue());
            lastResponseBody = byteArrayOutputStream.toByteArray();
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
