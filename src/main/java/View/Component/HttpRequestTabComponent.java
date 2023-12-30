package View.Component;

import Controller.HttpRequestCustomer;
import Model.BodyContain;
import Model.HistoryStruct;
import Model.HttpMethod;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestTabComponent extends JTabbedPane {
    private final HttpBodyComponent httpRequestBodyComponent;
    private final HttpKeyValueComponent httpRequestParamsComponent, httpRequestHeadComponent, httpRequestCookieComponent;
    private final ConfigSettingComponent configSettingComponent;

    public HttpRequestTabComponent() {
        httpRequestParamsComponent = new HttpKeyValueComponent();
        addTab("Params", httpRequestParamsComponent);
        httpRequestBodyComponent = new HttpBodyComponent();
        addTab("Body", httpRequestBodyComponent);
        httpRequestHeadComponent = new HttpKeyValueComponent(Map.ofEntries(Map.entry("crossOrigin", "*"), Map.entry("User-Agent", "PostmtfRuntime/1.0.0"), Map.entry("Accept", "*/*"), Map.entry("Accept-Encoding", "gzip, deflate, br"), Map.entry("Connection", "keep-alive")));
        addTab("Head", httpRequestHeadComponent);
        httpRequestCookieComponent = new HttpKeyValueComponent();
        addTab("Cookie", httpRequestCookieComponent);
        configSettingComponent = new ConfigSettingComponent();
        addTab("Config", configSettingComponent);
    }


    private String serializeCookieKeyValue(Map<String, String> kvp) {
        StringBuilder cookieStr = new StringBuilder();
        kvp.forEach((k, v) -> cookieStr.append(k).append('=').append(v).append(';'));
        return cookieStr.toString();
    }

    public void parseHistory(HistoryStruct historyStruct) {
        if (historyStruct.getHttpMethod() == HttpMethod.Get) {
            httpRequestParamsComponent.clear();
            httpRequestHeadComponent.clear();
            httpRequestCookieComponent.clear();
            httpRequestBodyComponent.clear();
            //insert Params
            Map<String, String> KVs = new HashMap<>();
            String queryStr = historyStruct.getHttpGetData().getURI().getQuery();
            if (queryStr != null) {
                for (String paramKV : queryStr.split("&")) {
                    String[] KV = paramKV.split("=");
                    KVs.put(KV[0], KV[1]);
                }
                httpRequestParamsComponent.addKeyValue(KVs);
                KVs.clear();
            }
            //insert Head
            for (Header header : historyStruct.getHttpGetData().getAllHeaders()) {
                KVs.put(header.getName(), header.getValue());
            }
            httpRequestHeadComponent.addKeyValue(KVs);
            KVs.clear();
            //insert cookie
            for (Header cookieHeader : historyStruct.getHttpGetData().getHeaders("Cookie")) {
                cookieHeader.getElements();
            }


        } else {

        }
    }

    public HttpPost sendPost(String url) throws URISyntaxException, IOException {
        BodyContain bodyContain = httpRequestBodyComponent.getBody();
        Map<String, String> headerContain = httpRequestHeadComponent.getKeyValueData(), paramContain = httpRequestParamsComponent.getKeyValueData();
        headerContain.put("Cookie", serializeCookieKeyValue(httpRequestCookieComponent.getKeyValueData()));
        URIBuilder uriBuilder = new URIBuilder(url);
        paramContain.forEach(uriBuilder::addParameter);
        if (!bodyContain.isUsingBin())
            return HttpRequestCustomer.sendPostRequest(uriBuilder.build(), bodyContain.getStringEntity(), configSettingComponent.buildRequestConfig(), bodyContain.isUsingJSON(), headerContain);
        else {
            headerContain.put("content-type", Files.probeContentType(bodyContain.getSelectedFile().toPath()));
            return HttpRequestCustomer.sendPostRequest(uriBuilder.build(), new FileEntity(bodyContain.getSelectedFile()), configSettingComponent.buildRequestConfig(), headerContain);
        }
    }

    public HttpGet sendGet(String url) throws URISyntaxException, IOException {
        Map<String, String> headerContain = httpRequestHeadComponent.getKeyValueData(), paramContain = httpRequestParamsComponent.getKeyValueData();
        headerContain.put("Cookie", serializeCookieKeyValue(httpRequestCookieComponent.getKeyValueData()));
        URIBuilder uriBuilder = new URIBuilder(url);
        paramContain.forEach(uriBuilder::addParameter);
        return HttpRequestCustomer.sendGetRequest(uriBuilder.build(), configSettingComponent.buildRequestConfig(), headerContain);
    }

}
