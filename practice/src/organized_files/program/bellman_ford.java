import java.io.*;

import java.util.*;

public class bellman_ford {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int vtces = Integer.parseInt(input[0]);
		int edges = Integer.parseInt(input[1]);
		int[][] graph = new int[edges][3];
		for (int i = 0; i < edges; i++) {
			String[] parts = br.readLine().split(" ");
			graph[i][0] = Integer.parseInt(parts[0]) - 1;
			graph[i][1] = Integer.parseInt(parts[1]) - 1;
			graph[i][2] = Integer.parseInt(parts[2]);
			
		}
		int[] path = new int[vtces];
		Arrays.fill(path, Integer.MAX_VALUE);
		path[0] = 0;
		BellManFord(graph, path, edges,vtces);

	}

	public static void BellManFord(int[][] graph, int[] path, int edges,int vtces) {
		for (int i = 0; i <
		 vtces - 1; i++) {

			for (int j = 0; j < edges; j++) {
				int u = graph[j][0];
				int v = graph[j][1];
				int wt = graph[j][2];
				if (path[u] == Integer.MAX_VALUE) {
					continue;
				}
				if (path[u] + wt < path[v]) {
					path[v] = path[u] + wt;
				}
			}
		}
		for (int i = 1; i < vtces; i++) {
			if (path[i] != Integer.MAX_VALUE) {
				System.out.print(path[i] + " ");
			} else {
				System.out.print(1000000000);
			}

		}
		System.out.println();

	}
}