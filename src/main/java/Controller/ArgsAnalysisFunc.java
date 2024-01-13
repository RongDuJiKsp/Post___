package Controller;

import View.ViewConfig;
import View.Window.ExceptionDialog;
import View.Window.MainWindow;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class ArgsAnalysisFunc {
    private static MusicPlayer musicPlayer = null;
    @Setter
    private static MainWindow mainWindowRef;

    public static void analysis(String[] args) {

        Iterator<String> argIt = Arrays.stream(args).iterator();
        while (argIt.hasNext()) {
            String arg = argIt.next();
            try {
                switch (arg) {
                    case "--music" -> musicArg();
                    case "--no-buf-arg" -> setBufferedArg();
                    case "--history" -> addHistoryArg(argIt.next());
                    default -> throw new RuntimeException("Illegal parameter!");
                }
            } catch (Exception e) {
                new ExceptionDialog(mainWindowRef, "An exception occurred while parsing user parameters. exception param: " + arg + " ,exception : " + e).render();
            }
        }


    }

    private static void musicArg() {
        if (musicPlayer != null) return;
        musicPlayer = new MusicPlayer(ArgsAnalysisFunc.class.getClassLoader().getResourceAsStream("UnrealSuperhero3_Loop.wav"));
        musicPlayer.setLoop(true).setVolume(4).play();
    }

    private static void setBufferedArg() {
        ViewConfig.isUsingBufferedFile = false;
    }

    private static void addHistoryArg(String fileName) throws IOException {
        ViewConfig.addedUsersHistory = true;
        mainWindowRef.getMainPage().readFileAndUpdate(new File(fileName));
    }

}
