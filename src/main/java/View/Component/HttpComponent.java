/*
 * Created by JFormDesigner on Fri Nov 17 22:25:10 CST 2023
 */

package View.Component;

import View.FunctionalComponent.KeyValueListAdderComponent;

import javax.swing.*;
import java.awt.*;

/**
 * @author rdjks
 */
public class HttpComponent extends JPanel {
    public HttpComponent() {
        initComponents();
    }

    private void init() {

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tableScrollPane = new JScrollPane();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {35, 34, 153, 176, 142, 114, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 169, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        add(tableScrollPane, new GridBagConstraints(0, 2, 6, 2, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane tableScrollPane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
