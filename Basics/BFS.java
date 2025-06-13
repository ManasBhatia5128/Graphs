import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    static List<Integer> breadthFirstList(int V, List<List<Integer>> adj){
        List<Integer> ans = new ArrayList<>();
        boolean[] visited = new boolean[V+1];
        Queue<Integer> que = new LinkedList<>();
        que.add(1);
        visited[1] = true;
        while (!que.isEmpty()) {
            int elem = que.poll();
            ans.add(elem);
            for(int no: adj.get(elem)){
                if(!visited[no]){
                    que.add(no);
                    visited[no] = true;
                }
            }
        }

        return ans;
    }

    static List<List<Integer>> makeAdjList(int V, int[][] edges) {
        List<List<Integer>> mainList = new ArrayList<>();
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

    static void printLevelwise(int V, List<List<Integer>> adj) {
        Queue<Integer> que = new LinkedList<>();
        int[] visitedVertices = new int[V + 1];
        que.add(1);
        visitedVertices[1] = 1;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {// it will only run for that fixed size, see that size is not getting updated in each iteration
                int elem = que.poll();
                System.out.print(elem + " ");
                for (int num : adj.get(elem)) {
                    if (visitedVertices[num] == 0) {
                        visitedVertices[num] = 1;
                        que.add(num);
                    }
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 9;
        int[][] edges = { { 1, 2 }, { 1, 6 }, { 2, 3 }, { 2, 4 }, { 6, 7 }, { 6, 9 }, { 4, 5 }, { 7, 8 }, { 5, 8 } };
        List<List<Integer>> adj = makeAdjList(n, edges);
        // System.out.println(breadthFirstPrint(n, adj));
        // printLevelwise(n, adj);
        List<Integer> ans = breadthFirstList(n, adj);
        System.out.println(ans);
    }
}
