package DisjointSet;

import java.util.ArrayList;
import java.util.List;

public class DisjointSetClass {
    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(7);
        ds.unionByRank(1, 2);
        ds.unionByRank(2, 3);
        ds.unionByRank(4, 5);
        ds.unionByRank(6, 7);
        ds.unionByRank(5, 6);
        ds.unionByRank(3, 7);
        System.out.println(ds.findUlimateParent(3) == ds.findUlimateParent(7) ? "Same" : "Not Same");
    }
}

class DisjointSet{
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    public DisjointSet(int n){
        for(int i = 0; i <= n; i++){
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
    }
    public int findUlimateParent(int node){
        if(node == parent.get(node)){
            return node;
        }
        int ulp = findUlimateParent(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node); // or ulp
    }
    public void unionByRank(int u, int v){
        int ulp_u = findUlimateParent(u);
        int ulp_v = findUlimateParent(v);
        if(ulp_u == ulp_v) return;
        if(rank.get(ulp_u) < rank.get(ulp_v)){
            parent.set(ulp_u, ulp_v);
        }
        else if(rank.get(ulp_v) < rank.get(ulp_u)){
            parent.set(ulp_v, ulp_u);
        }
        else{
            parent.set(ulp_v, ulp_u);
            int rankU = rank.get(ulp_u);
            rank.set(ulp_u, rankU + 1);

        }
    }
    public void unionBySize(int u, int v){
        int ulp_u = findUlimateParent(u);
        int ulp_v = findUlimateParent(v);
        if(size.get(ulp_u) < size.get(ulp_v)){
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_u) + size.get(ulp_v));
        }
        else{
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_v) + size.get(ulp_u));
        }

    }
}
// Both unions have same TC O(4alpha)
// size is more intutive because it is not distorted
// rank is maxHeight which later gets changed