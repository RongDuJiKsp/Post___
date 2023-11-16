package View;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public final class ViewConfig {
    public static void initUITheme() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
