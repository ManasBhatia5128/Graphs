package TopologicalSortAndProblems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DetectCycleDirectedTopo {
    static boolean detectCycle(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = directedAdjacencyList(V, edges);
        Queue<Integer> que = new LinkedList<>();
        int ansCnt  = 0;
        int[] inDegree = new int[V];
        for (ArrayList<Integer> list : adj) {
            for (int elem : list) {
                inDegree[elem]++;
            }
        }
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                que.add(i);
            }
        }
        while (!que.isEmpty()) {
            int elem = que.poll();
            ansCnt++;
            for(int num: adj.get(elem)){
                inDegree[num]--;
                if(inDegree[num] == 0){
                    que.add(num);
                }
            }
        }
        return ansCnt < V;
    }
    static ArrayList<ArrayList<Integer>> directedAdjacencyList(int V, int[][] edges) {
        // Observe that if vertices are 2 then nodes should be 0,1 only not any value
        ArrayList<ArrayList<Integer>> ansList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            ansList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            ansList.get(edge[1]).add(edge[0]);
        }
        return ansList;
    }
}
