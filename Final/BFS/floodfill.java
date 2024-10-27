class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        

        // Base case for empty image
        if (image.length == 0 || image[sr][sc] == color) {
            return image;
        }
        // Call DFS
        dfs(image, sr, sc, image[sr][sc], color);
        
        return image;
    }

    public void dfs(int[][] image, int i, int j, int color, int newColor) {
        // Base cases for out of bounds or water/visited cell
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] != color) {
            return;
        }
        
        // Mark current cell as visited
        image[i][j] = newColor;
        
        // Explore all four directions
        dfs(image, i + 1, j, color, newColor); // Down
        dfs(image, i - 1, j, color, newColor); // Up
        dfs(image, i, j + 1, color, newColor); // Right
        dfs(image, i, j - 1, color, newColor); // Left

    }
}