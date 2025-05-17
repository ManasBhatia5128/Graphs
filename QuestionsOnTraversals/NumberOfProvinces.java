package QuestionsOnTraversals;

import java.util.ArrayList;

public class NumberOfProvinces {
    static ArrayList<ArrayList<Integer>> convertMatrixToList(int[][] matrix){
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int V = matrix.length;
        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0; i < V; i++){
            for(int j = 0; j < V; j++){
                if(matrix[i][j] == 1){
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }
        return adj;
    }

    static boolean dfsTraversal(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> ans){
        if(visited[node]){
            return false;
        }
        visited[node] = true;
        ans.add(node);
        for(int elem: adj.get(node)){
            if(!visited[elem]){
                dfsTraversal(elem, adj, visited, ans);
            }
        }
        return true;
    }

    static int countProvinces(ArrayList<ArrayList<Integer>> adj){
        boolean[] visited = new boolean[adj.size()];
        int count = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        int i = 0;
        while(true){
            if(allVisited(visited)){
                break;
            }
            dfsTraversal(i, adj, visited, ans);
            count++;
            i++;
        }
        return count;
    }
    static boolean allVisited(boolean[] visited){
        for(boolean elem: visited){
            if(elem == false){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[][] matrix = {{1,0,0},{0,1,0},{0,0,1}};
        ArrayList<ArrayList<Integer>> adj = convertMatrixToList(matrix);
        System.out.println(countProvinces(adj));
    }
}