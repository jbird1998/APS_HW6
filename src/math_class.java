import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class math_class {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        ArrayList<String> numList = new ArrayList<>(N);
        StringTokenizer line = new StringTokenizer(reader.readLine());
        int length = 0;

        while(line.hasMoreTokens()) {
            String str = line.nextToken();
            numList.add(str);
            length += str.length();
        }

        Collections.sort(numList, new arrangeNums());

        StringBuilder builder = new StringBuilder(length);
        for (int i = N - 1; i >= 0; i--) {
            builder.append(numList.get(i));
        }

        System.out.println(builder.toString());
    }

    public static class arrangeNums implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            int len = s1.length() + s2.length();
            StringBuilder b1 = new StringBuilder(len);
            StringBuilder b2 = new StringBuilder(len);
            b1.append(s1);
            b2.append(s2);
            b1.append(s2);
            b2.append(s1);
            return b1.toString().compareTo(b2.toString());
        }
    }
}
