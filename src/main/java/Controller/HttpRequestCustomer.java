package Controller;

import com.alibaba.fastjson2.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpRequestCustomer {
    private final HttpClient httpClient;

    public HttpRequestCustomer() {
        httpClient = HttpClients.createDefault();
    }

    public HttpResponse sendPostRequest(String uri, String strEntity, RequestConfig requestConfig, boolean isJSON) throws URISyntaxException, IOException {
        HttpPost httpPost = new HttpPost(new URI(uri));
        if (isJSON) {
            httpPost.addHeader("content-type", "application/json");
            if (strEntity.charAt(0) == '[') strEntity = JSON.parseArray(strEntity).toJSONString();
            else if (strEntity.charAt(0) == '{') strEntity = JSON.parseObject(strEntity).toJSONString();
            else  throw new RuntimeException(" Illegal json string! Cannot be resolved");
        } else {
            httpPost.addHeader("content-type", "text/plain");
        }
        HttpEntity httpEntity = new StringEntity(strEntity, "utf-8");
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(httpEntity);
        return httpClient.execute(httpPost);
    }
    public HttpResponse sendGetRequest(String uri){
        return  null;
    }
}
