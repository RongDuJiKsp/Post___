/*
 * Created by JFormDesigner on Thu Nov 16 09:09:24 CST 2023
 */

package View.Page;

import Controller.HistorySaver;
import Model.HistoryStruct;
import Model.HttpMethod;
import Model.Protocol;
import Model.RenderAble;
import View.Component.HttpRequestTabComponent;
import View.Component.HttpResponseTabComponent;
import View.Component.WebSocketIOComponent;
import View.FunctionalComponent.SelectItemComponent;
import View.ViewConfig;
import View.Window.ExceptionDialog;
import View.Window.MainWindow;
import View.Window.SaveRecordsWindow;
import com.alibaba.fastjson2.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.HttpClients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * @author rdjksp
 */
public class MainPage extends JPanel implements RenderAble {
    private final MainWindow mainWindow;
    private SelectItemComponent protocols, methods;
    private HttpRequestTabComponent httpRequestTabComponent;
    private HttpResponseTabComponent httpResponseTabComponent;
    private WebSocketIOComponent webSocketIOComponent;
    private boolean isRequestTab;
    volatile private HistorySaver historySaver;

    public MainPage(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        init();
    }


    private void init() {
        initComponents();
        initTabs();
        initChoicer();
        initAction();
        initOthers();
    }

    private void initOthers() {
        historySaver = new HistorySaver();
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
        //init WebSocket Tab
        webSocketIOComponent = new WebSocketIOComponent();
        webSocketIOComponent.setVisible(false);
        add(webSocketIOComponent, tabsLocal);
    }

    private void initChoicer() {
        ArrayList<String> protocol = new ArrayList<>(Arrays.asList(Protocol.Http.getValue(), Protocol.WebSocket.getValue(), Protocol.SocketIO.getValue()));
        protocols = new SelectItemComponent(protocol, Protocol.Http.getValue(), this::onChangeProtocol);
        selectMethodBar.add(protocols);
        ArrayList<String> method = new ArrayList<>(Arrays.asList(HttpMethod.Post.getValue(), HttpMethod.Get.getValue()));
        methods = new SelectItemComponent(method, HttpMethod.Post.getValue());
        selectMethodBar.add(methods);
    }

