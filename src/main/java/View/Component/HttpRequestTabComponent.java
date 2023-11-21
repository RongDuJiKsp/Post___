package View.Component;

import Controller.HttpRequestCustomer;
import Model.BodyContain;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Map;

public class HttpRequestTabComponent extends JTabbedPane {
    private final HttpBodyComponent httpRequestBodyComponent;
    private final HttpKeyValueComponent httpRequestParamsComponent,httpRequestHeadComponent;

    public HttpRequestTabComponent() {

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

    synchronized public HttpPost sendPost(String url) throws URISyntaxException, IOException {
        BodyContain bodyContain = httpRequestBodyComponent.getBody();
        Map<String, String> headerContain = httpRequestHeadComponent.getKeyValueData(), paramContain = httpRequestParamsComponent.getKeyValueData();
        URIBuilder uriBuilder = new URIBuilder(url);
        paramContain.forEach(uriBuilder::addParameter);
        if (!bodyContain.isUsingBin())
            return HttpRequestCustomer.sendPostRequest(uriBuilder.build(), bodyContain.getStringEntity(), null, bodyContain.isUsingJSON(), headerContain);
        else {
            headerContain.put("content-type", Files.probeContentType(bodyContain.getSelectedFile().toPath()));
            return HttpRequestCustomer.sendPostRequest(uriBuilder.build(), new FileEntity(bodyContain.getSelectedFile()), null, headerContain);
        }
    }

    synchronized public HttpGet sendGet(String url) throws URISyntaxException, IOException {
        Map<String, String> headerContain = httpRequestHeadComponent.getKeyValueData(), paramContain = httpRequestParamsComponent.getKeyValueData();
        URIBuilder uriBuilder = new URIBuilder(url);
        paramContain.forEach(uriBuilder::addParameter);
        return HttpRequestCustomer.sendGetRequest(uriBuilder.build(), null, headerContain);
    }

}
