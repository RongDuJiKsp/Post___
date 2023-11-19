package Controller;

import com.alibaba.fastjson2.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

public class HttpRequestCustomer {
    private final HttpClient httpClient;

    public HttpRequestCustomer() {
        httpClient = HttpClients.createDefault();
    }

    public HttpResponse sendPostRequest(URI uri, String strEntity, RequestConfig requestConfig, boolean isJSON, Map<String, String> headerList) throws IOException {
        if (isJSON) {
            headerList.put("content-type", "application/json;charset=utf-8");
            if (strEntity.charAt(0) == '[') strEntity = JSON.parseArray(strEntity).toJSONString();
            else if (strEntity.charAt(0) == '{') strEntity = JSON.parseObject(strEntity).toJSONString();
            else throw new RuntimeException(" Illegal json string! Cannot be resolved");
        } else {
            headerList.put("content-type", "text/plain;charset=utf-8");
        }
        return sendPostRequest(uri, new StringEntity(strEntity, "utf-8"), requestConfig, headerList);
    }

    public HttpResponse sendPostRequest(URI uri, HttpEntity httpEntity, RequestConfig requestConfig, Map<String, String> headerList) throws IOException {
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(httpEntity);
        if (headerList != null) headerList.forEach(httpPost::addHeader);
        return httpClient.execute(httpPost);
    }

    public HttpResponse sendGetRequest(URI uri, RequestConfig requestConfig, Map<String, String> headerList) throws IOException {
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setConfig(requestConfig);
        if (headerList != null) headerList.forEach(httpGet::addHeader);
        return httpClient.execute(httpGet);
    }
}
