/*
Given a 2D grid, each cell is either a zombie or a human. Zombies can turn adjacent (up/down/left/right) human beings into zombies every day. Find out how many days does it take to infect all humans?

Input:
matrix, a 2D integer array where a[i][j] = 1 represents a zombie on the cell and a[i][j] = 0 represents a human on the cell.

Output:
Return an integer represent how many days does it take to infect all humans.
Return -1 if no zombie exists.

Example :
Input:
[[0, 1, 1, 0, 1],
[0, 1, 0, 1, 0],
[0, 0, 0, 0, 1],
[0, 1, 0, 0, 0]]

Output:
2

Explanation:
At the end of the day 1, the status of the grid:
[[1, 1, 1, 1, 1],
[1, 1, 1, 1, 1],
[0, 1, 0, 1, 1],
[1, 1, 1, 0, 1]]

At the end of the day 2, the status of the grid:
[[1, 1, 1, 1, 1],
[1, 1, 1, 1, 1],
[1, 1, 1, 1, 1],
[1, 1, 1, 1, 1]]
*/

/*  Solution approach:

We will use the given matrix to store information about which cells have zombies in it and which cells have humans at any given point of time. And along with that we will also store the number of days taken to convert a human to zombie. To achieve both we will mark cells with humans to -1 and cells with zombies to 0 so that while doing bfs we can increment the value of human cells by adding 1 to number of days taken to make a zombie in the current cell. Since initially number of days taken to convert a zombie into zombie will be 0 so we can safely proceed with this idea. Also, we will be adding only the neighbouring cells with -1 in it i.e., humans, to the bfs queue. NOTE: we don't need to keep a separate array of visited cells since -1 will denote the unvisited cells.

We start by initially adding all the cells having zombies to the queue. All these points will be the starting points for further BFS traversal.

*/

import java.util.*;

public class ZombieInfection {
  
  boolean isValid(int i, int j, int[][] matrix){
    int n=matrix.length, m=matrix[0].length;
    return 0<=i && i<n && 0<=j && j<m && matrix[i][j]==-1;
  }
	
  public int humanDays(int[][] matrix) {
    Queue<Integer[]>bfs=new ArrayDeque<Integer[]>();
		int n=matrix.length, m=matrix[0].length;
		for(int i=0;i<n;i++){
		    for(int j=0;j<m;j++){
		        matrix[i][j]-=1;
		        if(matrix[i][j]!=-1)
		            bfs.add(new Integer[]{i,j});
		    }
		}
    
    // if no zombies present, maxDays will be -1, if all are zombies then maxDays have to be 0
		int maxDays=bfs.isEmpty()?-1:0;
    
		while(!bfs.isEmpty()){
		    Integer[]pair=bfs.remove();
		    int x=pair[0],y=pair[1];
        maxDays=Math.max(maxDays,matrix[x][y]);
		    if(isValid(x-1,y,matrix)){
            matrix[x-1][y]=matrix[x][y]+1;
		        bfs.add(new Integer[]{x-1,y});
		    }
		    if(isValid(x+1,y,matrix)){
            matrix[x+1][y]=matrix[x][y]+1;
		        bfs.add(new Integer[]{x+1,y});
		    }
		    if(isValid(x,y-1,matrix)){
            matrix[x][y-1]=matrix[x][y]+1;
		        bfs.add(new Integer[]{x,y-1});
		    }
		    if(isValid(x,y+1,matrix)){
		        matrix[x][y+1]=matrix[x][y]+1;
		        bfs.add(new Integer[]{x,y+1});
		    }
		}
		return maxDays;
	}
}
