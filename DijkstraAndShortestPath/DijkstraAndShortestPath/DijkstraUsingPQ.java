package DijkstraAndShortestPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class DijkstraUsingPQ {
    public static void main(String[] args) {
        int V = 3;
        int[][] edges = { { 0, 1, 1 }, { 1, 2, 3 }, { 0, 2, 6 } };
        int src = 2;
        System.out.println(Arrays.toString(dijkstra(V, edges, src)));
    }

    static int[] dijkstra(int V, int[][] edges, int src) {
        // code here
        int[] dist = new int[V];
        ArrayList<ArrayList<int[]>> adj = makeWeightedAdj(V, edges);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;
        minHeap.add(new int[] { 0, src });
        while (!minHeap.isEmpty()) {
            int[] elem = minHeap.poll();
            int initialDist = elem[0];
            int node = elem[1];
            for (int[] pair : adj.get(node)) {
                int addedPathDistance = pair[1];
                int neighbour = pair[0];
                int newDist = initialDist + addedPathDistance;
                if (dist[neighbour] > newDist) {
                    dist[neighbour] = newDist;
                    minHeap.add(new int[] { newDist, neighbour });
                }
            }
        }

        // for disconnected graph:
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                dist[i] = -1;
            }
        }
        return dist;
    }

    static ArrayList<ArrayList<int[]>> makeWeightedAdj(int V, int[][] edges) {
        ArrayList<ArrayList<int[]>> ans = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            ans.add(new ArrayList<int[]>());
        }
        for (int[] edge : edges) {
            ans.get(edge[0]).add(new int[] { edge[1], edge[2] });
            ans.get(edge[1]).add(new int[] { edge[0], edge[2] });
        }
        return ans;
    }
}
