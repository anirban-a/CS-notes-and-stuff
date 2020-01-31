class Trie{
    private class TrieNode{
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
}