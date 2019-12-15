/*
Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In one step, you can move up, down, left or right from and to an empty cell.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.

 

Example 1:

Input: 
grid = 
[[0,0,0],
 [1,1,0],
 [0,0,0],
 [0,1,1],
 [0,0,0]], 
k = 1
Output: 6
Explanation: 
The shortest path without eliminating any obstacle is 10. 
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).


Example 2:

Input: 
grid = 
[[0,1,1],
 [1,1,1],
 [1,0,0]], 
k = 1
Output: -1
Explanation: 
We need to eliminate at least two obstacles to find such a walk.


Constraints:

    grid.length == m
    grid[0].length == n
    1 <= m, n <= 40
    1 <= k <= m*n
    grid[i][j] == 0 or 1
    grid[0][0] == grid[m-1][n-1] == 0

*/

import java.util.*;
class ShortestPathInGridWithObstacles {
    boolean valid(int n,int m, int i, int j, int k, int[][]grid){
        return 0<=i && i<n && 0<=j && j<m && ((grid[i][j]==1&&k>0)||grid[i][j]==0);
    }
    public int shortestPath(int[][] grid, int k) {
        Queue<int[]>q=new ArrayDeque<>();
        int n=grid.length,m=grid[0].length;

        // visited[][] is going to keep track of 2 things:
        // 1) if the node was visited.
        // 2) with what value of k was it visited, so that if we have higher value of k we can revisit and try to find a path
        
        int visited[][]=new int[n][m];
        q.add(new int[]{0,0,k,0});
        for(int i=0;i<n;i++)Arrays.fill(visited[i],-1);
        int ans=-1;
        while(!q.isEmpty()){
            int[]root=q.remove();
            int x=root[0],y=root[1];
            if(x==n-1 && y==m-1){
                ans=root[3];
                return ans;
            }
            
            // traverse the path only if it is unvisited or in case it is visited we will traverse only if we have a better value of K than we did earlier.

            if(valid(n,m,x-1,y,root[2], grid) && (visited[x-1][y]==-1 || (visited[x-1][y]!=-1 && root[2]>visited[x-1][y]))){
                int newK=grid[x-1][y]==1?root[2]-1:root[2];
                visited[x-1][y]=newK;
                q.add(new int[]{x-1,y,newK,root[3]+1});
            }
            if(valid(n,m,x+1,y,root[2], grid) && (visited[x+1][y]==-1 || (visited[x+1][y]!=-1 && root[2]>visited[x+1][y]))){
                int newK=grid[x+1][y]==1?root[2]-1:root[2];
                visited[x+1][y]=newK;
                q.add(new int[]{x+1,y,newK,root[3]+1});
            }
            if(valid(n,m,x,y-1,root[2], grid) && (visited[x][y-1]==-1 || (visited[x][y-1]!=-1 && root[2]>visited[x][y-1]))){
                int newK=grid[x][y-1]==1?root[2]-1:root[2];
                visited[x][y-1]=newK;
                q.add(new int[]{x,y-1,newK,root[3]+1});
            }
            if(valid(n,m,x,y+1,root[2], grid) && (visited[x][y+1]==-1 || (visited[x][y+1]!=-1 && root[2]>visited[x][y+1]))){
                int newK=grid[x][y+1]==1?root[2]-1:root[2];
                visited[x][y+1]=newK;
                q.add(new int[]{x,y+1,newK,root[3]+1});
            }
        }
        return ans;
    }
}