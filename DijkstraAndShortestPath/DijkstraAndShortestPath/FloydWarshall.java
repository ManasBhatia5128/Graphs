package DijkstraAndShortestPath;

import java.util.Arrays;

public class FloydWarshall {
    public static void main(String[] args) {
        
    }
    static void floydWarshall(int[][] dist) {
        // Code here
        int V = dist.length;
        int maxNo = (int) 1e8;
        for(int v = 0; v < V; v++){
            for(int i = 0; i < V; i++){
                for(int j = 0; j < V; j++){
                    if(i != j && i != v && j != v){
                        if(dist[i][v] != maxNo && dist[v][j] != maxNo){
                            int newDist = dist[i][v] + dist[v][j];
                            if(newDist < dist[i][j]){
                                dist[i][j] = newDist;
                            }
                        }
                    }
                }
            }
        }
        for(int[] elem: dist){
            System.out.println(Arrays.toString(elem));
        }
    }
}
