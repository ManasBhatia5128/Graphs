package QuestionsOnTraversals;

import java.util.LinkedList;
import java.util.Queue;

public class NmberOfIslands {
    public static void main(String[] args) {
        char[][] grid = {
                { 'L', 'L', 'L' },
                { 'L', 'L', 'L' },
                { 'L', 'L', 'L' }
        };
        // Expected Output: 1
        int[][] matrix = { { 1, 1, 0 }, { 1, 0, 0 }, { 0, 0, 1 } };

        // System.out.println(noOfIslandsBrute(grid));
        System.out.println(islands(matrix));
    }

    static int islands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        Queue<Pair> que = new LinkedList<>();
        int[] dx = { -1, -1, -1, 1, 1, 1, 0, 0 };
        int[] dy = { -1, 0, 1, -1, 0, 1, -1, 1 };
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    if (!visited[i][j]) {
                        que.add(new Pair(i, j));
                        visited[i][j] = true;
                        count++;
                        while (!que.isEmpty()) {
                            Pair elem = que.poll();
                            for (int k = 0; k < 8; k++) {
                                int newX = elem.e1 + dx[k];
                                int newY = elem.e2 + dy[k];
                                if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                                    if(grid[newX][newY] == 1 && !visited[newX][newY]){
                                        que.add(new Pair(newX, newY));
                                        visited[newX][newY] = true;
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        return count;
    }

    static int noOfIslandsBrute(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        Queue<Pair> queue = new LinkedList<>();
        int[] dx = { -1, 0, 1, 0, -1, -1, 1, 1 };
        int[] dy = { 0, 1, 0, -1, -1, 1, -1, 1 };

        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'L' && !visited[i][j]) {
                    count++;
                    visited[i][j] = true;
                    queue.add(new Pair(i, j));
                    while (!queue.isEmpty()) {
                        Pair poppedPair = queue.poll();
                        int e1 = poppedPair.e1;
                        int e2 = poppedPair.e2;

                        for (int k = 0; k < dx.length; k++) {
                            int newX = e1 + dx[k];
                            int newY = e2 + dy[k];
                            if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                                if (grid[newX][newY] == 'L' && !visited[newX][newY]) {
                                    visited[newX][newY] = true;
                                    queue.add(new Pair(newX, newY));
                                }
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}

class Pair {
    int e1;
    int e2;

    Pair(int e1, int e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
}