import java.io.*;
import java.util.*;

public class hamiltonion_path_and_cycle {
    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int vtces = Integer.parseInt(br.readLine());
        int edges = Integer.parseInt(br.readLine());
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }
        HashSet<Integer> visited = new HashSet<>();
        int src = Integer.parseInt(br.readLine());
        drawTreeAndGenerateComp(graph, src, src + "", visited, src);
    }

    public static void drawTreeAndGenerateComp(ArrayList<Edge>[] graph, int src, String psf, HashSet<Integer> visited,
            int osrc) {

        if (visited.size() == graph.length - 1) {
            System.out.print(psf);
            boolean hamiltonion = false;
            for (Edge e : graph[src]) {
                if (e.nbr == osrc) {
                    hamiltonion = true;
                    break;
                }
            }
            if (hamiltonion) {
                System.out.println("*");
            } else {
                System.out.println(".");
            }
            return;
        }
        visited.add(src);
        for (Edge e : graph[src]) {
            if (visited.contains(e.nbr) == false) {
                drawTreeAndGenerateComp(graph, e.nbr, psf + e.nbr, visited, osrc);

            }
        }
        visited.remove(src);

    }
}