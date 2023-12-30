package Controller;

import View.ViewConfig;

public class ArgsAnalysisFunc {
    static MusicPlayer musicPlayer = null;

    public static void analysis(String[] args) {
        for (String arg : args) {
            switch (arg) {
                case "--music" -> musicArg();
                case "--no-buf-arg" -> setBufferedArg();
                default -> throw new RuntimeException("Illegal parameter!");
            }

        }
    }

    private static void musicArg() {
        if (musicPlayer != null) return;
        musicPlayer = new MusicPlayer(ArgsAnalysisFunc.class.getClassLoader().getResourceAsStream("UnrealSuperhero3_Loop.wav"));
        musicPlayer.setLoop(true).setVolume(3).play();
    }

    private static void setBufferedArg() {
        ViewConfig.isUsingBufferedFile = false;
    }

}
