import Controller.MusicPlayer;
import View.Window.MainWindow;

public class PostMTFApplication {
    static MusicPlayer musicPlayer = null;

    private static void musicArg() {
        if (musicPlayer != null) return;
        musicPlayer = new MusicPlayer(PostMTFApplication.class.getClassLoader().getResourceAsStream("UnrealSuperhero3_Loop.wav"));
        musicPlayer.setLoop(true).setVolume(3).play();
    }

    public static void main(String[] args) {
        for (String arg : args) {
            switch (arg) {
                case "--music" -> musicArg();
                default -> {
                }
            }

        }
        new MainWindow();
    }


}
