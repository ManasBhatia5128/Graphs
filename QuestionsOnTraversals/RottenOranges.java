package QuestionsOnTraversals;

import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int e1;
    int e2;

    Pair(int e1, int e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
}

public class RottenOranges {
    public static void main(String[] args) {
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(rottenOrangeTime(grid));
    }

    static int rottenOrangeTime(int[][] grid) {
        // Method implementation goes here
        int timeCount = 0;
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] rotten = new boolean[n][m];
        Queue<Pair> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    rotten[i][j] = true;
                    que.add(new Pair(i, j));
                }
            }
        }
        que.add(new Pair(-1, -1));
        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { -1, 1, 0, 0 };
        while (!que.isEmpty() || (que.size() == 1 && que.peek().e1 == -1)) {
            Pair poppedPair = que.poll();
            int e1 = poppedPair.e1;
            int e2 = poppedPair.e2;
            if(e1 == -1 && e2 == -1){
                if(!que.isEmpty()){
                    timeCount++;
                    que.add(new Pair(-1,-1));
                }
            }
            for (int k = 0; k < 4; k++) {
                int newX = e1 + dx[k];
                int newY = e2 + dy[k];
                if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                    if(grid[newX][newY] == 1){
                        grid[newX][newY] = 2;
                        que.add(new Pair(newX, newY));
                        rotten[newX][newY] = true;
                    }
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    return -1;
                }
            }
        }
        return timeCount;
    }
}