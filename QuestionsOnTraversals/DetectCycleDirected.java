package QuestionsOnTraversals;

import java.util.ArrayList;

public class DetectCycleDirected {
    public static void main(String[] args) {
        int V = 4;
        int[][] edges = {
                { 0, 1 },
                { 2, 3 }
        };

        System.out.println(detectCycleDirected(V, edges));

    }

    static ArrayList<ArrayList<Integer>> directedAdjacencyList(int V, int[][] edges) {
        // Observe that if vertices are 2 then nodes should be 0,1 only not any value
        ArrayList<ArrayList<Integer>> ansList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            ansList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            ansList.get(edge[0]).add(edge[1]);
        }
        return ansList;
    }

    static boolean detectCycleDirected(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = directedAdjacencyList(V, edges);
        boolean[] visited = new boolean[adj.size()];
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                if (dfsHelper(adj, visited, i)) { // you can' return directly if a cycle is not found in first
                                                  // component, it won't check 2nd component then
                    return true;
                }
            }
        }
        return false;
    }

    static boolean dfsHelper(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int currNode) {
        if (!visited[currNode]) {
            visited[currNode] = true;
        }
        for (int elem : adj.get(currNode)) {
            if (visited[elem]) {
                return true;
            }
            if (dfsHelper(adj, visited, elem)) {
                return true;
            }
        }
        return false;
    }
}