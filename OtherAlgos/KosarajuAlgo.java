package OtherAlgos;

import java.util.Stack;
import java.util.ArrayList;
public class KosarajuAlgo {
    public static void main(String[] args) {
        int V = 8;
        int[][] edges = {{0,1},{1,2},{2,0},{2,3},{3,4},{4,5},{5,6},{6,7},{4,7},{6,4}};
        ArrayList<ArrayList<Integer>> adj = edgesIntoAdj(V, edges);
        System.out.println(kosajaru(adj));
    }
    static int kosajaru(ArrayList<ArrayList<Integer>> adj){
        int ans = 0;
        int V = adj.size();
        boolean[] visited = new boolean[V];
        Stack<Integer> finishTime = new Stack<>();
        for(int i = 0; i < V; i++){
            if(!visited[i]){
                dfsTime(adj, visited, i, finishTime); // TC = O(V+E), for each node you are checking all its edges, not 2E, its for DG only
            }
        }
        ArrayList<ArrayList<Integer>> reversedList = reversedAdjList(adj); // O(V + E), firsly for initialisng new list then for iterating on edges
        boolean[] newVisited = new boolean[V];
        while(!finishTime.isEmpty()){ // O(V+E) net because its just a dfs
            int source = finishTime.pop();
            if(!newVisited[source]){
                dfs(reversedList, newVisited, source);
                ans++;
            }
        }
        return ans;
    }
    static void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int currNode){
        visited[currNode] = true;
        for(int elem: adj.get(currNode)){
            if(!visited[elem]){
                dfs(adj, visited, elem);
            }
        }
    }
    static void dfsTime(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int currNode, Stack<Integer> finishTime){
        visited[currNode] = true;
        for(int elem: adj.get(currNode)){
            if(!visited[elem]){
                dfsTime(adj, visited, elem, finishTime);
            }
        }
        finishTime.add(currNode);
    }
    static ArrayList<ArrayList<Integer>> reversedAdjList(ArrayList<ArrayList<Integer>> adj){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int V = adj.size();
        for (int i = 0; i < V; i++) {
            ans.add(new ArrayList<>());
        }
        for(int i = 0; i < V; i++){
            for(int j = 0; j < adj.get(i).size(); j++){
                ans.get(adj.get(i).get(j)).add(i);
            }
        }
        return ans;
    }
    static ArrayList<ArrayList<Integer>> edgesIntoAdj(int V, int[][] edges){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            ans.add(new ArrayList<>());
        }
        for(int[] edge: edges){
            ans.get(edge[0]).add(edge[1]);
        }
        return ans;
    }
}
