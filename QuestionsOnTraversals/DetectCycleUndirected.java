package QuestionsOnTraversals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DetectCycleUndirected {
    public static void main(String[] args) {
        int V = 4;
        int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 } };
        // System.out.println(detectCycleUndirectedBfs(V, edges));
        
        System.out.println(detectCycleUndirectedDfs(V, edges));
    }

    static boolean detectCycleUndirectedDfs(int V, int[][] edges){
        ArrayList<ArrayList<Integer>> list = makeAdjList(V, edges);
        boolean[] visited = new boolean[V];
        for(int i = 0; i < V; i++){
            if(!visited[i]){
                if(dfsHelper(V, list, visited, i, -1)){
                    return true;
                }
            }
        }
        return false;
    }

    static boolean detectCycleUndirectedBfs(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> list = makeAdjList(V, edges);
        Queue<Pair> que = new LinkedList<>();
        boolean[] visited = new boolean[V];
        for (int node = 0; node < V; node++) {
            if (!visited[node]) {
                que.add(new Pair(node, -1));
                visited[node] = true;
                while (!que.isEmpty()) {
                    Pair elem = que.poll();
                    int num = elem.e1;
                    int prev = elem.e2;
                    for (int number : list.get(num)) {
                        if (number != prev) {
                            if (visited[number]) {
                                return true;
                            }
                            que.add(new Pair(number, num));
                            visited[number] = true;
                        }
                    }
                }
            }
        }
        return false;
    }
    static boolean dfsHelper(int V, ArrayList<ArrayList<Integer>> list, boolean[] visited, int currNode, int prevNode){
        if(visited[currNode]){
            return true;
        }
        visited[currNode] = true;
        for(int elem: list.get(currNode)){
            if(elem != prevNode){
                boolean ans =  dfsHelper(V, list, visited, elem, currNode);
                if(ans){
                    return true;
                }
            }
        }
        return false;
    }

    static ArrayList<ArrayList<Integer>> makeAdjList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> mainList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= V; i++) {
            mainList.add(new ArrayList<>());
        }
        for (int[] elem : edges) {
            mainList.get(elem[0]).add(elem[1]);
            mainList.get(elem[1]).add(elem[0]); // if it is a directed graph you must not add this statement since the
                                                // direction is from elem[0] -> elem[1]
        }
        return mainList;
    }
}
