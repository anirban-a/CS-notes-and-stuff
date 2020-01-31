
/**


Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

The first call to next() will return the smallest number in BST. Calling next() again will return the next smallest number in the BST, and so on.

    Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

    Try to optimize the additional space complexity apart from the amortized time complexity. 

URL: https://www.interviewbit.com/problems/bst-iterator/

*/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */



/*  
	Approach:
	=========

    We will do it without any extra space.
    We will first flatten the BST in in-order style, that way we can begin with the smallest element and reach till the largest.
*/

class BSTIterator{
    private TreeNode head;
    private TreeNode iter;
    
    private final int HEAD=0, TAIL=1;
    public Solution(TreeNode root) {
        head=null;
        TreeNode[]list = flatten(root);
        head=(list==null?null:list[HEAD]);
        iter=head;
    }

    private TreeNode[] flatten(TreeNode node){
        if(node==null)return null;
        if(node.left==null && node.right==null){
            return new TreeNode[]{node,node};
        }
        TreeNode[]leftList=flatten(node.left); // flatten the left sub-tree
        TreeNode[]rightList=flatten(node.right); // flatten the right sub-tree
        
        // link leftList->node->rightList
        if(leftList!=null && rightList!=null){
            leftList[TAIL].right=node;
            node.right=rightList[HEAD];
            node.left=null;
            return new TreeNode[]{leftList[HEAD],rightList[TAIL]};
        }
        if(leftList==null){
            node.right=rightList[HEAD];
            node.left=null;
            return new TreeNode[]{node,rightList[TAIL]};
        }
        leftList[TAIL].right=node;
        node.left=node.right=null;
        return new TreeNode[]{leftList[HEAD],node};
    }
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return iter!=null;
    }

    /** @return the next smallest number */
    public int next() {
        int x=iter.val;
        iter=iter.right;
        return x;
    }
}