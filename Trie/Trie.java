import java.util.LinkedList;
import java.util.List;

class TrieNode{
    char value;
    TrieNode[]children;
    boolean isEnd;
    public TrieNode() {
        children = new TrieNode[26];
    }
    public TrieNode(char value) {
        this.value = value;
        children = new TrieNode[26];
    }
}
public class Trie
{
    TrieNode root;
    public Trie() {
        root=new TrieNode();
    }
    public void addWord(String word) {
        TrieNode node = root;
        int i=0;
        while(i<word.length()) {
            char c=word.charAt(i);
            if(node.children[c-'a']==null) {
                TrieNode t=new TrieNode(c);
                node.children[c-'a']=t;
            }
            node=node.children[c-'a'];
            i++;
        }
        node.isEnd=true;
    }
    
    // search for word in trie
    public boolean search(String word) {
        TrieNode node = root;
        int i=0;
        while(i<word.length()) {
            char c=word.charAt(i);
            if(node.children[c-'a']==null)
                return false;
            node=node.children[c-'a'];
            i++;
        }
        return true;
    }
    
    // find if any word with the given prefix exists
    public boolean startsWith(String pref) {
        TrieNode node = root;
        for(int i=0;i<pref.length();i++) {
            char c=pref.charAt(i);
            int idx=c-'a';
            if(node.children[idx]==null)return false;
            node=node.children[idx];
        }
        return true;
    }
    
    // return list of words that can be formed from the current node
    private List<String> findWords(TrieNode node){
        List<String>list=new LinkedList<>();
        if(node.isEnd) {
            list.add("");
            return list;
        }
        for(int i=0;i<26;i++) {
            if(node.children[i]!=null) {
                List<String>words=findWords(node.children[i]);
                for(String word:words)
                    list.add(node.children[i].value + word);
            }
        }
        return list;
    }
    
    // given a prefix, find list of words with the given prefix
    public List<String> findWordsByPref(String str){
        List<String>list=new LinkedList<>();
        TrieNode node=root;
        for(int i=0;i<str.length();i++) {
            node=node.children[str.charAt(i)-'a'];
        }
        List<String>words = findWords(node);
        for(String word:words) {
            list.add(str+word);
        }
        return list;
    }
    
    public static void main(String[] args)
    {
        Trie t=new Trie();
        t.addWord("hello");
        t.addWord("help");
        t.addWord("happy");
        t.addWord("alex");
        t.addWord("and");
//        System.out.println(t.search("hello"));
//        System.out.println(t.startsWith("ho"));
//        System.out.println(t.findWords(t.root));
        System.out.println(t.findWordsByPref("hel"));
       
    }
}
