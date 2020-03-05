import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class generate_test_daily_prize {

    public static void main(String[] args) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        int randNum;
        int randLen;
        int a = (int)(Math.random()*500 + 1);
        lines.add(""+a);
        for (int i = 1; i <= a; i++) {
            randLen = (int)(Math.random()*10000 + 1);
            String line = "" + randLen;
            for (int j = 0; j < randLen; j++) {
                randNum = (int)(Math.random()*1000000 + 1);
                line += " " + randNum;
                System.out.println(j + " " + i);
            }
            lines.add(line);
        }
        Path file = Paths.get("test1.txt");
        Files.write(file, lines, StandardCharsets.UTF_8);
    }
}