    private void initAction() {
        //init change req/res action
        isRequestTab = true;
        httpResponseTabComponent.setVisible(false);
        toChangeReqResButton.addActionListener(actionEvent -> onReverseSelectedTab());
        //init send action
        sendButton.addActionListener(actionEvent -> onPressSendButton());
        //init download action
        downloadBodyButton.addActionListener(actionEvent -> onDownloadBinFile());
        //init save history action
        saveHistoryButton.addActionListener(actionEvent -> onDownloadHistory());
        //init exit action
        mainWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                onExit();
            }
        });
    }

    private void initHistory() {
        try {
            readFileAndUpdate(new File(new File(".").getCanonicalPath() + "\\dat.db"));
        } catch (FileNotFoundException ignore) {

        } catch (IOException e) {
            sendError(e.toString());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        saveHistoryButton = new JButton();
        label2 = new JLabel();
        url = new JTextField();
        label1 = new JLabel();
        toChangeReqResButton = new JButton();
        downloadBodyButton = new JButton();
        selectMethodBar = new JMenuBar();
        sendButton = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {51, 77, 85, 76, 89, 82, 61, 67, 50, 65, 70, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 19, 42, 0, 38, 91, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- saveHistoryButton ----
        saveHistoryButton.setText("SaveHistory");
        add(saveHistoryButton, new GridBagConstraints(10, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText("Host: ");
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

        //---- downloadBodyButton ----
        downloadBodyButton.setText("download response body as bin file");
        add(downloadBodyButton, new GridBagConstraints(3, 4, 3, 1, 0.0, 0.0,
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

    private void updateTabs(boolean isRequestTab) {
        this.isRequestTab = isRequestTab;
        httpRequestTabComponent.setVisible(isRequestTab);
        httpResponseTabComponent.setVisible(!isRequestTab);
    }


    synchronized private void sendError(String error) {
        if (error == null) return;
        new ExceptionDialog(mainWindow, new String(error.getBytes(), StandardCharsets.UTF_8)).render();
    }

    synchronized private void writeResponseOnUI(HttpResponse res) throws IOException {
        httpResponseTabComponent.parseHttpResponse(res);
    }


    private void sendPost() {
        new Thread(() -> {
            try {
                var toSend = httpRequestTabComponent.sendPost("http://" + url.getText());
                HttpResponse httpResponse = HttpClients.createDefault().execute(toSend.getKey());
                historySaver.addData(new HistoryStruct(HttpMethod.Post, null, toSend.getKey(), httpResponse, toSend.getValue(), new Date().toString()));
                writeResponseOnUI(httpResponse);
            } catch (UnknownHostException e) {
                sendError("Host can't be dns syntax ,please check host");
            } catch (StringIndexOutOfBoundsException e) {
                sendError("If you didn't use JSON ,please off 'use json'");
            } catch (URISyntaxException e) {
                sendError("URI Syntax Exception ,please check your url," + e.getReason());
            } catch (IOException e) {
                sendError(e.toString());
            }
        }).start();
    }

    private void sendGet() {
        new Thread(() -> {
            try {
                var toSend = httpRequestTabComponent.sendGet("http://" + url.getText());
                HttpResponse httpResponse = HttpClients.createDefault().execute(toSend.getKey());
                historySaver.addData(new HistoryStruct(HttpMethod.Get, toSend.getKey(), null, httpResponse, toSend.getValue(), new Date().toString()));
                writeResponseOnUI(httpResponse);
            } catch (UnknownHostException e) {
                sendError("Host can't be dns syntax ,please check host");
            } catch (StringIndexOutOfBoundsException e) {
                sendError("If you didn't use JSON ,please off 'use json'");
            } catch (URISyntaxException e) {
                sendError("URI Syntax Exception ,please check your url," + e.getReason());
            } catch (IOException e) {
                sendError(e.toString());
            }
        }).start();
    }

    private void connectWebSocket() {
        try {
            webSocketIOComponent.connectWebSocket(new URI("ws://" + url.getText()));
        } catch (Exception e) {
            sendError(e.toString());
        }
    }

    private void connectSocketIO() {
        try {
            webSocketIOComponent.connectSocketIO(new URI("ws://" + url.getText()));
        } catch (Exception e) {
            sendError(e.toString());
        }
    }

    private void onPressSendButton() {
        if (protocols.getText().equals(Protocol.Http.getValue())) {
            if (methods.getText().equals(HttpMethod.Post.getValue()))
                sendPost();
            else if (methods.getText().equals(HttpMethod.Get.getValue()))
                sendGet();
            updateTabs(false);
        } else if (protocols.getText().equals(Protocol.WebSocket.getValue())) connectWebSocket();
        else if (protocols.getText().equals(Protocol.SocketIO.getValue())) connectSocketIO();
    }

    private void onChangeProtocol(ActionEvent actionEvent) {
        boolean isSelectedHttp = ((JMenuItem) actionEvent.getSource()).getText().equals(Protocol.Http.getValue());
        methods.setVisible(isSelectedHttp);
        downloadBodyButton.setEnabled(isSelectedHttp);
        httpRequestTabComponent.setVisible(isSelectedHttp);
        httpResponseTabComponent.setVisible(isSelectedHttp);
        toChangeReqResButton.setEnabled(isSelectedHttp);
        webSocketIOComponent.setVisible(!isSelectedHttp);
        saveHistoryButton.setVisible(isSelectedHttp);
        webSocketIOComponent.clearWebSocketConnect();
        if (isSelectedHttp) updateTabs(isRequestTab);
        if (((JMenuItem) actionEvent.getSource()).getText().equals(Protocol.WebSocket.getValue()))
            webSocketIOComponent.setUsingWebSocket(true);
        else if (((JMenuItem) actionEvent.getSource()).getText().equals(Protocol.SocketIO.getValue()))
            webSocketIOComponent.setUsingWebSocket(false);
    }

    private void onReverseSelectedTab() {
        updateTabs(!isRequestTab);
    }

    private void onDownloadBinFile() {
        if (!httpResponseTabComponent.isReceivedBinFile()) {
            sendError("没有等待下载的响应体!");
            return;
        }
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File file = jFileChooser.getSelectedFile();
            if (file == null) return;
            if (file.exists()) throw new IllegalArgumentException("指定的文件已经存在！");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(httpResponseTabComponent.getLastResponseBody());
            fileOutputStream.close();
        } catch (IOException | IllegalArgumentException exception) {
            sendError(exception.toString());
        }
    }

    private void onDownloadHistory() {
        new SaveRecordsWindow(mainWindow, historySaver).setVisible(true);
    }

    private void onExit() {
        try {
            HistoryStruct toSave = null;
            if (methods.getText().equals(HttpMethod.Post.getValue())) {
                var toSend = httpRequestTabComponent.sendPost(url.getText());
                toSave = new HistoryStruct(HttpMethod.Post, null, toSend.getKey(), null, toSend.getValue(), new Date().toString());

            } else if (methods.getText().equals(HttpMethod.Get.getValue())) {
                var toSend = httpRequestTabComponent.sendGet(url.getText());
                toSave = new HistoryStruct(HttpMethod.Get, toSend.getKey(), null, null, toSend.getValue(), new Date().toString());
            }
            if (toSave == null) return;
            Files.writeString(new File(new File(".").getCanonicalPath() + "\\dat.db").toPath(), JSONObject.toJSONString(toSave));
            System.exit(0);

        } catch (RuntimeException | IOException | URISyntaxException ignored) {
            new ExceptionDialog(null, "\"Failed to save for some reason :)\"").render();
            System.exit(0);
        }
    }

    public void readFileAndUpdate(File file) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            String str = new String(fileInputStream.readAllBytes());
            JSONObject jsonObject = JSONObject.parseObject(str);
            HistoryStruct historyStruct = new HistoryStruct(jsonObject);
            updateDataWithHistory(historyStruct);
        }
    }

    synchronized public void updateDataWithHistory(HistoryStruct historyStruct) {
        httpRequestTabComponent.parseHistory(historyStruct);
        URI uri;
        if (historyStruct.getHttpMethod() == HttpMethod.Get)
            uri = historyStruct.getHttpGetData().getURI();
        else uri = historyStruct.getHttpPostData().getURI();
        url.setText(uri.toASCIIString().replaceAll("^[a-z]+://", "").replaceAll("\\?(&?\\w+=(\\w|%)+)*", ""));
    }
    @Override
    public void render() {
        if (!ViewConfig.addedUsersHistory) initHistory();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton saveHistoryButton;
    private JLabel label2;
    private JTextField url;
    private JLabel label1;
    private JButton toChangeReqResButton;
    private JButton downloadBodyButton;
    private JMenuBar selectMethodBar;
    private JButton sendButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


}
