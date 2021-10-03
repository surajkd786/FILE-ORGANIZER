import java.util.*;
import java.io.*;

class number_of_enclaves {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] st = br.readLine().split(" ");
        int m = Integer.parseInt(st[0]);
        int n = Integer.parseInt(st[1]);

        int[][] arr = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st[j]);
            }
        }

        System.out.println(numEnclaves(arr));

    }

    public static int numEnclaves(int[][] arr) {
        int countEnclave = 0;
        //dfs on boundry element i.e 1
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (i == 0 || j == 0 || i == arr.length - 1 || j == arr[0].length - 1 )
                if(arr[i][j]==1)
                    dfs(arr, i, j);
            }

        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 1) {
                    countEnclave++;
                }
            }

        }
        return countEnclave;
    }

    public static void dfs(int[][] arr, int r, int c) {
        if (r < 0 || c < 0 || r >= arr.length || c >= arr[0].length || arr[r][c] == 0) {
            return;
        }
        arr[r][c] = 0;
        // up
        dfs(arr, r - 1, c);
        // down
        dfs(arr, r + 1, c);
        // left
        dfs(arr, r, c - 1);
        // right
        dfs(arr, r, c + 1);
    }

}