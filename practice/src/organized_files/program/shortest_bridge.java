
import java.io.*;
import java.util.*;

public class shortest_bridge {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] st = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st[j]);
            }
        }

        System.out.println(shortestBridge(arr));

    }

    public static class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static int[][] dirs = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public static int shortestBridge(int[][] A) {
        LinkedList<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[A.length][A[0].length];
        boolean flag = false;
        for (int i = 0; i < A.length && !flag; i++) {
            for (int j = 0; j < A[0].length && !flag; j++) {
                if (A[i][j] == 1) {

                    dfs(i, j, A, visited, q);
                    flag = true;
                }
            }
        }
        int level = 0;
        while (q.size() > 0) {
            int size = q.size();
           
            while (size-- > 0) {
                Pair rem = q.removeFirst();
                for (int i = 0; i < 4; i++) {
                    int xdash = rem.row + dirs[i][0];
                    int ydash = rem.col + dirs[i][1];
                    if (xdash < 0 || ydash < 0 || xdash >= A.length || ydash >= A[0].length
                            || visited[xdash][ydash] == true) {
                        continue;
                    }
                    if (A[xdash][ydash] == 1)
                        return level;

                    visited[xdash][ydash] = true;
                    q.addLast(new Pair(xdash, ydash));

                }
            }
            level++;
            
        }
        return level;

    }

    public static void dfs(int r, int c, int[][] arr, boolean[][] visited, LinkedList<Pair> q) {
        if (r<0 || c<0 || r>=arr.length|| c>=arr[0].length || visited[r][c]==true || arr[r][c]==0) {
            return;
        }
        visited[r][c] = true;
        q.addLast(new Pair(r,c));
        dfs(r-1, c, arr, visited, q);
        dfs(r, c+1, arr, visited, q);
        dfs(r+1, c, arr, visited, q);
        dfs(r, c-1, arr, visited, q);
        return;
    }
}
