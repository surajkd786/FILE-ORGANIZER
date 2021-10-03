
import java.io.*;
import java.util.*;

public class articulation_point {
    static int[] parent;
    static int[] discovery;
    static int[] low;
    static int time;
    static boolean[] vis;
    static boolean[] articulation_point;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] part = br.readLine().split(" ");
        int vtces = Integer.parseInt(part[0]);
        int edges = Integer.parseInt(part[1]);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vtces; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]) - 1;
            int v = Integer.parseInt(parts[1]) - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        parent = new int[vtces];
        parent[0] = -1;
        discovery = new int[vtces];
        low = new int[vtces];
        time = 0;
        vis = new boolean[vtces];
        articulation_point = new boolean[vtces];

        dfs(0, graph);
        int ans = 0;
        for (int j = 0; j < vtces; j++) {
            if (articulation_point[j] == true) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    public static void dfs(int src, ArrayList<ArrayList<Integer>> graph) {
        discovery[src] = low[src] = time;
        time++;
        vis[src] = true;
        int count = 0;
        ArrayList<Integer> nbr = graph.get(src);
        for (int i = 0; i < nbr.size(); i++) {
            int v = nbr.get(i);

            if (parent[src] == v) {
                continue;
            } else if (vis[v] == true) {
                low[src] = Math.min(low[src], discovery[v]);
            } else {
                parent[v] = src;
                count++;
                dfs(v, graph);
                low[src] = Math.min(low[src], low[v]);
                if (parent[src] == -1) {
                    if (count >= 2) {
                        articulation_point[src] = true;
                    }
                } else {
                    if (low[v] >= discovery[src]) {
                        articulation_point[src] = true;
                    }
                }
            }
        }
    }
}