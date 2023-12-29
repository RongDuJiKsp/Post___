package Controller;


import Model.HistoryStruct;
import Model.HttpMethod;
import com.alibaba.fastjson2.JSONObject;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class HistorySaver {
    private ArrayList<HistoryStruct> historyData;
    private DefaultTableModel dataModel;

    HistorySaver() {
        dataModel = new DefaultTableModel();
        dataModel.addColumn("Time");
        dataModel.addColumn("Url");
        dataModel.addColumn("Method");
        dataModel.addColumn("Status");
    }

    public File getChosenData(int index) throws IOException {
        HistoryStruct toSave = historyData.get(index);
        File tmpFile = null;
        if (toSave.getHttpMethod() == HttpMethod.Get) {
            tmpFile = File.createTempFile("HttpGet-" + toSave.getHttpResponseData().getStatusLine().getStatusCode() + "-" + toSave.getHttpResponseData().getStatusLine().getReasonPhrase() + Math.floor(Math.random() * 10000), "json");
        } else {
            tmpFile = File.createTempFile("HttpPost-" + toSave.getHttpResponseData().getStatusLine().getStatusCode() + "-" + toSave.getHttpResponseData().getStatusLine().getReasonPhrase() + Math.floor(Math.random() * 10000), "json");
        }
        tmpFile.deleteOnExit();
        try (FileOutputStream fileOutputStream = new FileOutputStream(tmpFile)) {
            fileOutputStream.write(JSONObject.from(toSave).toJSONBBytes());
        }
        return tmpFile;
    }

    synchronized public void addData(HistoryStruct historyStruct) {
        historyData.add(historyStruct);
        Vector<String> col = new Vector<>();
        col.add(historyStruct.getSendDate().toString());
        if (historyStruct.getHttpMethod() == HttpMethod.Get) {
            col.add(historyStruct.getHttpGetData().getURI().getHost());
            col.add(HttpMethod.Get.getValue());
        } else {
            col.add(historyStruct.getHttpPostData().getURI().getHost());
            col.add(HttpMethod.Post.getValue());
        }
        col.add(historyStruct.getHttpResponseData().getStatusLine().toString());
    }


}
