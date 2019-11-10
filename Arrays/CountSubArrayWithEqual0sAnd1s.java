/*
* Given an array count the number of sub-arrays with equal number of 0s and 1s in it.
*
* Example:
* ========
* Input: 1 0 0 1 0 1 1
* Output: 8
* 
* Approach:
* =========
* Consider 0 as -1 and 1 as 1. -1 will indicate loss of 1. Taking cumulative sum of 1s and -1s till i-th index will give how many 1s have we gained or lost.
* If for 2 indices i,j where i<j, if sum till i equals sum till j, then sub-array [i+1,j] will have equal number of 1s and 0s. Why?
* Because between 2 indices with same sum, we must have gained and lost equal number of 1s which is why the sum is still the same. Let's say we have m such
* occurences of index where the sum is same, choosing any 2 indices i,j will give a sub-array from i+1 till j i.e., we want mC2 which is m*(m-1)/2 for each
* such sum where m>1. Since we will also add the number of occurences of sum=0 to our result, so if we have for any case sum=0 only once and also say sum=1 once
* we just want to consider when sum is 0 and not calculate for sum=1 with only 1 occurence (as that will not yeild a sub-array with equal 1s and 0s).
*
*/
import java.io.*;
import java.util.*;
public class CountSubArrayWithEqual0sAnd1s{
	static int countSubArrays(int[]ar){
		Map<Integer,Integer>mem=new HashMap<Integer,Integer>();
		int sum=0,count=0;
		for(int i=0;i<ar.length;i++){
			sum+=ar[i]==1?1:-1; // cumulative sum.
			if(mem.containsKey(sum)){
				mem.put(sum,mem.get(sum)+1);
			}else mem.put(sum,1);
			if(sum==0)count++; // this indicates a sub-array from beginning till i-th index with equal 1s and 0s.
		}
		Iterator<Integer>iter=mem.keySet().iterator();
		while(iter.hasNext()){
			int x=mem.get(iter.next());
			if(x>1)count+=x*(x-1)/2;
		}
		return count;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[]in=br.readLine().split(" ");
		int[]ar=new int[in.length];
		for(int i=0;i<in.length;i++)ar[i]=Integer.parseInt(in[i]);
		System.out.println(countSubArrays(ar));
	}
}