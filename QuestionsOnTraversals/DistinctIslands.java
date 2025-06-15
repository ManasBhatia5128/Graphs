package QuestionsOnTraversals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class DistinctIslands {
    public static void main(String[] args) {
        // int[][] grid = { { 1, 1, 0, 1, 1 }, { 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1 }, { 1, 1, 0, 1, 1 } };
        int[][] grid = {
    {1, 1, 0, 0},
    {1, 0, 0, 1},
    {1, 1, 0, 1}
};


        System.out.println(distinctIslands(grid));
    }

    static int distinctIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dx = { 0, 0, 1, 1, 1, -1, -1, -1 };
        int[] dy = { 1, -1, 0, 1, -1, 0, 1, -1 };
        boolean[][] visited = new boolean[n][m];
        Queue<PairCase> que = new LinkedList<>();
        HashSet<ArrayList<PairCase>> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    ArrayList<PairCase> list = new ArrayList<>();
                    que.add(new PairCase(i, j));
                    visited[i][j] = true;
                    while (!que.isEmpty()) {
                        PairCase poppedPair = que.poll();
                        list.add(poppedPair);
                        int x = poppedPair.e1;
                        int y = poppedPair.e2;
                        for (int k = 0; k < 8; k++) {
                            int newX = x + dx[k];
                            int newY = y + dy[k];
                            if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                                if (!visited[newX][newY] && grid[newX][newY] == 1) {
                                    que.add(new PairCase(newX, newY));
                                    visited[newX][newY] = true;
                                }
                            }
                        }
                    }
                    // Sorting isn't even required since we follow the same patteren of traversal
                    /* Collections.sort(list, (a, b) -> {
                        if (a.e1 != b.e1)
                            return a.e1 - b.e1;
                        else
                            return a.e2 - b.e2;
                    }); */
                    PairCase minPair = list.get(0);
                    int elem1 = minPair.e1;
                    int elem2 = minPair.e2;
                    for (PairCase pair : list) {
                        pair.e1 -= elem1;
                        pair.e2 -= elem2;
                    }
                    // for (PairCase pair : list) {
                    //     pair.e1 -= minPair.e1;
                    //     pair.e2 -= minPair.e2;
                    // } this won't work, classes in java are pass by value of reference, once minPair becomes (0,0), subtracting this won't help
                    set.add(list);
                    for (int l = 0; l < list.size(); l++) {
                        System.out.print("(" + list.get(l).e1 + ", " + list.get(l).e2 + ")");
                    }
                    System.out.println();
                }
            }
        }

        return set.size();
    }
}

class PairCase {
    int e1, e2;

    PairCase(int e1, int e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PairCase)) return false;
        PairCase p = (PairCase) o;
        return this.e1 == p.e1 && this.e2 == p.e2;
    }

    @Override
    public int hashCode() {
        return 31 * e1 + e2;
    }
}
