package Controller;

import com.alibaba.fastjson2.JSON;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class HttpRequestCustomer {
    private final HttpClient httpClient;

    public HttpRequestCustomer() {
        httpClient = HttpClients.createDefault();
    }

    public HttpResponse sendPostRequest(String uri, String strEntity, RequestConfig requestConfig, boolean isJSON, List<Header> headerList) throws IOException {
        HttpPost httpPost = new HttpPost(uri);
        if (isJSON) {
            httpPost.addHeader("content-type", "application/json;charset=utf-8");
            if (strEntity.charAt(0) == '[') strEntity = JSON.parseArray(strEntity).toJSONString();
            else if (strEntity.charAt(0) == '{') strEntity = JSON.parseObject(strEntity).toJSONString();
            else throw new RuntimeException(" Illegal json string! Cannot be resolved");
        } else {
            httpPost.addHeader("content-type", "text/plain;charset=utf-8");
        }
        HttpEntity httpEntity = new StringEntity(strEntity, "utf-8");
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(httpEntity);
        if (headerList != null) for (Header header : headerList) httpPost.addHeader(header);
        return httpClient.execute(httpPost);
    }

    public HttpResponse sendGetRequest(String uri, List<Header> headerList, List<NameValuePair> nameValuePairs) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(uri);
        uriBuilder.addParameters(nameValuePairs);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        if (headerList != null) for (Header header : headerList) httpGet.addHeader(header);
        return httpClient.execute(httpGet);
    }
}
