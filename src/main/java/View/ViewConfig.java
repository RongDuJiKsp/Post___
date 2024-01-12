package View;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public final class ViewConfig {
    public static boolean isUsingBufferedFile = true;
    public static final boolean isUsingShower = true;
    public static boolean addedUsersHistory=false;

    public static void initUITheme() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ignored) {

        }
    }

}
