package com.bit.iv.algorithms.generate.maze;

import java.util.Random;

public class DivisionAlgorithm implements MazeGeneratorAlgorithm {
    private Random random;

    @Override
    public boolean[][] generateMaze(int n) {
        random = new Random();
        boolean[][] grid = new boolean[n][n];
        for(int i = 1; i < n - 1; i++) {
            for(int j = 1; j < n - 1; j++) {
                grid[j][i] = true;
            }
        }

        divide(grid, 1, 1, n - 2, n  - 2);
        //find exit
        for(int i = 1; i < n; i++) {
            if(grid[1][i]) {
                grid[0][i] = true;
                break;
            }
        }
        for(int i = n - 1; i > 0; i--) {
            if(grid[n - 2][i]) {
                grid[n - 1][i] = true;
                break;
            }
        }

        return grid;
    }

    private void divide(boolean[][] grid, int x_0, int y_0, int w, int h) {
        if(w <= 1 || h <= 1)
            return;
        if(w < h || (w == h && random.nextBoolean())) {
            int y = y_0 + 1 + random.nextInt(h - 1); // y_0 + 1 to .. y_o + h - 1
            int door = x_0 + random.nextInt(w);
            for(int x = x_0; x < x_0 + w; x++) {
                grid[y][x] = false;
            }
            grid[y][door] = true;

            divide(grid, x_0, y_0, w, y - y_0);
            divide(grid, x_0, y + 1, w, h - (y - y_0) - 1);
        } else {
            int x = x_0 + 1 + random.nextInt(w - 1);
            int door = y_0 + random.nextInt(h);
            for(int y = y_0; y < y_0 + h; y++)
                grid[y][x] = false;
            grid[door][x] = true;

            divide(grid, x_0, y_0, x - x_0, h);
            divide(grid, x + 1, y_0, w - (x - x_0) - 1, h);
        }
    }
}
