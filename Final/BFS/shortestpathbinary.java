import java.util.*;
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        
        // Base case for blocked start or end
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) {
            return -1;
        }
        
        // All 8 directions: right, right-down, down, left-down, left, left-up, up, right-up
        int[][] dirs = {{0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}, {-1,0}, {-1,1}};
        
        // Queue for BFS: store coordinates and distance
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1}); // {row, col, distance}
        grid[0][0] = 1; // Mark as visited
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];
            int distance = curr[2];
            
            // If we've reached the target
            if (row == n-1 && col == n-1) {
                return distance;
            }
            
            // Check all 8 directions
            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                // Check bounds and if cell is unvisited
                if (newRow >= 0 && newRow < n && 
                    newCol >= 0 && newCol < n && 
                    grid[newRow][newCol] == 0) {
                    
                    queue.offer(new int[]{newRow, newCol, distance + 1});
                    grid[newRow][newCol] = 1; // Mark as visited
                }
            }
        }
        
        return -1; // No path found
    }
}