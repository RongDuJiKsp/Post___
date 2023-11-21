package View.Component;

import Controller.SimpleFunction;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HttpResponseTabComponent extends JTabbedPane {
    private HttpKeyValueComponent httpResponseHeadComponent;
    private HttpBodyComponent httpResponseBodyComponent;
    private byte[] lastResponseBody;

    public HttpResponseTabComponent() {
        httpResponseHeadComponent = new HttpKeyValueComponent();
        httpResponseHeadComponent.setEditable(false);
        addTab("ResponseHead", httpResponseHeadComponent);
        httpResponseBodyComponent = new HttpBodyComponent();
        httpResponseBodyComponent.setEditable(false);
        addTab("ResponseBody", httpResponseBodyComponent);
    }

    public void parseHttpResponse(HttpResponse httpResponse) throws IOException {
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
}
