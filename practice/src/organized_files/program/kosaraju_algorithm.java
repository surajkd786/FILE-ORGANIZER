import java.io.*;
import java.util.*;

public class kosaraju_algorithm {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");
        int vtces = Integer.parseInt(st[0]);
        int edges = Integer.parseInt(st[1]);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vtces; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]) - 1;
            int v = Integer.parseInt(parts[1]) - 1;
            graph.get(u).add(v);
        }
        System.out.println(kosaraju_algo(graph, vtces));
    }

    public static int kosaraju_algo(ArrayList<ArrayList<Integer>> graph, int vtces) {
        boolean[] vis = new boolean[vtces];
        Stack<Integer> st = new Stack<>();
        // step 1:- applying dfs and adding vertices into a stack
        for (int i = 0; i < vtces; i++) {
            if (vis[i] == false) {
                dfs1(graph, i, st, vis);
            }
        }
        // step2:- creating reversed egde graph/transpose graph
        ArrayList<ArrayList<Integer>> ngraph = new ArrayList<>();
        boolean[] visited = new boolean[vtces];

        for (int i = 0; i < vtces; i++) {
            ngraph.add(new ArrayList<>());
        }
        for (int i = 0; i < vtces; i++) {
            for (Integer target : graph.get(i)) {
                ngraph.get(target).add(i);
            }
        }
        // step 3:-apllying dfs on the vertices present on stack and count the component
        int component = 0;
        while (st.size() > 0) {
            int cur = st.pop();
            if (visited[cur] == false) {
                dfs2(ngraph, cur, visited);
                component++;
            }
        }

        return component;
    }

    public static void dfs1(ArrayList<ArrayList<Integer>> graph, int src, Stack<Integer> st, boolean[] vis) {
        vis[src] = true;
        for (Integer nbr : graph.get(src)) {
            if (vis[nbr] == false) {
                dfs1(graph, nbr, st, vis);
            }
        }
        st.push(src);
    }

    public static void dfs2(ArrayList<ArrayList<Integer>> graph, int src, boolean[] vis) {
        vis[src] = true;
        for (Integer nbr : graph.get(src)) {
            if (vis[nbr] == false) {
                dfs2(graph, nbr, vis);
            }
        }

    }
}