/*

Given an string A. The only operation allowed is to insert characters in the beginning of the string.

Find how many minimum characters are needed to be inserted to make the string a palindrome string.



Input Format

The only argument given is string A.

Output Format

Return the minimum characters that are needed to be inserted to make the string a palindrome string.

For Example

Input 1:
    A = "ABC"
Output 1:
    2
    Explanation 1:
        Insert 'B' at beginning, string becomes: "BABC".
        Insert 'C' at beginning, string becomes: "CBABC".

Input 2:
    A = "AACECAAAA"
Output 2:
    2
    Explanation 2:
        Insert 'A' at beginning, string becomes: "AAACECAAAA".
        Insert 'A' at beginning, string becomes: "AAAACECAAAA".


*/

/*

Approach:
==========

Since we can only append characters at the begining of the string, so it makes sense to find the longest prefix which is a palindrome. Everything else after that can be placed
in a mirror image way before the begining of the prefix.

Let's say in the given string S, S[0,j] is palindrome such that the length j is maximum then the substring formed from reverse(S[j+1,n]) where n is the length of S, can be appended
at the begining. Now, we don't really need to append anything in this problem. We only need to find the Longest Palindromic Prefix, let's say its length is l, then the number of
characters from l+1 till the end will be n-(l+1) which is our answer.
*/

public class MinimumCharsToMakePalindromeString{
    public int solve(String A) {
        int n=A.length();
        int i=0,j=n-1,k=-1;
        while(i<=j){
            if(A.charAt(i)!=A.charAt(j)){
                if(A.charAt(0)==A.charAt(j)){
                    k=j;
                    i=1;
                }
                else{
                    k=-1;
                    i=0;
                }
            }else{
                if(k==-1)
                    k=j;
                i++;
            }
            j--;
        }
        return n-k-1;
    }	
}