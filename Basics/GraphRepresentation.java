import java.util.ArrayList;
import java.util.Arrays;

public class GraphRepresentation {
    public static void main(String[] args) {

        // M-1) Using adjacency matrix
        int n = 5;
        // int m = 6;
        // int[][] adj = new int[n+1][n+1];
        int[][] edges = { { 1, 2 }, { 1, 3 }, { 3, 4 }, { 2, 4 }, { 2, 5 }, { 4, 5 } };
        int[][] edgesWithWeights = {{ 1, 2, 1 }, { 1, 3, 4 }, { 3, 4, 5 }, { 2, 4, 2 }, { 2, 5, 7 }, { 4, 5, 3 }};
        /*
        for(int[] elem: edges){
            adj[elem[0]][elem[1]] = 1;
            adj[elem[1]][elem[0]] = 1;
        }
        for(int[] elem: adj){
            System.out.println(Arrays.toString(elem));
        }
             */

        // M-2) Using adjcency arraylist
        ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            mainList.add(new ArrayList<>());
        }
        for(int[] elem: edges){
            mainList.get(elem[0]).add(elem[1]);
            mainList.get(elem[1]).add(elem[0]); // if it is a directed graph you must not add this statement since the direction is from elem[0] -> elem[1]
        }
        System.out.println(mainList);

        // How to store weighted graph
        ArrayList<ArrayList<int[]>> mainListWithWeights = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            mainListWithWeights.add(new ArrayList<>());
        }
        for(int[] elem: edgesWithWeights){
            int[] pair1 = {elem[1], elem[2]};
            int[] pair2 = {elem[0], elem[2]};
            mainListWithWeights.get(elem[0]).add(pair1);
            mainListWithWeights.get(elem[1]).add(pair2);
        }
        for(ArrayList<int[]> arr: mainListWithWeights){
            if(!arr.isEmpty()){
                System.out.print(Arrays.toString(arr.get(0)));
                System.out.print(Arrays.toString(arr.get(1)));
            }
            System.out.println();
        }
    }
}
