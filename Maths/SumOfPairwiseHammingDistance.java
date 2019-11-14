/*
* Sum of pairwise Hamming Distance.
* =================================
* Hamming distance between two non-negative integers is defined as the number of positions at which the corresponding bits are different.
*
* For example,
*
* HammingDistance(2, 7) = 2, as only the first and the third bit differs in the binary representation of 2 (010) and 7 (111).
*
* Given an array of N non-negative integers, find the sum of hamming distances of all pairs of integers in the array.
* Return the answer modulo 1000000007.
*/

/*

Approach 1:
===========
For every A[i] and A[j] where j>i, find the count of difference in bits and add the count modulo 1000000007 to final answer. Since we counted all distinct
pairs, we must return ans*2 modulo 1000000007.

Time complexity O(n^2).


Approach 2:
===========
We will use 2 maps, one to keep a count of set bits for every bit position (LSB through MSB) for all the numbers, and likewise another map to keep count of
unset bits. Reason for that is for every number we actually need the count of numbers whose i-th bit position differs from the current number which is
equivalent to total number of bit difference at i-th position with respect to the current number. And for i-th bit position of every number if it is set
increment set bit count at i-th bit position of the map keeping count of set bits, and same for unset bits.

*/
import java.io.*;
import java.util.*;
public class SumOfPairwiseHammingDistance{
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    static int hammingDistance(final List<Integer> A) {
        int ans=0;
        final int mod=1000000007;
        int[]setCount=new int[31];
        int[]unsetCount=new int[31];
        for(int i=0;i<A.size();i++){
            int x=A.get(i);
            int k=1;
            for(int bit=0;bit<31;bit++){
                if((x&k)!=0){
                	// current bit position is set for x so we count the number of unset bits at this position
                    ans=(ans%mod+unsetCount[bit]%mod)%mod;
                    setCount[bit]++;
                }else{
                	// current bit position is unset for x so we count the number of set bits at this position
                    ans=(ans%mod+setCount[bit]%mod)%mod;
                    unsetCount[bit]++;
                }
                k=k<<1;
            }
        }
        return (ans*2)%mod;
    }

    // DRIVER PROGRAM
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[]in=br.readLine().split(" ");
		List<Integer>list=new ArrayList<>();
		for(int i=0;i<in.length;i++)
			list.add(Integer.parseInt(in[i]));
		int count=hammingDistance(list);
		System.out.println(count);
	}
}