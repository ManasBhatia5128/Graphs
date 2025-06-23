package TopologicalSortAndProblems;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class ShortestPathDAG {
    public static void main(String[] args) {
        int V = 4, E = 2;
        int[][] edges = { { 0, 1, 2 }, { 0, 2, 1 } };
        int sourceNode = 0;
        System.out.println(Arrays.toString(shortestPath(V, E, edges, sourceNode)));
    }

    static int[] shortestPath(int V, int E, int[][] edges, int sourceNode) {
        // Code here
        boolean[] visited = new boolean[V];
        Stack<Integer> topoOrder = new Stack<>();
        int[] dist = new int[V];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[sourceNode] = 0;
        ArrayList<ArrayList<int[]>> adj = makeWeightedAdj(V, E, edges);
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfsTopo(adj, i, visited, topoOrder);
            }
        }
        while (!topoOrder.isEmpty()) {
            int poppedElem = topoOrder.pop();
            int initialDist = dist[poppedElem];
            if(initialDist != Integer.MIN_VALUE){
                for (int[] elem : adj.get(poppedElem)) {
                    int newDist = initialDist + elem[1];
                    dist[elem[0]] = Math.min(dist[elem[0]], newDist);
                }
            }
        }
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                dist[i] = -1;
            }
        }
        return dist;
    }

    static void dfsTopo(ArrayList<ArrayList<int[]>> adj, int currNode, boolean[] visited, Stack<Integer> topoOrder) {
        visited[currNode] = true;
        for (int[] elem : adj.get(currNode)) {
            int neighbour = elem[0];
            if (!visited[neighbour]) {
                dfsTopo(adj, neighbour, visited, topoOrder);
            }
        }

        // After every node is visited
        topoOrder.push(currNode);

    }

    static ArrayList<ArrayList<int[]>> makeWeightedAdj(int V, int E, int[][] edges) {
        ArrayList<ArrayList<int[]>> ans = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            ans.add(new ArrayList<int[]>());
        }
        for (int[] edge : edges) {
            ans.get(edge[0]).add(new int[] { edge[1], edge[2] });
        }
        return ans;
    }
}
