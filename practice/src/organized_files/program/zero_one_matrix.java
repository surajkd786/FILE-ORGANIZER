import java.io.*;
import java.util.*;

class zero_one_matrix {
  static class Pair {
    int x;
    int y;

    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private static int[][] dirs = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

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

    int[][] ans = updateMatrix(arr);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        System.out.print(ans[i][j] + " ");
      }
      System.out.println();
    }

  }
  /**
   Algorithm-
   (1) mark unvisited(i.e -1) to all 1
  +ve num can be answer
  (2) add index of zeros in queue
  apply BFS simultaneously from all zero present
  (3) (a) find first -1 which is 1 distance away from all the zeros and add indices in queue and change matrix[rem.x][rem.y]+1
  (b) repeat step (a) until any -1 left**/

  public static int[][] updateMatrix(int[][] matrix) {
    LinkedList<Pair> q = new LinkedList<>();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 0) {
          q.addLast(new Pair(i, j));
        } else {
          matrix[i][j] = -1;
        }
      }
    }
    while (q.size() > 0) {
      Pair rem = q.removeFirst();
      for (int i = 0; i < dirs.length; i++) {
        int xdash = rem.x + dirs[i][0];
        int ydash = rem.y + dirs[i][1];
        if (xdash >= 0 && ydash >= 0 && xdash < matrix.length && ydash < matrix[0].length && matrix[xdash][ydash] < 0) {
          matrix[xdash][ydash] = matrix[rem.x][rem.y] + 1;
          q.add(new Pair(xdash, ydash));
        }
      }
    }
    return matrix;
  }
}