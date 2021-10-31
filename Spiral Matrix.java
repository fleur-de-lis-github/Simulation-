//Given an m x n matrix, return all elements of the matrix in spiral order.
// See Detailed Description - https://leetcode.com/problems/spiral-matrix/

//Hint 1- Well for some problems, the best way really is to come up with some algorithms for simulation. Basically, you need to simulate what the problem asks us to do.
//Hint 2- We go boundary by boundary and move inwards. That is the essential operation. First row, last column, last row, first column and then we move inwards by 1 and then repeat. 
//That's all, that is all the simulation that we need.
/*Hint 3- Think about when you want to switch the progress on one of the indexes. If you progress on i out of [i, j] , you'd be shifting in the same column. 
Similarly, by changing values for j , you'd be shifting in the same row. Also, keep track of the end of a boundary so that you can move inwards and then keep repeating. 
It's always best to run the simulation on edge cases like a single column or a single row to see if anything breaks or not. */

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int VISITED = 101;
        int rows = matrix.length;
        int columns = matrix[0].length;
        // Four directions that we will move: right, down, left, up.
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // Initial direction: moving right.
        int currentDirection = 0;
        // The number of times we change the direction.
        int changeDirection = 0;
        // Current place that we are at is (row, col).
        // row is the row index; col is the column index.
        int row = 0;
        int col = 0;
        // Store the first element and mark it as visited.
        List<Integer> result = new ArrayList<>();
        result.add(matrix[0][0]);
        matrix[0][0] = VISITED;
        while (changeDirection < 2) {
            while (row + directions[currentDirection][0] >= 0 &&
                   row + directions[currentDirection][0] < rows &&
                   col + directions[currentDirection][1] >= 0 &&
                   col + directions[currentDirection][1] < columns &&
                   matrix[row + directions[currentDirection][0]]
                   [col + directions[currentDirection][1]] != VISITED) {
                // Reset this to 0 since we did not break and change the direction.
                changeDirection = 0;
                // Calculate the next place that we will move to.
                row = row + directions[currentDirection][0];
                col = col + directions[currentDirection][1];
                result.add(matrix[row][col]);
                matrix[row][col] = VISITED;
            }
            // Change our direction.
            currentDirection = (currentDirection + 1) % 4;
            // Increment change_direction because we changed our direction.
            changeDirection++;
        }
        return result;
    }
}

