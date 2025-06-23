package TopologicalSortAndProblems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AlienDictionary {
    public static void main(String[] args) {
        String[] words = { "baa", "abcd", "abca", "cab", "cad" };
        System.out.println(findOrder(words));
    }

    static String findOrder(String[] words) {
        // code here
        StringBuilder ansString = new StringBuilder();
        boolean[][] visitedGrid = new boolean[26][26];
        boolean[] visited = new boolean[26];
        ArrayList<int[]> edges = new ArrayList<>();
        for (int i = 0; i < words.length - 1; i++) {
            String str1 = words[i];
            String str2 = words[i + 1];
            int ptr1 = 0;
            int ptr2 = 0;
            while (ptr1 < str1.length() && ptr2 < str2.length()) {
                int charA = str1.charAt(ptr1) - 'a';
                int charB = str2.charAt(ptr2) - 'a';
                if (charA != charB) {
                    if (!visitedGrid[charA][charB]) {
                        int[] edge = new int[] { charA, charB };
                        edges.add(edge);
                        visited[charA] = true;
                        visited[charB] = true;
                        visitedGrid[charA][charB] = true;
                        break;
                    }
                }
                ptr1++;
                ptr2++;
            }
            if (ptr1 == str1.length() && ptr2 == str2.length())
                continue;

            if (ptr2 == str2.length()) {
                return ""; // word2 is a prefix of word1 and appears after → invalid
            }
        }

        // int nodeCount = 0;
        // for (boolean bool : visited) {
        // if (bool) {
        // nodeCount++;
        // }
        // } // what if nodes are b, c, d, e: node count will be 4, but elements should
        // start from 1 not 0
        int[] integerAns = kahnSortBFSWithoutVisited(26, edges);
        for (int val : integerAns) {
            if (visited[val]) {
                ansString.append((char) (val + 'a'));
            }
        }
        return ansString.toString();
    }

    static ArrayList<ArrayList<Integer>> directedAdjacencyList(int V, ArrayList<int[]> edges) {
        // Observe that if vertices are 2 then nodes should be 0,1 only not any value
        ArrayList<ArrayList<Integer>> ansList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            ansList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            ansList.get(edge[0]).add(edge[1]);
        }
        return ansList;
    }

    static int[] kahnSortBFSWithoutVisited(int V, ArrayList<int[]> edges) {
        ArrayList<ArrayList<Integer>> adj = directedAdjacencyList(V, edges);
        Queue<Integer> que = new LinkedList<>();
        int[] ans = new int[V];
        int[] inDegree = new int[V];
        for (ArrayList<Integer> list : adj) {
            for (int elem : list) {
                inDegree[elem]++;
            }
        }
        int cnt = 0;
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                que.add(i);
            }
        }
        while (!que.isEmpty()) {
            int elem = que.poll();
            ans[cnt] = elem;
            cnt++;
            for (int num : adj.get(elem)) {
                inDegree[num]--;
                if (inDegree[num] == 0) {
                    que.add(num);
                }
            }
        }
        return ans;
    }

}
