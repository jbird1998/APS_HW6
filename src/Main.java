import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] rank;
    static int[] sets;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(line.nextToken());
        double D = Double.parseDouble(line.nextToken());
        int constCount = 0;
        boolean newConstellation;

        sets = new int[N];
        rank = new int[N];
        double[] xCoords = new double[N];
        double[] yCoords = new double[N];

        for (int i = 0; i < N; i++) {
            sets[i] = i;
        }

        for (int i = 0; i < N; i++) {
            newConstellation = true;
            line = new StringTokenizer(reader.readLine());
            xCoords[i] = Double.parseDouble(line.nextToken());
            yCoords[i] = Double.parseDouble(line.nextToken());
            for (int j = 0; j < i; j++) {
                double dist = Math.sqrt(Math.pow(xCoords[i] - xCoords[j], 2) + Math.pow(yCoords[i] - yCoords[j], 2));
                if (dist <= D) {
                    union(i, j);
                    newConstellation = false;
                    break;
                }
            }
            if (newConstellation) {
                constCount++;
            }
        }

        System.out.print(constCount);
    }

    public static int find(int i) {
        // Returns the index
        if (sets[i] == i) {
            return i;
        }
        sets[i] = find(sets[i]);
        return sets[i];
    }

    public static void union(int a, int b) {
        int aP = find(a);
        int bP = find(b);
        if (aP == bP) {
            return;
        }
        if (rank[aP] < rank[bP]) {
            sets[aP] = bP;
        } else if (rank[bP] < rank[aP]) {
            sets[bP] = aP;
        } else {
            sets[bP] = aP;
            rank[aP] += 1;
        }
    }
}
