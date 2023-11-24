/*
 * Created by JFormDesigner on Fri Nov 24 08:59:53 CST 2023
 */

package View.Component;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author rdjks
 */
public class ConfigSettingComponent extends JPanel {
    public ConfigSettingComponent() {
        initComponents();
    }

    public RequestConfig buildRequestConfig() throws UnknownHostException, NumberFormatException {
        RequestConfig.Builder builder = RequestConfig.custom();
        if (!isEnableConfigButton.isSelected()) return builder.build();
        if (!inputConnectionRequestTimeout.getText().isEmpty())
            builder.setConnectionRequestTimeout(Integer.parseInt(inputConnectionRequestTimeout.getText()));
        if (!inputConnectTimeout.getText().isEmpty())
            builder.setConnectTimeout(Integer.parseInt(inputConnectTimeout.getText()));
        if (!inputSocketTimeout.getText().isEmpty())
            builder.setSocketTimeout(Integer.parseInt(inputSocketTimeout.getText()));
        if (!inputLocalAddress.getText().isEmpty())
            builder.setLocalAddress(InetAddress.getByName(inputLocalAddress.getText()));
        if (!inputProxy.getText().isEmpty())
            builder.setProxy(HttpHost.create(inputProxy.getText()));
        return builder
                .setNormalizeUri(isNormalizeUriButton.isSelected())
                .setAuthenticationEnabled(isAuthenticationEnabledButton.isSelected())
                .setContentCompressionEnabled(isContentCompressionEnabledButton.isSelected())
                .setCircularRedirectsAllowed(isCircularRedirectsAllowedButton.isSelected())
                .build();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        isEnableConfigButton = new JToggleButton();
        label1 = new JLabel();
        inputConnectionRequestTimeout = new JTextField();
        label2 = new JLabel();
        inputConnectTimeout = new JTextField();
        label3 = new JLabel();
        inputSocketTimeout = new JTextField();
        label4 = new JLabel();
        inputLocalAddress = new JTextField();
        label5 = new JLabel();
        inputProxy = new JTextField();
        isCircularRedirectsAllowedButton = new JToggleButton();
        isAuthenticationEnabledButton = new JToggleButton();
        isContentCompressionEnabledButton = new JToggleButton();
        isNormalizeUriButton = new JToggleButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 196, 208, 36, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 37, 0, 0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- isEnableConfigButton ----
        isEnableConfigButton.setText("Enable Config");
        add(isEnableConfigButton, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- label1 ----
        label1.setText("ConnectionRequestTimeout");
        add(label1, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(inputConnectionRequestTimeout, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText("ConnectTimeout");
        add(label2, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(inputConnectTimeout, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label3 ----
        label3.setText("SocketTimeout");
        add(label3, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(inputSocketTimeout, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label4 ----
        label4.setText("LocalAddressHost");
        add(label4, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(inputLocalAddress, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label5 ----
        label5.setText("Proxy");
        add(label5, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(inputProxy, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- isCircularRedirectsAllowedButton ----
        isCircularRedirectsAllowedButton.setText("CircularRedirectsAllowed");
        add(isCircularRedirectsAllowedButton, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- isAuthenticationEnabledButton ----
        isAuthenticationEnabledButton.setText("AuthenticationEnabled");
        add(isAuthenticationEnabledButton, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- isContentCompressionEnabledButton ----
        isContentCompressionEnabledButton.setText("ContentCompressionEnabled");
        add(isContentCompressionEnabledButton, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));

        //---- isNormalizeUriButton ----
        isNormalizeUriButton.setText("NormalizeUri");
        add(isNormalizeUriButton, new GridBagConstraints(2, 9, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JToggleButton isEnableConfigButton;
    private JLabel label1;
    private JTextField inputConnectionRequestTimeout;
    private JLabel label2;
    private JTextField inputConnectTimeout;
    private JLabel label3;
    private JTextField inputSocketTimeout;
    private JLabel label4;
    private JTextField inputLocalAddress;
    private JLabel label5;
    private JTextField inputProxy;
    private JToggleButton isCircularRedirectsAllowedButton;
    private JToggleButton isAuthenticationEnabledButton;
    private JToggleButton isContentCompressionEnabledButton;
    private JToggleButton isNormalizeUriButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
