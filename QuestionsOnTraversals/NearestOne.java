package QuestionsOnTraversals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class NearestOne {
    public static void main(String[] args) {
        int[][] grid = { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 0, 1 } };
        int[][] ans = nearest(grid);
        for (int[] elem : ans) {
            System.out.println(Arrays.toString(elem));
        }
    }

    static int[][] nearest(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    int[][] ans = new int[n][m];
    boolean[][] visited = new boolean[n][m];

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (grid[i][j] == 0) {
                ans[i][j] = -1;
            } else {
                ans[i][j] = 0; // distance for 1s is 0
            }
        }
    }

    Queue<Triplet> que = new LinkedList<>();
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (grid[i][j] == 0) {
                que.add(new Triplet(i, j, 1));
                visited[i][j] = true;
            }
        }
    }

    int[] dx = { 0, 0, 1, -1 };
    int[] dy = { 1, -1, 0, 0 };

    while (!que.isEmpty()) {
        Triplet trio = que.poll();
        int x = trio.e1;
        int y = trio.e2;
        int dist = trio.e3;

        for (int k = 0; k < 4; k++) {
            int newX = x + dx[k];
            int newY = y + dy[k];
            if (newX >= 0 && newX < n && newY >= 0 && newY < m && !visited[newX][newY]) {
                if (grid[newX][newY] == 1) {
                    ans[x][y] = dist;
                    break; // Stop further exploration from this 0 cell
                } else {
                    que.add(new Triplet(newX, newY, dist + 1));
                    visited[newX][newY] = true;
                }
            }
        }
    }

    return ans;
}

}

class Triplet {
    int e1;
    int e2;
    int e3;

    Triplet(int e1, int e2, int e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }
}