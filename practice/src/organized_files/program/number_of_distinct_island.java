import java.io.*;
import java.util.*;

class number_of_distinct_island {

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

        System.out.println(numDistinctIslands(arr));

    }

    public static StringBuilder psf = new StringBuilder();

    public static void dfs(int[][] arr, int r, int c) {

        arr[r][c] = 0;
        // up
        if (r - 1 >= 0 && arr[r - 1][c] == 1) {
            psf.append("u");
            dfs(arr, r - 1, c);
        }

        // right
        if (c + 1 < arr[0].length && arr[r][c + 1] == 1) {
            psf.append("r");
            dfs(arr, r, c + 1);
        }

        // down
        if (r + 1 < arr.length && arr[r + 1][c] == 1) {
            psf.append("d");
            dfs(arr, r + 1, c);
        }

        // left
        if (c - 1 >= 0 && arr[r][c - 1] == 1) {
            psf.append("l");
            dfs(arr, r, c - 1);
        }

        psf.append("z");
    }

    public static int numDistinctIslands(int[][] arr) {
        // write your code here
        HashSet<String> set = new HashSet<>();
        if (arr == null || arr.length < 1 || arr[0].length < 1)
        return 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                psf = new StringBuilder();
                if (arr[i][j] == 1) {
                    psf.append("x");
                    dfs(arr, i, j);
                    set.add(psf.toString());
                }
            }
        }
        return set.size();
    }
}
/** 
Algorithm-
(1) Apply DFS on every 1 staring from top left corner (after mark visited) on the four directions and append x to the StringBuilder
(2) Adding u-> up,r->right,l->left,d->down in a StringBuilder a/c to DFS call and append z if we backtrack
(3) After completeing all DFS call add this into HashSet after converting it to String
(4) apply 1,2, until no 1 left
(5) At the end return size of HashSet
*/