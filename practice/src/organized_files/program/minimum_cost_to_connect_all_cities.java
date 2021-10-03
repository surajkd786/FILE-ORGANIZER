import java.io.*;
import java.util.*;

public class minimum_cost_to_connect_all_cities {
    static class Edge implements Comparable<Edge> {
        int v;
        int wt;

        Edge(int nbr, int wt) {
            this.v = nbr;
            this.wt = wt;
        }

        @Override
        public int compareTo(Edge o) {
            return this.wt - o.wt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int vtces = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < vtces; i++) {
            graph.add(new ArrayList<>());
        }

        int edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph.get(v1).add(new Edge(v2, wt));
            graph.get(v2).add(new Edge(v1, wt));
        }
        System.out.println(findMinimumCost(graph,vtces));
    }
    static int cost;
    public static int findMinimumCost(ArrayList<ArrayList<Edge>> graph,int vtces){
        //creating a visited Array and priorityQueue and adding starting vertex
        boolean [] vis=new boolean[vtces];
        PriorityQueue<Edge> pq=new PriorityQueue<>();
        pq.add(new Edge(0, 0));
        cost=0;
        while (pq.size()>0) {
            Edge rem=pq.remove();
            if (vis[rem.v]==true) {
                continue;
            }
            vis[rem.v]=true;
            cost=cost+rem.wt;
            //visiting neighbour and adding into priority queue
            for (Edge nbr : graph.get(rem.v)) {
                if (vis[nbr.v]==false) {
                    pq.add(nbr);
                }
            }
        }
        return cost;
    }

}
