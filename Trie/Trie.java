/**
*
* This implementation assumes the words stored are going to be made of only lower case alphabets [a-z].
**/

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

    public TrieNode getNode(char chr){
        // return the child node for the given character or null if it doesn't exist
        return this.root.child[chr-'a'];
    }
}