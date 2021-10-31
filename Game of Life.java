/*According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its 
eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

-Any live cell with fewer than two live neighbors dies as if caused by under-population.
-Any live cell with two or three live neighbors lives on to the next generation.
-Any live cell with more than three live neighbors dies, as if by over-population.
-Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
-The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
Given the current state of the m x n grid board, return the next state. */

public class Solution {
    public void gameOfLife(int[][] board) {
        // defines 2 to be a 1 -> 0 case, defines -1 to be a 0 -> 1 case
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                int num = numOfLive(board, i, j);
                if (board[i][j] >= 1){
                    if (num < 2 || num > 3) {
                        board[i][j] = 2;
                    }
                }
                else{
                    if (num == 3) {
                        board[i][j] = -1;
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                }
                else if (board[i][j] == -1) {
                    board[i][j] = 1;
                }
            }
        }
    }
    private int numOfLive(int[][] board, int x, int y) {
        int count = 0;
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (!(i == x && j == y) && isLive(board, i, j)) {
                    count ++;
                }
            }
        }
        return count;
    }
    private boolean isLive(int[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return false;
        }
        if (board[i][j] >= 1) {
            return true;
        }
        return false;
    }
}

 
