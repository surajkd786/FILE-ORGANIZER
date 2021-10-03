
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class rotting_oranges {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] st = br.readLine().split(" ");
        int n = Integer.parseInt(st[0]);
        int m = Integer.parseInt(st[1]);

        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st[j]);
            }
        }

        System.out.println(orangesRotting(arr));

    }

    public static class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

    }

    private static int[][] dirs = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static int orangesRotting(int[][] grid) {
        LinkedList<Pair> q = new LinkedList<>();
        int fresh = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    q.addLast(new Pair(i, j));
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        if (fresh == 0) {
            return 0;
        }
        int level = -1;
        while (q.size() > 0) {
            level++;
            int size = q.size();
            while (size-- > 0) {
                Pair rem = q.removeFirst();
                for (int i = 0; i < dirs.length; i++) {
                    int xdash = rem.row + dirs[i][0];
                    int ydash = rem.col + dirs[i][1];
                    if (xdash >= 0 && ydash >= 0 && xdash < grid.length && ydash < grid[0].length
                            && grid[xdash][ydash] == 1) {
                        q.addLast(new Pair(xdash, ydash));
                        grid[xdash][ydash] = 0;
                        fresh--;
                    }
                }
            }
        }
        if (fresh == 0) {
            return level;
        } else {
            return -1;
        }
    }
}
