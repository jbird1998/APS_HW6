import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class daily_prize {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        int N = Integer.parseInt(line);

        Node root = null;
        Node node;
        StringTokenizer tokens;
        long sum = 0;
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(reader.readLine());
            int forms = Integer.parseInt(tokens.nextToken());
            for (int j = 0; j < forms; j++) {
                int val = Integer.parseInt(tokens.nextToken());
                node = new Node(val);
                if (root == null) {
                    root = node;
                } else {
                    Node.add(root, node);
                }
            }
//            System.out.println();
//            Node.inorder(root, 0);
//            System.out.println();
            Node max = Node.max(root);
            if (max == null) {
                continue;
            }
            //System.out.println("Max " + i + ": " + max.value);
            if (root.equals(max)) {
                root = root.left;
                root.parent = null;
            } else {
                max.parent.right = max.left;
                if (max.left != null) {
                    max.left.parent = max.parent;
                }
                max.parent = null;
            }
            Node min = Node.min(root);
            if (min == null) {
                continue;
            }
            //System.out.println("Min " + i + ": " + min.value);
            if (root.equals(min)) {
                root = root.right;
                if (root != null) {
                    root.parent = null;
                }
            } else {
                min.parent.left = min.right;
                if (min.right != null) {
                    min.right.parent = min.parent;
                }
                min.parent = null;
            }
            sum += (max.value - min.value);
        }
        System.out.println(sum);
    }

    public static class Node {
        int value;
        Node parent, right, left;

        Node(int v) {
            this.value = v;
            this.parent = null;
            this.right = null;
            this.left = null;
        }

        static Node max(Node root) {
            if (root == null) {
                return null;
            }
            if (root.right != null) {
                return max(root.right);
            }
            return root;
        }

        static Node min(Node root) {
            if (root == null) {
                return null;
            }
            if (root.left != null) {
                return min(root.left);
            }
            return root;
        }

        static void add(Node root, Node node) {
            if (node.value < root.value) {
                if (root.left == null) {
                    root.left = node;
                    node.parent = root;
                } else {
                    add(root.left, node);
                }
            } else {
                if (root.right == null) {
                    root.right = node;
                    node.parent = root;
                } else {
                    add(root.right, node);
                }
            }
        }

        static void inorder(Node root, int depth) {
            if (root == null) {
                return;
            }
            inorder(root.left, 2*depth + 1);
            System.out.println(root.value + " " + depth);
            inorder(root.right, 2*depth + 2);
        }

    }
}
