package Model;

import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Getter
@AllArgsConstructor
public class HistoryStruct {
    private HttpMethod httpMethod;
    private HttpGet httpGetData;
    private HttpPost httpPostData;
    private HttpResponse httpResponseData;
    private BodyContain bodyContain;
    private String sendDate;

    public HistoryStruct(JSONObject jsonObject) throws UnknownHostException {
        //init Base
        httpMethod = jsonObject.getObject("httpMethod", HttpMethod.class);
        httpResponseData = null;
        sendDate = jsonObject.getString("sendDate");
        bodyContain = jsonObject.getJSONObject("bodyContain").to(BodyContain.class);
        //init get
        JSONObject get = jsonObject.getJSONObject("httpGetData");
        if (get != null) {
            httpGetData = new HttpGet(get.getString("URI"));
            //init config
            JSONObject config = get.getJSONObject("config");
            RequestConfig.Builder builder = RequestConfig.custom();
            builder.setAuthenticationEnabled(config.getBoolean("authenticationEnabled"))
                    .setCircularRedirectsAllowed(config.getBoolean("circularRedirectsAllowed"))
                    .setConnectTimeout(config.getIntValue("connectTimeout"))
                    .setConnectionRequestTimeout(config.getIntValue("connectionRequestTimeout"))
                    .setContentCompressionEnabled(config.getBoolean("contentCompressionEnabled"))
                    .setDecompressionEnabled(config.getBoolean("decompressionEnabled"))
                    .setExpectContinueEnabled(config.getBoolean("expectContinueEnabled"))
                    .setMaxRedirects(config.getIntValue("maxRedirects"))
                    .setNormalizeUri(config.getBoolean("normalizeUri"))
                    .setRedirectsEnabled(config.getBoolean("redirectsEnabled"))
                    .setRelativeRedirectsAllowed(config.getBoolean("relativeRedirectsAllowed"))
                    .setSocketTimeout(config.getIntValue("socketTimeout"))
                    .setStaleConnectionCheckEnabled(config.getBoolean("staleConnectionCheckEnabled"));
            if (config.containsKey("localAddress"))
                builder.setLocalAddress(InetAddress.getByName(config.getString("localAddress")));
            if (config.containsKey("proxy"))
                builder.setProxy(config.getJSONObject("proxy").to(HttpHost.class));

            httpGetData.setConfig(builder.build());
            //init protocol
            JSONObject protocolConfig = get.getJSONObject("protocolVersion");
            httpGetData.setProtocolVersion(new ProtocolVersion(protocolConfig.getString("protocol"), protocolConfig.getIntValue("minor"), protocolConfig.getIntValue("major")));
            //init header
            for (JSONObject headerObject : get.getJSONArray("allHeaders").toArray(JSONObject.class)) {
                httpGetData.addHeader(headerObject.getString("name"), headerObject.getString("value"));
            }
        }
        //init post
        JSONObject post = jsonObject.getJSONObject("httpPostData");
        if (post != null) {
            httpPostData = new HttpPost(post.getString("URI"));
            //init cfg
            JSONObject config = post.getJSONObject("config");
            RequestConfig.Builder builder = RequestConfig.custom();
            builder.setAuthenticationEnabled(config.getBoolean("authenticationEnabled"))
                    .setCircularRedirectsAllowed(config.getBoolean("circularRedirectsAllowed"))
                    .setConnectTimeout(config.getIntValue("connectTimeout"))
                    .setConnectionRequestTimeout(config.getIntValue("connectionRequestTimeout"))
                    .setContentCompressionEnabled(config.getBoolean("contentCompressionEnabled"))
                    .setDecompressionEnabled(config.getBoolean("decompressionEnabled"))
                    .setExpectContinueEnabled(config.getBoolean("expectContinueEnabled"))
                    .setMaxRedirects(config.getIntValue("maxRedirects"))
                    .setNormalizeUri(config.getBoolean("normalizeUri"))
                    .setRedirectsEnabled(config.getBoolean("redirectsEnabled"))
                    .setRelativeRedirectsAllowed(config.getBoolean("relativeRedirectsAllowed"))
                    .setSocketTimeout(config.getIntValue("socketTimeout"))
                    .setStaleConnectionCheckEnabled(config.getBoolean("staleConnectionCheckEnabled"));
            if (config.containsKey("localAddress"))
                builder.setLocalAddress(InetAddress.getByName(config.getString("localAddress")));
            if (config.containsKey("proxy"))
                builder.setProxy(config.getJSONObject("proxy").to(HttpHost.class));
            httpPostData.setConfig(builder.build());
            //init protocol
            JSONObject protocolConfig = post.getJSONObject("protocolVersion");
            httpPostData.setProtocolVersion(new ProtocolVersion(protocolConfig.getString("protocol"), protocolConfig.getIntValue("minor"), protocolConfig.getIntValue("major")));
            //init header
            for (JSONObject headerObject : post.getJSONArray("allHeaders").toArray(JSONObject.class)) {
                httpPostData.addHeader(headerObject.getString("name"), headerObject.getString("value"));
            }
        }

    }
}
