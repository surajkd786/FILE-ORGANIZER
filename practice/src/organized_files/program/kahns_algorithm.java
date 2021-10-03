import java.io.*;
import java.util.*;

public class kahns_algorithm {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int vtces = Integer.parseInt(input[0]);
        int edges = Integer.parseInt(input[1]);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vtces; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            graph.get(v).add(u);
        }
        int [] ans=findOrder( graph,vtces);
        for (int i : ans) {
            System.out.print(i+" ");
        }
    }
    public static int[] findOrder(ArrayList<ArrayList<Integer>> graph,int vtces){
        int [] indegree=new int[vtces];
        int []ans=new int[vtces];
        // (1) computing indegree(i.e number of incoming edges) for each of the vertex in DAG and initialise the count of visited nodes as 0
        for (int i = 0; i < vtces; i++) {
            for (int nbr : graph.get(i)) {
                indegree[nbr]++;
            }
        }
        //(2) Pick all the vertices with indegree as 0 and add them into a queue
        LinkedList<Integer> queue=new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i]==0) {
                queue.addLast(i);   
            }
        }
        //(3) Remove a vertex from the queue and then
        //(a) Decrease in-degree by 1 for all its neighbouring nodes
        //(b) if indegree of neighbouring nodes is reduced to zero, then add it to the queue.
        // (4) reppeat step 3 untill the queue is empty
        int idx=0;//count
        while (queue.size()>0) {
            int rem=queue.removeFirst();
            ans[idx]=rem;
            idx++;
            for (int nbr : graph.get(rem)) {
                indegree[nbr]--;
                if (indegree[nbr]==0) { 
                    queue.addLast(nbr);
                }
            }
        }
        //(5) if count of visited nodes is not equal to the number of nodes in graph then topological sorting is not possible for given graph
        if (idx==vtces) {
            return ans;
        }else{
            return new int[]{-1};
        }
    }
}