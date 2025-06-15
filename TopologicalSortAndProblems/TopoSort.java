package TopologicalSortAndProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class TopoSort {
    public static void main(String[] args) {
        int V = 6;
        int[][] edges = { { 5, 0 }, { 4, 0 }, { 5, 2 }, { 2, 3 }, { 3, 1 }, { 4, 1 } };
        System.out.println(Arrays.toString(topoSortDFS(V, edges)));
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

    static int[] topoSortDFS(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = directedAdjacencyList(V, edges);
        boolean[] visited = new boolean[V];
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfsHelper(adj, visited, st, i);
            }
        }
        for (int i = 0; i < ans.length; i++) {
            ans[i] = st.pop();
        }
        return ans;
    }

    static void dfsHelper(ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> ans, int currNode) {
        // if (!visited[currNode]) { // redundant as we are checking !visited inside for loop
            visited[currNode] = true;
        // }
        for (int elem : adj.get(currNode)) {
            if (!visited[elem]) {
                dfsHelper(adj, visited, ans, elem);
            }
        }
        ans.add(currNode);
    }
}
