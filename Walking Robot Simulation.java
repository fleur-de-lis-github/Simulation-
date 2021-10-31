/*A robot on an infinite XY-plane starts at point (0, 0) facing north. The robot can receive a sequence of these three possible types of commands:

-2: Turn left 90 degrees.
-1: Turn right 90 degrees.
1 <= k <= 9: Move forward k units, one unit at a time.
Some of the grid squares are obstacles. The ith obstacle is at grid point obstacles[i] = (xi, yi). If the robot runs into an obstacle, then it will instead stay in its current location and move on to the next command.

Return the maximum Euclidean distance that the robot ever gets from the origin squared (i.e. if the distance is 5, return 25).

Note:

North means +Y direction.
East means +X direction.
South means -Y direction.
West means -X direction.
 

Example 1:

Input: commands = [4,-1,3], obstacles = []
Output: 25
Explanation: The robot starts at (0, 0):
1. Move north 4 units to (0, 4).
2. Turn right.
3. Move east 3 units to (3, 4).
The furthest point the robot ever gets from the origin is (3, 4), which squared is 32 + 42 = 25 units away.
Example 2:

Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
Output: 65
Explanation: The robot starts at (0, 0):
1. Move north 4 units to (0, 4).
2. Turn right.
3. Move east 1 unit and get blocked by the obstacle at (2, 4), robot is at (1, 4).
4. Turn left.
5. Move north 4 units to (1, 8).
The furthest point the robot ever gets from the origin is (1, 8), which squared is 12 + 82 = 65 units away.
Example 3:

Input: commands = [6,-1,-1,6], obstacles = []
Output: 36
Explanation: The robot starts at (0, 0):
1. Move north 6 units to (0, 6).
2. Turn right.
3. Turn right.
4. Move south 6 units to (0, 0).
The furthest point the robot ever gets from the origin is (0, 6), which squared is 62 = 36 units away.
 

Constraints:

1 <= commands.length <= 104
commands[i] is either -2, -1, or an integer in the range [1, 9].
0 <= obstacles.length <= 104
-3 * 104 <= xi, yi <= 3 * 104
The answer is guaranteed to be less than 231. */

class Solution {
    private int curX = 0;
    private int curY = 0;
    private int curDir = 0;
    private int[][] directions = {{0,1}, {1,0}, {0, -1}, {-1, 0}};
     
    public int robotSim(int[] commands, int[][] obstacles) {
        int ans = 0;
         
        // step 1: put the obstacles into a hashset
        //
        Set<Long> set = new HashSet<>();
        for (int[] obstacle : obstacles) {
            long x =  (long) obstacle[0] + 30000;
            long y = (long) obstacle[1] + 30000;
            long hashCode = (x << 16) + y;
            set.add(hashCode);
        }
         
        // step 2: go to each command
        //
        for (int command : commands) {
            if (command == -1) {
                changeDirection(-1);
            } else if (command == -2) {
                changeDirection(-2);
            } else if (command >= 1 && command <= 9) {
                go(command, set);
                ans = Math.max(ans, curX * curX + curY * curY);
            }
        }
         
        return ans;
    }
     
    private void changeDirection(int direction) {
        if (direction == -1) {
            curDir = (curDir + 1 + 4) % 4;
        } else if (direction == -2) {
            curDir = (curDir - 1 + 4) % 4;
        }
    }
     
    private void go(int steps, Set<Long> set) {
        int[] direction = directions[curDir];
        int targetX = curX + steps * direction[0];
        int targetY = curY + steps * direction[1];
         
        for (int i  = 0; i < steps; i++) {
            curX += direction[0];
            curY += direction[1];
             
            long x = (long)curX + 30000;
            long y = (long)curY + 30000;
            long hashCode = (x << 16) + y;
            if (set.contains(hashCode)) {
                curX -= direction[0];
                curY -= direction[1];
                 
                break;
            }
        }
    }
}
