import java.io.*;
import java.util.*;

public class perfect_friend {
    static class Edge {
        int src;
        int nbr;

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int v = 0; v < n; v++) {
            graph[v] = new ArrayList<>();
        }
        for (int i = 0; i < k; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            graph[v1].add(new Edge(v1, v2));
            graph[v2].add(new Edge(v2, v1));
        }
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        boolean [] visited=new boolean[n];
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> comp=new ArrayList<>();
            if (visited[i]==false) {
                drawTreeAndGenerateComp(graph,i,comp,visited);
                comps.add(comp);
            }
        }
        perfectFriend(comps);
    }
    public static void  drawTreeAndGenerateComp(ArrayList<Edge>[] graph,int src, ArrayList<Integer> comp, boolean [] visited){
        visited[src]=true;
        comp.add(src);
        for (Edge e : graph[src]) {
            if (visited[e.nbr]==false) {
                drawTreeAndGenerateComp(graph,e.nbr,comp,visited);
            }
        }
    } 
        
    public static void perfectFriend(ArrayList<ArrayList<Integer>> comps) {
        int count=0;
        for (int i = 0; i < comps.size(); i++) {
            for (int j = i+1; j < comps.size(); j++) {
                count+=comps.get(i).size()*comps.get(j).size();
                
            }
        }
        System.out.println(count);
    }

}