public class PatternSearchKMP {

// strStr method takes a string A, and a pattern B to look for in the given String A.

    public int strStr(final String A, final String B) {
        if(A.length()==0 || B.length()==0)return -1;
        if(A.equals(B))return 0;
        int[]pre=new int[B.length()];
        pre[0]=-1;
        for(int i=1,j=0;i<B.length();i++){
            if(B.charAt(j)==B.charAt(i)){
                pre[i]=j;
                j++;
            }else{
                pre[i]=-1;
                j=0;
            }
        }
        int i=0,j=-1;
        int ans=-1;
        while(i<A.length() && j<B.length()-1){
            if(A.charAt(i)==B.charAt(j+1)){
                if(j==-1)
                    ans=i;
                j++;
            }else{
                while(j>=0 && A.charAt(i)!=B.charAt(j+1))
                    j=pre[j];
                if(A.charAt(i)==B.charAt(j+1)){
                    if(j==-1)
                        ans=i;
                    j++;
                }else ans=-1;
            }
            i++;
        }
        if(j==B.length()-1){
            return i-j-1;
        }
        return -1;
    }
}
