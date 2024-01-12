package Controller;


import Model.HistoryStruct;
import Model.HttpMethod;
import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Vector;

@Getter
public class HistorySaver {
    private final ArrayList<HistoryStruct> historyData;
    private final DefaultTableModel dataModel;

    public HistorySaver() {
        historyData = new ArrayList<>();
        dataModel = new DefaultTableModel();
        dataModel.addColumn("Time");
        dataModel.addColumn("Url");
        dataModel.addColumn("Method");
        dataModel.addColumn("Status");
    }

    public File getChosenData(int index) throws IOException {
        return buildFile(historyData.get(index));
    }

    public static File buildFile(HistoryStruct toBuild) throws IOException {
        String filePrefix;
        if (toBuild.getHttpMethod() == HttpMethod.Get) {
            filePrefix = toBuild.getSendDate().replaceAll("[ :]","-") + "-HttpGet-" + toBuild.getHttpResponseData().getStatusLine().getStatusCode() + "-" + toBuild.getHttpResponseData().getStatusLine().getReasonPhrase() + "-" + (int) Math.floor(Math.random() * 10000);
        } else {
            filePrefix = toBuild.getSendDate().replaceAll("[ :]","-") + "-HttpPost-" + toBuild.getHttpResponseData().getStatusLine().getStatusCode() + "-" + toBuild.getHttpResponseData().getStatusLine().getReasonPhrase() + "-" + (int) Math.floor(Math.random() * 10000);
        }
        File tmpFile = Files.createTempFile(filePrefix, ".json").toFile();
        tmpFile.deleteOnExit();
        Files.writeString(tmpFile.toPath(), JSONObject.toJSONString(toBuild));
        return tmpFile;
    }

    synchronized public void addData(HistoryStruct historyStruct) {
        historyData.add(historyStruct);
        Vector<String> col = new Vector<>();
        col.add(historyStruct.getSendDate());
        if (historyStruct.getHttpMethod() == HttpMethod.Get) {
            col.add(historyStruct.getHttpGetData().getURI().getHost());
            col.add(HttpMethod.Get.getValue());
        } else {
            col.add(historyStruct.getHttpPostData().getURI().getHost());
            col.add(HttpMethod.Post.getValue());
        }
        col.add(historyStruct.getHttpResponseData().getStatusLine().toString());
        dataModel.addRow(col);
    }


}
