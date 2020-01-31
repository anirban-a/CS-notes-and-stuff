/**


Given a binary tree, flatten it to a linked list in-place.

Example :
Given


         1
        / \
       2   5
      / \   \
     3   4   6

The flattened tree should look like:

   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6

Note that the left child of all nodes should be NULL.

URL:  https://www.interviewbit.com/problems/flatten-binary-tree-to-linked-list/

*/


/**
	Approach:
	=========
        I will try to build the linked list by following the below strategy:
        
        1) building a linked-list using the left sub-tree and return its head and tail, i.e., the first node and the last node.
        2) build a linked-list using the right sub-tree and return its head and tail.
        3) removing the left sub-tree, and link it's tail with the head of the list from right sub-tree (if both exist), and then link current node's right with the head of the list
            from left sub-tree.
        4) if node.left == null then just return the node as head and the tail found so far, nothing much to do as the right sub-tree has already been converted.
        5) if node.right == null, then just link node.right to the head of the list found from left sub-tree, the tail will be the tail from left sub-tree and node is now the head. 
        Also, make node.left = null, and return the head and tail.
        
        The main idea is to convert the binary tree to a unary tree by eliminating the left sub-tree and joining it with the right sub-tree, i.e., "append" the right sub-tree to
        the left sub-tree and the list formed so far will be appended to node.right.

*/

/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class FlattenBinaryTree{
    
    // this method will return the head and tail of the list formed after linking.
    private final int HEAD=0,TAIL=1;
    TreeNode[] link(TreeNode node){
        if(node==null)return null;
        if(node.left==null && node.right==null){
            return new TreeNode[]{node,node}; // TreeNode[]{head, tail} -> head (first node)of the list and tail (last node) of the list
        }
        TreeNode[] leftTree=link(node.left);
        TreeNode[] rightTree=link(node.right);
        
        if(leftTree!=null && rightTree!=null){
            leftTree[TAIL].right=rightTree[HEAD];
            leftTree[TAIL].left=null;
            node.right=leftTree[HEAD];
            node.left=null;
            return new TreeNode[]{node,rightTree[TAIL]};
        }
        if(leftTree==null){
            return new TreeNode[]{node,rightTree[TAIL]};
        }
        node.right=leftTree[HEAD];
        node.left=null;
        return new TreeNode[]{node,leftTree[TAIL]};
    }
    public TreeNode flatten(TreeNode a) {
        return link(a)[HEAD]; // return the head of the list
    }
}