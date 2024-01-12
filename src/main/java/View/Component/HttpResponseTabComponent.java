package View.Component;

import Controller.SimpleFunction;
import View.ViewConfig;
import lombok.Getter;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HttpResponseTabComponent extends JTabbedPane {
    private final HttpKeyValueComponent httpResponseHeadComponent, httpResponseCookieComponent;
    private final HttpBodyComponent httpResponseBodyComponent;
    @Getter
    private byte[] lastResponseBody;
    @Getter
    private String contentType;


    public HttpResponseTabComponent() {
        httpResponseHeadComponent = new HttpKeyValueComponent();
        httpResponseHeadComponent.setEditable(false);
        addTab("Response Head", httpResponseHeadComponent);
        httpResponseBodyComponent = new HttpBodyComponent();
        httpResponseBodyComponent.setEditable(false);
        addTab("Response Body", httpResponseBodyComponent);
        httpResponseCookieComponent = new HttpKeyValueComponent();
        httpResponseCookieComponent.setEditable(false);
        addTab("Response Cookies", httpResponseCookieComponent);
    }

    public void parseHttpResponse(HttpResponse httpResponse) throws IOException {
        httpResponseCookieComponent.clear();
        httpResponseBodyComponent.clear();
        httpResponseHeadComponent.clear();

        if (httpResponse == null) return;
        //save headers
        for (Header header : httpResponse.getAllHeaders()) {
            if (header.getValue().equals("Set-Cookie")) continue;
            httpResponseHeadComponent.getTableModel().addRow(new String[]{header.getName(), header.getValue()});
        }
        // save cookie
        if (httpResponse.containsHeader("Set-Cookie")) {
            for (Header header : httpResponse.getHeaders("Set-Cookie")) {
                String[] kv = header.getValue().split("=");
                httpResponseCookieComponent.getTableModel().addRow(new String[]{kv[0], kv[1]});
            }
        }
        //save body
        if (httpResponse.containsHeader("content-type")) {
            contentType = httpResponse.getFirstHeader("content-type").getValue();
            if (ViewConfig.isUsingBufferedFile) {
                try (ByteArrayOutputStream byteArrayOutputStream = SimpleFunction.cloneInputStream(httpResponse.getEntity().getContent())) {
                    lastResponseBody = byteArrayOutputStream.toByteArray();
                    if (ViewConfig.isUsingShower)
                        httpResponseBodyComponent.setBodyBuffered(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), contentType);

                }
            } else {
                lastResponseBody = null;
                if (ViewConfig.isUsingShower)
                    httpResponseBodyComponent.setBodyUnbuffered(httpResponse.getEntity().getContent(), contentType);
            }

        } else contentType = null;
    }

    public boolean isReceivedBinFile() {
        return lastResponseBody != null && contentType != null && lastResponseBody.length > 0;
    }

}
