package Controller;

import Model.WebSocketCallBack;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WebSocketCustomer extends WebSocketClient {

    private final WebSocketCallBack webSocketCallBack;

    public WebSocketCustomer(URI serverUri, WebSocketCallBack webSocketCallBack) {
        super(serverUri);
        this.webSocketCallBack = webSocketCallBack;
    }


    @Override
    public void onOpen(ServerHandshake handshakeData) {
        webSocketCallBack.getMessage("server connected");
    }

    @Override
    public void onMessage(String message) {
        webSocketCallBack.getMessage(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        webSocketCallBack.getMessage("connect closed in code" + code + " because of   " + ("".equals(reason)?"None":reason));
    }

    @Override
    public void onError(Exception ex) {
        System.err.println(ex.getMessage());
    }
}
