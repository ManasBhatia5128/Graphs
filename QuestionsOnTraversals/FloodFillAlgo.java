package QuestionsOnTraversals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FloodFillAlgo {
    public static void main(String[] args) {
        int[][] grid = { { 1, 1, 1 },
                { 1, 1, 0 },
                { 1, 0, 1 }
        };
        int[][] ans = floodFill(grid, 1, 1, 2);
        for (int[] elem : ans) {
            System.out.println(Arrays.toString(elem));
        }
    }

    static void dfs(int[][] image, int x, int y, int oldColor, int newColor) {
        int n = image.length;
        int m = image[0].length;
        if (image[x][y] != oldColor) {
            return;
        }
        image[x][y] = newColor;
        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { 1, -1, 0, 0 };
        for (int k = 0; k < 4; k++) {
            int newX = x + dx[k];
            int newY = y + dy[k];
            if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                dfs(image, newX, newY, oldColor, newColor);
            }
        }
    }

    static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int oldColor = image[sr][sc];
        dfs(image, sr, sc, oldColor, color);
        return image;
    }

    static int[][] flood(int[][] image, int sr, int sc, int color) {
        int n = image.length;
        int m = image[0].length;
        int[][] ansMatrix = image.clone(); // deep copy ie actuallly copying elements
        boolean[][] visited = new boolean[n][m];
        Queue<Pair> que = new LinkedList<>();
        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { 1, -1, 0, 0 };
        int oldColor = image[sr][sc];
        ansMatrix[sr][sc] = color;
        visited[sr][sc] = true;
        que.add(new Pair(sr, sc));
        while (!que.isEmpty()) {
            Pair poppedPair = que.poll();
            int newX = poppedPair.e1;
            int newY = poppedPair.e2;
            
        }

    }

}
