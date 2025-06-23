package DisjointSet;

import java.util.Arrays;

public class KruskalAlgo {
    public static void main(String[] args) {
        int[][] edges = { { 1, 1, 4 }, { 2, 1, 2 }, { 3, 2, 3 }, { 3, 2, 4 }, { 4, 1, 5 }, { 5, 3, 4 }, { 7, 2, 6 },
                { 8, 3, 6 }, { 9, 4, 5 } };
        int[][] ans = findMSTUsingDJSet(6, edges);
        for (int[] elem : ans) {
            System.out.println(Arrays.toString(elem));
        }
    }

    static int[][] findMSTUsingDJSet(int V, int[][] edges) {
        int[][] ans = new int[V - 1][3];
        int count = 0;
        int sum = 0;
        DisjointSet ds = new DisjointSet(V); // O(V) SC
        // Arrays.sort(edges, (a,b) -> a[0] - b[0]); // might cause integer overflow
        // TC = O(E)
        Arrays.sort(edges, (a, b) -> Integer.compare(a[0], b[0]));
        // TC: O(E x 4 x alpha)
        for (int[] edge : edges) {
            if(ds.findUlimateParent(edge[1]) != ds.findUlimateParent(edge[2])){
                ds.unionBySize(edge[1], edge[2]);
                ans[count] = new int[]{edge[0], edge[1], edge[2]};
                sum += edge[0];
                count++;
            }
        }
        System.out.println(sum);
        return ans;
    }
}
