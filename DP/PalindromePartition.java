/**


Given a string A, partition A such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of A.


Input Format:

The first and the only argument contains the string A.

Output Format:

Return an integer, representing the answer as described in the problem statement.

Constraints:

1 <= length(A) <= 501

Examples:

Input 1:
    A = "aba"

Output 1:
    0

Explanation 1:
    "aba" is already a palindrome, so no cuts are needed.

Input 2:
    A = "aab"
    
Output 2:
    1

Explanation 2:
    Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.


*/

public class PalindromePartition{
	// cache all indices i,j to indicate if A[i,j] is palindrome
    boolean[][]palindrome;

    // cache all indices to get the minimum cuts required in A[i,j] to split A[i,j] into palindromes
    int[][]dp;

    final int INF=Integer.MAX_VALUE-2;
    
    int findCut(String A, int i, int j){
    	// if A[i,j] is already palindrome then no cuts required.
        if(palindrome[i][j])
            return 0;

        // if A[i,j] not palindrome but we already have data in cache for A[i,j]
        if(dp[i][j]!=INF)
            return dp[i][j];

        int ans=INF;
        for(int k=i;k<j;k++){
        	// if A[i,k] is palindrome then let's try to find the minimum number of cuts required to make A[p,j] palindrome where k+1<=p<j
            if(palindrome[i][k])
                ans=Math.min(ans,1+findCut(A,k+1,j));
        }
        return dp[i][j]=ans;
    }
    public int minCut(String A) {
        int n=A.length();
        palindrome=new boolean[n][n];
        dp=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
                dp[i][j]=INF;
        }
        for(int i=0;i<n;i++){
            palindrome[i][i]=true;
            if(i<n-1 && A.charAt(i)==A.charAt(i+1))
                palindrome[i][i+1]=true;
        }
        for(int l=1;l<=n;l++){
            for(int i=0;i<=n-l;i++){
                int j=i+l-1;
                if(i<j-1 && A.charAt(i)==A.charAt(j))
                    palindrome[i][j]=palindrome[i+1][j-1];
            }
        }
        return findcut(A,0,n-1);
    }
}