package View.FunctionalComponent;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class KeyValueListAdderComponent extends DefaultTableModel {
    private HashMap<String, String> key_valuePair;

    public KeyValueListAdderComponent() {
        super(new String[]{"Key", "Value"}, 1);
    }

    public void addKeyValue(String key, String value) {
        key_valuePair.put(key, value);
        addRow(new String[]{key, value});
    }

    public HashMap<String, String> getKey_ValuePair() {
        return key_valuePair;
    }

}
