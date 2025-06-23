package TopologicalSortAndProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class KahnSort {
    public static void main(String[] args) {
        int V = 6;
        int[][] edges = { { 5, 0 }, { 4, 0 }, { 5, 2 }, { 2, 3 }, { 3, 1 }, { 4, 1 } };
        System.out.println(Arrays.toString(kahnSortBFSWithoutVisited(V, edges)));
    }

    static int[] kahnSortBFSWithoutVisited(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = directedAdjacencyList(V, edges);
        Queue<Integer> que = new LinkedList<>();
        int[] ans = new int[V];
        int[] inDegree = new int[V];
        for (ArrayList<Integer> list : adj) {
            for (int elem : list) {
                inDegree[elem]++;
            }
        }
        int cnt = 0;
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                que.add(i);
            }
        }
        while (!que.isEmpty()) {
            int elem = que.poll();
            ans[cnt] = elem;
            cnt++;
            for(int num: adj.get(elem)){
                inDegree[num]--;
                if(inDegree[num] == 0){
                    que.add(num);
                }
            }
        }
        return ans;
    }

    static int[] kahnSortBFS(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = directedAdjacencyList(V, edges);
        Queue<Integer> que = new LinkedList<>();
        int[] ans = new int[V];
        int[] inDegree = new int[V];
        boolean[] visited = new boolean[V];
        for (ArrayList<Integer> list : adj) {
            for (int elem : list) {
                inDegree[elem]++;
            }
        }
        int cnt = 0;
        while (true) {
            for (int i = 0; i < V; i++) {
                if (inDegree[i] == 0 && !visited[i]) {
                    que.add(i);
                    visited[i] = true;
                }
            }
            if (que.isEmpty()) {
                break;
            }
            int elem = que.poll();
            ans[cnt] = elem;
            cnt++;
            for (int num : adj.get(elem)) {
                inDegree[num]--;
            }
        }
        return ans;
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
}
