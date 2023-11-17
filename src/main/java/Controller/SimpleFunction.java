package Controller;

import java.io.InputStream;
import java.util.Scanner;

public class SimpleFunction {
    public static String readInputToStrLine(InputStream inputStream){
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) stringBuilder.append(scanner.nextLine());
        return  stringBuilder.toString();
    }
}
