/**
*
* This implementation assumes the words stored are going to be made of only lower case alphabets [a-z].
**/

import java.util.*;
class Trie{
    class TrieNode{
        char val;
        TrieNode[]child;
        boolean isWord;

        TrieNode(){
            isWord=false;
            child=new TrieNode[26];
        }
    }

    private class Pair{
        TrieNode node;
        String word;
        Pair(TrieNode node, String word){
            this.node=node;
            this.word=word;
        }
    }

    private TrieNode root=null;

    public Trie(){
        root=this.new TrieNode();
    }

    public void addWord(String word){
        Trie.TrieNode currentNode=this.root;

        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            int idx=c-'a';
            if(currentNode.child[idx]!=null)
                currentNode=currentNode.child[idx];
            else{
                Trie.TrieNode node=new TrieNode();
                node.val=c;
                currentNode.child[c-'a']=node;
                currentNode=node;
            }
        }
        currentNode.isWord=true;        
    }

    public boolean search(String word){
        TrieNode currentNode=root;
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            int idx=c-'a';
            if(currentNode.child[idx]==null)
                return false;
            currentNode=currentNode.child[idx];
        }
        return currentNode.isWord;
    }

    /** Returns the child node for the given character or null if it doesn't exist. */
    public TrieNode getNode(char chr){

        return this.root.child[chr-'a'];
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix){
        // if there exists a valid node then the prefix is valid.
        return getNodeWithPrefix(prefix)!=null;
    }

    private TrieNode getNodeWithPrefix(String prefix){
        TrieNode currentNode=root;
        for(int i=0;i<prefix.length();i++){
            char c=prefix.charAt(i);
            int idx=c-'a';
            if(currentNode.child[idx]==null)
                return null;
            currentNode=currentNode.child[idx];
        }
        return currentNode;        
    }

    public List<String> wordListWithPrefix(String prefix){
        TrieNode node=getNodeWithPrefix(prefix);
        Stack<Pair> stack=new Stack<>();
        List<String>wordList = new LinkedList<>();
        stack.push(new Pair(node,prefix));

        // do DFS to form words.

        while(!stack.empty()){
            Pair pair=stack.pop();
            TrieNode currentNode = pair.node;
            prefix=pair.word;
            if(currentNode.isWord)
                wordList.add(pair.word);
            for(int i=0;i<26;i++){
                if(currentNode.child[i]!=null){
                    Pair childPair=new Pair(currentNode.child[i],prefix+currentNode.child[i].val);
                    stack.push(childPair);
                }
            }
        }
        return wordList;
    }
}