package QuestionsOnTraversals;

import java.util.Arrays;

public class FloodFillAlgo {
    public static void main(String[] args) {
        int[][] grid = { { 1, 1, 1 },
                { 1, 1, 0 },
                { 1, 0, 1 }
        };
        int[][] ans = floodFill(grid, 1, 1, 2);
        for(int[] elem: ans){
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
}
