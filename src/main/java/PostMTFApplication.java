import Controller.ArgsAnalysisFunc;
import View.Window.MainWindow;

public class PostMTFApplication {


    public static void main(String[] args) {
        ArgsAnalysisFunc.analysis(args);
        new MainWindow();
    }


}
