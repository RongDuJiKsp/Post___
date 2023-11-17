package View.FunctionalComponent;

import Model.ActionCallback;

import javax.swing.*;
import java.util.List;

public class SelectItemComponent extends JMenu {
    private ActionCallback actionCallback;

    public SelectItemComponent(List<String> selectComponentItems) {
        for (String selectComponentItem : selectComponentItems) {
            JMenuItem jMenuItem = new JMenuItem(selectComponentItem);
            jMenuItem.addActionListener(actionEvent -> {
                setText(((JMenuItem) actionEvent.getSource()).getText());
                if (actionCallback != null) actionCallback.callback(actionEvent);
            });
            add(jMenuItem);
        }

    }

    public SelectItemComponent(List<String> selectComponentItems, String initItem) {
        this(selectComponentItems);
        setText(initItem);
    }

    public SelectItemComponent(List<String> selectComponentItems, String initItem, ActionCallback actionCallback) {
        this(selectComponentItems, initItem);
        this.actionCallback = actionCallback;
    }
}
