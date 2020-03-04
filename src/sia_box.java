import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class sia_box {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        int N = Integer.parseInt(line);
        boolean isStack = true, isQueue = true, isPQueue = true;

        try {
            PriorityQueue<Integer> p_queue = new PriorityQueue<>(new max_comp());
            ArrayList<Integer> queue = new ArrayList<>();
            Stack<Integer> stack = new Stack<>();

            StringTokenizer tokens;
            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(reader.readLine());
                int op = Integer.parseInt(tokens.nextToken());
                int num = Integer.parseInt(tokens.nextToken());
                if (op == 1) {
                    p_queue.offer(num);
                    queue.add(num);
                    stack.push(num);
                } else {
                    if (stack.pop() != num) {
                        isStack = false;
                    }
                    if (p_queue.poll() != num) {
                        isPQueue = false;
                    }
                    if (queue.remove(0) != num) {
                        isQueue = false;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("impossible");
            return;
        }

        if (isStack && !isQueue && !isPQueue) {
            System.out.println("stack");
        } else if (isQueue && !isPQueue && !isStack) {
            System.out.println("queue");
        } else if (isPQueue && !isQueue && !isStack) {
            System.out.println("priority queue");
        } else if (!isQueue && !isPQueue) {
            System.out.println("impossible");
        } else {
            System.out.println("not sure");
        }
    }

    public static class max_comp implements Comparator<Integer> {

        @Override
        public int compare(Integer i1, Integer i2) {
            return -1*i1.compareTo(i2);
        }
    }
}
