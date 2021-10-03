import java.io.*;
import java.util.*;

public class pepcoder_and_reversing {

    static class Pair {
        int v;
        int wt;

        Pair(int v, int wt) {
            this.v = v;
            this.wt = wt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int vtces = Integer.parseInt(input[0]);
        int edges = Integer.parseInt(input[1]);
        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < vtces; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]) - 1;
            int v2 = Integer.parseInt(parts[1]) - 1;
            graph.get(v1).add(new Pair(v2, 0));
            graph.get(v2).add(new Pair(v1, 1));// reverse of each edge
        }
        LinkedList<Pair> queue = new LinkedList<>();
        boolean[] visited = new boolean[vtces];

        queue.addLast(new Pair(0, 0));
        while (queue.size() > 0) {
            Pair rem = queue.removeFirst();
            if (rem.v == vtces - 1) {
                System.out.println(rem.wt);
                return;
            }
            if (visited[rem.v] == true) {
                continue;
            }
            visited[rem.v] = true;
            for (Pair nbr : graph.get(rem.v)) {
                if (visited[nbr.v] == false) {
                    if (nbr.wt == 0) {
                        queue.addFirst(new Pair(nbr.v, rem.wt + 0));
                    } else {
                        queue.addLast(new Pair(nbr.v, rem.wt + 1));
                    }
                }
            }

        }
        System.out.println("-1");
    }
}