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
import View.Component.WebSocketComponent;
import View.FunctionalComponent.SelectItemComponent;
import View.Window.ExceptionDialog;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * @author rdjks
 */
public class MainPage extends JPanel {
    private final HttpRequestCustomer httpRequestCustomer;
    private final JFrame mainWindow;


    public MainPage(JFrame mainWindow) {
        httpRequestCustomer = new HttpRequestCustomer();
        this.mainWindow = mainWindow;
        initComponents();
        init();
        initSender();
    }

    private void init() {
        //init choice
        ArrayList<String> protocol = new ArrayList<>(Arrays.asList(Protocol.Http.getValue(), Protocol.WebSocket.getValue(), Protocol.SocketIO.getValue()));
        protocols = new SelectItemComponent(protocol, Protocol.Http.getValue(), actionEvent -> {
            String selected = ((JMenuItem) actionEvent.getSource()).getText();
            methods.setVisible(selected.equals(Protocol.Http.getValue()));
            if (selected.equals(Protocol.Http.getValue())) {
                httpRequestTab.setVisible(isRequestTab);
                httpResponseTab.setVisible(!isRequestTab);
                webSocketComponent.setVisible(false);
            } else {
                httpResponseTab.setVisible(false);
                httpRequestTab.setVisible(false);
                webSocketComponent.setVisible(true);
            }
        });
        selectMethodBar.add(protocols);
        ArrayList<String> method = new ArrayList<>(Arrays.asList(HttpMethod.Post.getValue(), HttpMethod.Get.getValue()));
        methods = new SelectItemComponent(method, HttpMethod.Post.getValue());
        selectMethodBar.add(methods);
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
                Map.entry("crossOrigin", "*")
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
            isRequestTab = !isRequestTab;
            httpRequestTab.setVisible(isRequestTab);
            httpResponseTab.setVisible(!isRequestTab);
        });
        //init WebSocket Tab
        webSocketComponent = new WebSocketComponent();
        webSocketComponent.setVisible(false);
        add(webSocketComponent, tabsLocal);

    }

    private void initSender() {
        sendButton.addActionListener(actionEvent -> {
            if (protocols.getText().equals(Protocol.Http.getValue()) && methods.getText().equals(HttpMethod.Post.getValue()))
                sendPost();
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

    private void sendPost() {
        if (!(runningThread == null || runningThread.getState() == Thread.State.TERMINATED)) return;
        runningThread = new Thread(() -> {
            try {
                BodyContain bodyContain = httpRequestBodyComponent.getBody();
                Map<String, String> headerContain = httpRequestHeadComponent.getKeyValueData(), paramContain = httpRequestParamsComponent.getKeyValueData();
                URIBuilder uriBuilder = new URIBuilder(url.getText());
                paramContain.forEach(uriBuilder::addParameter);
                HttpResponse httpResponse = null;
                if (!bodyContain.isUsingBin())
                    httpResponse = httpRequestCustomer.sendPostRequest(uriBuilder.build(), bodyContain.getStringEntity(), null, bodyContain.isUsingJSON(), headerContain);
                else {
                    headerContain.put("content-type", Files.probeContentType(bodyContain.getSelectedFile().toPath()));
                    httpResponse = httpRequestCustomer.sendPostRequest(uriBuilder.build(), new FileEntity(bodyContain.getSelectedFile()), null, headerContain);
                }
                InputStream inputStream = httpResponse.getEntity().getContent();
                BufferedInputStream br = new BufferedInputStream(inputStream);
                String response = SimpleFunction.readInputToStrLine(br);
                lastResponseBody = inputStream.readAllBytes();
                httpResponseBodyComponent.setBody(response);
                httpResponseHeadComponent.getTableModel().getDataVector().clear();
                for (Header header : httpResponse.getAllHeaders()) {
                    httpResponseHeadComponent.getTableModel().addRow(new String[]{header.getName(), header.getValue()});
                }
            } catch (URISyntaxException | IOException e) {
                new ExceptionDialog(mainWindow).showMessage(e.getLocalizedMessage());
                throw new RuntimeException(e);
            }

        });
        runningThread.start();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label2;
    private JTextField url;
    private JLabel label1;
    private JButton toChangeReqResButton;
    private JMenuBar selectMethodBar;
    private JButton sendButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private SelectItemComponent protocols, methods;
    private JTabbedPane httpRequestTab, httpResponseTab;
    private GridBagConstraints tabsLocal;
    private HttpKeyValueComponent httpRequestParamsComponent, httpRequestHeadComponent, httpResponseHeadComponent;
    private HttpBodyComponent httpRequestBodyComponent, httpResponseBodyComponent;
    private WebSocketComponent webSocketComponent;
    private boolean isRequestTab;
    private Thread runningThread;
    private byte[] lastResponseBody;

}
