import java.util.ArrayList;

public class DFS {
    public static void main(String[] args) {
        int V = 8;
        int[][] edges = { { 1, 2 }, { 1, 3 }, { 2, 5 }, { 2, 6 }, { 3, 7 }, { 3, 4 }, { 7, 8 }, { 4, 8 } };
        ArrayList<ArrayList<Integer>> adj = makeAdjList(V, edges);
        boolean[] visited = new boolean[V + 1];
        ArrayList<Integer> ans = new ArrayList<>();
        dfsTraversal(1, adj, visited, ans);
        System.out.println(ans);

    }

    static void dfsTraversal(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> ans){
        if(visited[node]){
            return;
        }
        visited[node] = true;
        ans.add(node);
        for(int elem: adj.get(node)){
            if(!visited[elem]){
                dfsTraversal(elem, adj, visited, ans);
            }
        }
    }

    static ArrayList<ArrayList<Integer>> makeAdjList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();
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
