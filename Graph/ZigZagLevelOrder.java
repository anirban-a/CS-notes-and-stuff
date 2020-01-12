/**

Given a binary tree, return the zigzag level order traversal of its nodes’ values. (ie, from left to right, then right to left for the next level and alternate between).

Example :
Given binary tree

    3
   / \
  9  20
    /  \
   15   7

return

[
         [3],
         [20, 9],
         [15, 7]
]


*/

import java.util.*;

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

class Pair{
	//  Helper class for storing level and corresponding nodes

    TreeNode node;
    int label;
    public Pair(TreeNode node,int label){
        this.node = node;
        this.label = label;
    }
}

public class ZigZagLevelOrder{
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode A) {

        ArrayList<ArrayList<Integer>>result=new ArrayList<ArrayList<Integer>>();
        Queue<Pair>q=new LinkedList<Pair>();
        q.add(new Pair(A,0));
        ArrayList<Integer>level=new ArrayList<>();
        ArrayList<Pair>list=new ArrayList<>();
        while(!q.isEmpty()){
        	// remove all the nodes belonging to same level
            while(!q.isEmpty()){
                Pair p=q.remove();
                list.add(p);
                level.add(p.node.val);
            }
            result.add(new ArrayList<>(level));

            // since the next level is in reverse order of the current level, so in order to have the next to next
            // level in the same order as current level we need to traverse in reverse.
            for(int i=list.size()-1;i>=0;i--){
                Pair p=list.get(i);
                if(p.label==0){
                    if(p.node.right!=null)
                        q.add(new Pair(p.node.right,1-p.label));
                    if(p.node.left!=null)
                        q.add(new Pair(p.node.left,1-p.label));
                }else{
                    if(p.node.left!=null)
                        q.add(new Pair(p.node.left,1-p.label));                    
                    if(p.node.right!=null)
                        q.add(new Pair(p.node.right,1-p.label));
                }
            }
            list.clear();
            level.clear();
        }
        return result;
    }
}