import Controller.ArgsAnalysisFunc;
import View.Window.MainWindow;

public class PostMTFApplication {


    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
        ArgsAnalysisFunc.analysis(args);
        mw.render();
    }
}
