/**


Given a binary tree T, find the maximum path sum. The path may start and end at any node in the tree.

Input Format:
=============
The first and the only argument contains a pointer to the root of T, A.


Output Format:
=============

Return an integer representing the maximum sum path.

Constraints:
=============

1 <= Number of Nodes <= 7e4
-1000 <= Value of Node in T <= 1000


Example :
=========

Input 1:

       1
      / \
     2   3

Output 1:
     6

Explanation 1:
    The path with maximum sum is: 2 -> 1 -> 3

Input 2:
    
       -10
       /  \
     -20  -30

Output 2:
    -10

Explanation 2
    The path with maximum sum is: -10


*/

// Solution:

/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) {
 *      val = x;
 *      left=null;
 *      right=null;
 *     }
 * }
 */
public class MaxSumPathInBinaryTree{
	// keep track of the overall maximum sum
    int overallMaxSum=Integer.MIN_VALUE;

    int maxSumFindHelper(TreeNode node){
        if(node==null)
            return 0;
        if(node.left==null && node.right==null){
            overallMaxSum=Math.max(overallMaxSum,node.val);
            return node.val;
        }
        // maximum sum from left sub-tree excluding the current node's value
        int maxLeftSum=maxSumFindHelper(node.left);

        // maximum sum from the right sub-tree excluding the current node's value
        int maxRightSum=maxSumFindHelper(node.right);

        // maximum sum inclusive of the current node's value
        int maxSumTillHere=Math.max(node.val, Math.max(maxLeftSum,maxRightSum)+node.val);

        /*
        	For overall maximum sum there are 3 possibilites:

        	(i) the sum is maxSumTillHere
        	(ii) the sum in maxLeftSum + maxRightSum + current node's value
        	(iii) the maximum sum till the current node is still not greater than the overall maximum sum.

        */
        overallMaxSum=Math.max(overallMaxSum, Math.max(maxSumTillHere,maxLeftSum+maxRightSum+node.val));
        return maxSumTillHere;
    }
    public int maxPathSum(TreeNode A) {
        maxSumFindHelper(A);
        return overallMaxSum;
    }
}