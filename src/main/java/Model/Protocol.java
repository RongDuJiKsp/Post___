package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Protocol {
    Http("Http"), WebSocket("WebSocket"), SocketIO("Socket.IO");
    private final String value;
}
