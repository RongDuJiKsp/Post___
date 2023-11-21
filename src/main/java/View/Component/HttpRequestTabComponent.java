package View.Component;

import Controller.HttpRequestCustomer;
import Model.BodyContain;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Map;

public class HttpRequestTabComponent extends JTabbedPane {
    private HttpBodyComponent httpRequestBodyComponent;
    private HttpKeyValueComponent httpRequestParamsComponent, httpRequestHeadComponent;
    private final HttpRequestCustomer httpRequestCustomer;

    public HttpRequestTabComponent() {
        httpRequestCustomer = new HttpRequestCustomer();
        httpRequestParamsComponent = new HttpKeyValueComponent();
        addTab("Params", httpRequestParamsComponent);
        httpRequestBodyComponent = new HttpBodyComponent();
        addTab("Body", httpRequestBodyComponent);
        httpRequestHeadComponent = new HttpKeyValueComponent(Map.ofEntries(
                Map.entry("crossOrigin", "*"),
                Map.entry("User-Agent", "PostmtfRuntime/1.0.0"),
                Map.entry("Accept", "*/*"),
                Map.entry("Accept-Encoding", "gzip, deflate, br"),
                Map.entry("Connection", "keep-alive")
        ));
        addTab("Head", httpRequestHeadComponent);
    }

    synchronized public HttpResponse sendPost(String url) throws URISyntaxException, IOException {
        BodyContain bodyContain = httpRequestBodyComponent.getBody();
        Map<String, String> headerContain = httpRequestHeadComponent.getKeyValueData(), paramContain = httpRequestParamsComponent.getKeyValueData();
        URIBuilder uriBuilder = new URIBuilder(url);
        paramContain.forEach(uriBuilder::addParameter);
        if (!bodyContain.isUsingBin())
            return httpRequestCustomer.sendPostRequest(uriBuilder.build(), bodyContain.getStringEntity(), null, bodyContain.isUsingJSON(), headerContain);
        else {
            headerContain.put("content-type", Files.probeContentType(bodyContain.getSelectedFile().toPath()));
            return httpRequestCustomer.sendPostRequest(uriBuilder.build(), new FileEntity(bodyContain.getSelectedFile()), null, headerContain);
        }
    }

    synchronized public HttpResponse sendGet(String url) throws URISyntaxException, IOException {
        Map<String, String> headerContain = httpRequestHeadComponent.getKeyValueData(), paramContain = httpRequestParamsComponent.getKeyValueData();
        URIBuilder uriBuilder = new URIBuilder(url);
        paramContain.forEach(uriBuilder::addParameter);
        return httpRequestCustomer.sendGetRequest(uriBuilder.build(), null, headerContain);
    }

}
