package QuestionsOnTraversals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BPGraph {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>();
        adj1.add(new ArrayList<>(List.of(1, 3))); // 0
        adj1.add(new ArrayList<>(List.of(0, 2))); // 1
        adj1.add(new ArrayList<>(List.of(1, 3))); // 2
        adj1.add(new ArrayList<>(List.of(0, 2))); // 3
        // Bipartite ✅
        ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
        adj2.add(new ArrayList<>(List.of(1, 2))); // 0
        adj2.add(new ArrayList<>(List.of(0, 2))); // 1
        adj2.add(new ArrayList<>(List.of(0, 1))); // 2
        // Not Bipartite ❌

        ArrayList<ArrayList<Integer>> adj3 = new ArrayList<>();
        adj3.add(new ArrayList<>(List.of(1))); // 0
        adj3.add(new ArrayList<>(List.of(0))); // 1
        adj3.add(new ArrayList<>(List.of(3))); // 2
        adj3.add(new ArrayList<>(List.of(2, 4))); // 3
        adj3.add(new ArrayList<>(List.of(3))); // 4
        // Bipartite ✅

        ArrayList<ArrayList<Integer>> adj4 = new ArrayList<>();
        adj4.add(new ArrayList<>(List.of(1))); // 0
        adj4.add(new ArrayList<>(List.of(0))); // 1
        adj4.add(new ArrayList<>(List.of(3, 4))); // 2
        adj4.add(new ArrayList<>(List.of(2, 4))); // 3
        adj4.add(new ArrayList<>(List.of(2, 3))); // 4
        // Not Bipartite ❌

        ArrayList<ArrayList<Integer>> adj5 = new ArrayList<>();
        adj5.add(new ArrayList<>(List.of(1, 2))); // 0
        adj5.add(new ArrayList<>(List.of(0, 3, 4))); // 1
        adj5.add(new ArrayList<>(List.of(0))); // 2
        adj5.add(new ArrayList<>(List.of(1))); // 3
        adj5.add(new ArrayList<>(List.of(1))); // 4
        // Bipartite ✅

        System.out.println(bipartiteDFS(adj4));
    }

    static boolean bipartiteBFS(ArrayList<ArrayList<Integer>> adj) {
        int[] visited = new int[adj.size()];
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < adj.size(); i++) {
            if (visited[i] == 0) {
                que.add(i);
                visited[i] = 1;
                while (!que.isEmpty()) {
                    int elem = que.poll();
                    for (int no : adj.get(elem)) {
                        if (visited[no] == 0) {
                            que.add(no);
                            visited[no] = visited[elem] == 1 ? -1 : 1;
                        } else if (visited[no] == visited[elem]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    static boolean bipartiteDFS(ArrayList<ArrayList<Integer>> adj) {
        int[] visited = new int[adj.size()];
        for (int i = 0; i < adj.size(); i++) {
            if (visited[i] == 0) {
                if (!bipartiteDFSHelper(adj, visited, i, -1)) { // this line is required, we have to consider all possibilites of disconnected graphs
                    return false;
                }
            }
        }
        return true;
    }

    static boolean bipartiteDFSHelper(ArrayList<ArrayList<Integer>> adj, int[] visited, int currNode, int prevNode) {
        if (visited[currNode] == 0) {
            visited[currNode] = (prevNode == -1) ? 1 : (visited[prevNode] == 1 ? -1 : 1);
        }

        for (int elem : adj.get(currNode)) {
            if (visited[elem] == 0) {
                if (!bipartiteDFSHelper(adj, visited, elem, currNode)) {
                    return false;
                }
            } else if (visited[elem] == visited[currNode]) {
                return false;
            }
        }

        return true;
    }

}
