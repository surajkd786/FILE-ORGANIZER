import java.io.*;
import java.util.*;

public class negative_weight_cycle_detection {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int vtces = Integer.parseInt(input[0]);
        int edges = Integer.parseInt(input[1]);
        int[][] graph = new int[edges][3];
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            graph[i][0] = Integer.parseInt(parts[0]);
            graph[i][1] = Integer.parseInt(parts[1]);
            graph[i][2] = Integer.parseInt(parts[2]);
        }
        System.out.println(IsGraphContainNegativeWtCycle(graph, edges, vtces));

    }

    public static int IsGraphContainNegativeWtCycle(int[][] graph, int edges, int vtces) {
        int[] path = new int[vtces];
        for (int i = 0; i < vtces - 1; i++) {
            for (int j = 0; j < edges; j++) {
                int u = graph[j][0];
                int v = graph[j][1];
                int wt = graph[j][2];
                if (path[v] == Integer.MAX_VALUE) {
                    continue;
                }
                if (path[u] + wt < path[v]) {
                    path[v] = path[u] + wt;
                }
            }
        }
        for (int j = 0; j < edges; j++) {
            int u = graph[j][0];
            int v = graph[j][1];
            int wt = graph[j][2];
            if (path[v] == Integer.MAX_VALUE) {
                continue;
            }
            if (path[u] + wt < path[v]) {
                return 1;
            }
        }
        return 0;
    }
}