package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.util.Date;

@AllArgsConstructor
@Getter
public class HistoryStruct {
    private HttpMethod httpMethod;
    private HttpGet httpGetData;
    private HttpPost httpPostData;
    private HttpResponse httpResponseData;
    private Date sendDate;
}
