/*

Sorted Permutation Rank

Given a string, find the rank of the string amongst its permutations sorted lexicographically.
Assume that no characters are repeated.

Example(1) :

Input : 'acb'
Output : 2

The order permutations with letters 'a', 'c', and 'b' are as:

rank permutation
---- -----------
 1		abc
 2		acb
 3		bac
 4		bca
 5		cab
 6		cba

Example(2):

Input : 'view'
Output: 15

Note: The answer might not fit in an integer, so return your answer % 1000003
*/


/*

Approach:
---------
Enumerating all permutations and matching with the current one is going to be exponential. So we cannot use this.

Lets start by looking at the first character.

If the first character is X (not 'X'), all permutations which had the first character less than X would come before this permutation when sorted lexicographically.

Let's use a map to keep mappings between the alphabet in the given string and their original rank. For example, if given "acb", the mapping will be as 'a'->1,'b'->2,'c'->3, if given
"view", the mapping will be as 'e'->1,'i'->2,'v'->3,'w'->4 i.e., the sequence of characters present in the string in sorted order. This mapping will be used for 2 purpose:

1) To check how many characters smaller than the i-th character of the string can be placed at i-th position to have strings arranged lexicographically.

2) To calculate how many possible choices of characters smaller than i-th character actually exist for placing in i-th position to have strings arranged lexicographically. As in,
let's say the given string is "view", then by the time we reach 'w', we have already used up 'v','i','e' which actually come before 'w', so we are left with no other choice to fill that
position to have a lexicographically string smaller than 'view'

Using the above 2 points, as we traverse the string we will calculate 2 things:

1) How many possible choices are there for placing characters smaller than that at i-th position in the string.
2) For every character chosen at i-th index in step 1, how many possible strings (permutations) can be formed from (i+1)-th index i.e., if I placed at k-th place I will have k-1 places
to fill from here on with k-1 available characters (where k is the total available places) which is factorial of (k-1). [ (k-1)P(k-1) ].

Since we know there will be no repetition in the characters, it is safe to lower the rank of characters higher than that at i-th index which will indicate the updated number of available
choices for each of those characters.

Therefore, our answer is summation of x[i]*(k-1)! for 0<=i<n (where,
 x[i] is the number of choices available for filling index i with a smaller character than already present, and
 n is the length of the string)

*/
import java.io.*;
import java.util.*;

public class SortedPermutationRank{

    static final int mod=1000003;

    static int fact(int x){
        int p=1;
        while(x>0){
            p=(p%mod*x%mod)%mod;
            x--;
        }
        return p;
    }

    public static int findRank(String A) {
        char[]ar=A.toCharArray();
        Arrays.sort(ar);
        Map<Character,Integer>rank=new HashMap<>();
        for(int i=0;i<ar.length;i++)rank.put(ar[i],i+1); // mapping each character to a rank
        int sum=0; // final answer
        for(int i=0;i<ar.length;i++){
            int k=ar.length-i; // k is the available number of places to fill
            int r=rank.get(A.charAt(i));
            sum=(sum%mod+(Math.max(r-1,0)*fact(k-1))%mod)%mod; // r should be greater than 0, i,e., we can have at the most 1 choice left, only then can place a smaller character.
            for(int j=0;j<ar.length;j++){
                if(ar[j]>A.charAt(i))
                    rank.put(ar[j],rank.get(ar[j])-1); // update the rank of all the characters higher than charAt(i)
            }
        }
        return (sum%mod+1)%mod;
    }
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String in=br.readLine();
		int answer=findRank(in);
		System.out.println(answer);
	}
}