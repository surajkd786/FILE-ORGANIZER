
import java.util.*;
import java.io.*;

public class as_far_from_land_as_possible {

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

        System.out.println(maxDistance(arr));

    }

    private static int[][] dirs = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public static int maxDistance(int[][] grid) {
        int level = -1;
        LinkedList<Pair> q = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    q.addLast(new Pair(i, j));
                }
            }
        }
        if (q.size() == 0 || q.size() == grid.length * grid[0].length) {
            return -1;
        }
        while (q.size() > 0) {
            level++;
            int size = q.size();
            while (size-- > 0) {
                Pair rem = q.removeFirst();
                for (int i = 0; i < 4; i++) {
                    int xdash = rem.row + dirs[i][0];
                    int ydash = rem.col + dirs[i][1];
                    if (xdash >= 0 && ydash >= 0 && xdash < grid.length && ydash < grid[0].length
                            && grid[xdash][ydash] == 0) {
                        q.addLast(new Pair(xdash, ydash));
                        grid[xdash][ydash] = 1;
                    }
                }
            }

        }
        return level;

    }

    public static class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}

/*
(1) add index of all 1's in a queue
(2) apply DFS on each item of queue and find nearest distance of 1 from zeors and mark visited
    i.e grid[xdash][ydash]=1;
    */
    