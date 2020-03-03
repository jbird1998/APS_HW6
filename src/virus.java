import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class virus {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(line.nextToken());
        int M = Integer.parseInt(line.nextToken());

        ArrayList<ArrayList<Integer>> offices = new ArrayList<>(M);
        for (int i = 0; i < M; i++) {
            offices.add(new ArrayList<>());
        }

        StringTokenizer[] awayMonks = new StringTokenizer[N];
        int[] arriveTimes = new int[N];
        boolean[] arrived = new boolean[N];
        boolean[] blocked = new boolean[N];
        int arrivedCount = 0;

        for (int i = 0; i < N; i++) {
            awayMonks[i] = new StringTokenizer(reader.readLine());
            arriveTimes[i] = Integer.parseInt(awayMonks[i].nextToken());
        }

        ArrayList<ArrayList<Integer>> monks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            monks.add(new ArrayList<>());
        }

        int t = -1;
        int visitsComplete = 0;
        int headPointer;
        ArrayList<Integer> list;
        ArrayList<Integer> office;
        while (visitsComplete != M || arrivedCount != N) {
            t++;
            visitsComplete = 0;
            if (arrivedCount < N) {
                for (int i = 0; i < N; i++) {
                    if (!arrived[i] && arriveTimes[i] == t) {
                        arrived[i] = true;
                        arrivedCount++;
                        int bound = Integer.parseInt(awayMonks[i].nextToken());
                        ArrayList<Integer> officesToVisit = monks.get(i);
                        for (int j = 0; j < bound; j++) {
                            officesToVisit.add(Integer.parseInt(awayMonks[i].nextToken()));
                        }
                    }
                }
            }
            for (int i = 0; i < N; i++) { // add and then remove
                list = monks.get(i);
                if (!list.isEmpty()) {
                    if (!blocked[i]) {
                        blocked[i] = true;
                        headPointer = list.get(0);
                        office = offices.get((headPointer - 1));
                        office.add(i);
                        list.remove(0);
                    }
                }
            }
            for (int i = 0; i < M; i++) {
                list = offices.get(i);
                //System.out.println(list);
                if (!list.isEmpty()) {
                    blocked[list.get(0)] = false;
                    list.remove(0);
                } else {
                    visitsComplete++;
                }
            }
            //System.out.println("\n\n");
        }
        System.out.println(t);
    }
}
