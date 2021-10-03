import java.io.*;
import java.util.*;

public class number_of_islands {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[m][n];

        for (int i = 0; i < arr.length; i++) {
            String parts = br.readLine();
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = Integer.parseInt(parts.split(" ")[j]);
            }
        }

        // write your code here
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        int count=0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (visited[i][j] == false && arr[i][j] == 0) {
                    drawTreeAndGenerateComp(arr, i, j, visited);
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static void drawTreeAndGenerateComp(int[][] arr, int i, int j, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length || visited[i][j] == true || arr[i][j] == 1) {
            return;
        }
        visited[i][j]=true;
        drawTreeAndGenerateComp(arr, i - 1, j, visited);//north
        drawTreeAndGenerateComp(arr, i, j + 1, visited);//east
        drawTreeAndGenerateComp(arr, i + 1, j, visited);//south
        drawTreeAndGenerateComp(arr, i, j - 1, visited);//west
    }
}
