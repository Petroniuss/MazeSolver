package com.bit.iv.algorithm;

import com.bit.iv.data.structures.DisjointSet;

import java.util.Random;

/**
 *Sometimes this algorithm gets stuck although i cannot figure out why
 * Also this alg has tendencies towards creating lots of short dead ends
 */
public class RandomizedKruskal implements MazeGeneratorAlgorithm{

    @Override
    public boolean[][] generateMaze(int n) {
        return kruskal(new boolean[n][n], n);
    }

    private boolean[][] kruskal(boolean[][] grid, int n) {
        DisjointSet set = new DisjointSet(n * n);
        grid[0][1] = true;
        grid[n - 1][n - 2] = true;

        int[][] px = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        Random random = new Random();

        while (set.find(getPos(1, 0, n)) != set.find(getPos(n - 2, n - 1, n))) {
            int y = random.nextInt(n - 2) + 1;
            int x = random.nextInt(n  - 2) + 1;

            //check for connections!

            if (!grid[y][x] && countNeighbours(y, x, grid, px) < 1) {
                //System.out.printf("(%d, %d) %n", x, y);
                grid[y][x] = true;
            } else if (!grid[y][x] && checkForTwo(x, y, n, grid, px, set)) {
                grid[y][x] = true;
                //System.out.printf("(%d, %d) TWO %n", x, y);
                for(int i = 0; i < 4; i++) {
                    int ny = y + px[i][1];
                    int nx = x + px[i][0];
                    if (grid[ny][nx]) {
                        set.union(getPos(x, y, n), getPos(nx, ny, n));
                    }
                }
            }
        }

        //Delete part of maze that cannot be accessed
        for(int j = 1; j < n - 1; j++){
            for (int i = 1; i < n - 1; i++) {
                if (set.find(getPos(i, j, n)) != set.find(getPos(1, 0, n))) {
                    grid[j][i] = false;
                }
            }
        }

        return grid;
    }

    //if this wall divides two components connect TWO!
    private boolean checkForTwo(int x, int y, int n, boolean[][] grid, int[][] px, DisjointSet set) {
        int count = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = i + 1; j < 4; j++) {
                int ax = x + px[i][0];
                int ay = y + px[i][1];
                int bx = x + px[j][0];
                int by = y + px[j][1];

                if(grid[ay][ax] && grid[by][bx] &&
                    set.find(getPos(ax, ay, n)) != set.find(getPos(bx, by, n)))
                    count++;
            }
        }

        return count >= 1;
    }

    private int countNeighbours(int y, int x, boolean[][] grid, int[][] px) {
        int count = 0;
        for(int i = 0; i < 4; i++){
            int ny = y + px[i][1];
            int nx = x + px[i][0];

            if(grid[ny][nx])
                count++;
        }

        return count;
    }

    private int getPos(int x, int y, int n) {
        return y * n  + x;
    }
}
