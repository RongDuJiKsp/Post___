import View.Page.MainPage.MainPage;
import View.MainWindow;

public class PostMTFApplication {
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.add(new MainPage(mainWindow));
        mainWindow.setVisible(true);
    }
}
