import java.io.*;
import java.util.*;

class bas_routes {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] st = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st[j]);
            }
        }

        String[] st1 = br.readLine().split(" ");
        int src = Integer.parseInt(st1[0]);
        int dest = Integer.parseInt(st1[1]);
        System.out.println(numBusesToDestination(arr, src, dest));

    }

    public static int numBusesToDestination(int[][] routes, int S, int T) {
        int level = 0;
        int N = routes.length;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        // to fill the hashMap with busStop corresponding to buses going in that
        // direction
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                int bus_stop_num = routes[i][j];
                ArrayList<Integer> bas_no = map.getOrDefault(bus_stop_num, new ArrayList<>());
                bas_no.add(i);
                map.put(bus_stop_num, bas_no);
            }
        }
        LinkedList<Integer> q = new LinkedList<>();
        HashSet<Integer> busStopVis = new HashSet<>();
        HashSet<Integer> busVis = new HashSet<>();
        q.addLast(S);
        busStopVis.add(S);
        while (q.size() > 0) {
            int size = q.size();
            while (size-- > 0) {
                int rem = q.removeFirst();
                if (rem == T) {
                    return level;
                }
                ArrayList<Integer> buses = map.get(rem);
                for (int bus : buses) {
                    if (busVis.contains(bus) == true) {
                        continue;
                    }
                    int[] arr = routes[bus];// [{1,2,7},{3,6,7},{1,6}]
                    for (int busStops : arr) {
                        if (busStopVis.contains(busStops) == true) {
                            continue;
                        }
                        q.addLast(busStops);
                        busStopVis.add(busStops);
                    }
                    busVis.add(bus);
                }
            }
            level++;
        }
        return -1;
    }
}
