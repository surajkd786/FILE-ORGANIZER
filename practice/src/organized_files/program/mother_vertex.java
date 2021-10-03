import java.io.*;
import java.util.*;

public class mother_vertex {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // steps to take graph as input
        String[] parts = br.readLine().split(" ");
        int vtces = Integer.parseInt(parts[0]);
        int edges = Integer.parseInt(parts[1]);

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vtces; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges; i++) {
            String[] part = br.readLine().split(" ");
            int u = Integer.parseInt(part[0]) - 1;
            int v = Integer.parseInt(part[1]) - 1;
            graph.get(u).add(v);
        }
        System.out.println(findMotherVertex(graph, vtces));

    }

    // solution of mother vertex
    public static int findMotherVertex(ArrayList<ArrayList<Integer>> graph, int vtces) {
        boolean[] vis = new boolean[vtces];
        Stack<Integer> st = new Stack<>();
        // step 1: applying dfs and fill the stack in ranom order
        for (int i = 0; i < vtces; i++) {
            if (vis[i] == false) {
                dfs(graph, i, st, vis);
            }
        }
        // step 2: to check whether stack top element is mother vertex or not
        int ans = st.pop();
        count = 0;
        boolean[] visited = new boolean[vtces];
        if (visited[ans] == false) {
            dfs(graph, ans, visited);
        }
        if (count == vtces) {
            return ans + 1;
        } else {
            return -1;
        }

    }

    static int count;

    public static void dfs(ArrayList<ArrayList<Integer>> graph, int src, Stack<Integer> st, boolean[] vis) {
        vis[src] = true;
        for (Integer nbr : graph.get(src)) {
            if (vis[nbr] == false) {
                dfs(graph, nbr, st, vis);
            }
        }
        st.push(src);
    }

    public static void dfs(ArrayList<ArrayList<Integer>> graph, int src, boolean[] vis) {
        vis[src] = true;
        for (Integer nbr : graph.get(src)) {
            if (vis[nbr] == false) {
                dfs(graph, nbr, vis);
            }
        }
        count++;
    }
}