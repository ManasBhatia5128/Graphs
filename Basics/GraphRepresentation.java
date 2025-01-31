import java.util.Arrays;

public class GraphRepresentation {
    public static void main(String[] args) {
        int n = 5;
        // int m = 6;
        int[][] adj = new int[n+1][n+1];
        int[][] edges = { { 1, 2 }, { 1, 3 }, { 3, 4 }, { 2, 4 }, { 2, 5 }, { 4, 5 } };
        for(int[] elem: edges){
            adj[elem[0]][elem[1]] = 1;
            adj[elem[1]][elem[0]] = 1;
        }
        for(int[] elem: adj){
            System.out.println(Arrays.toString(elem));
        }
    }
}
