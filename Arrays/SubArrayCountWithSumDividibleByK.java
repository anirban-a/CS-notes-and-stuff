/*


Count all sub-arrays having sum divisible by k

You are given an array of positive and/or negative integers and a value K . The task is to find count of all sub-arrays whose sum is divisible by K?

Examples :

Input  : arr[] = {4, 5, 0, -2, -3, 1}, 
         K = 5
Output : 7

there are 7 sub-arrays whose is divisible by K
{4, 5, 0, -2, -3, 1}
{5}
{5, 0}
{5, 0, -2, -3}
{0}
{0, -2, -3}
{-2, -3}

*/

import java.util.*;
import java.lang.*;
import java.io.*;

class SubArrayCountWithSumDividibleByK {
    static int count(int[]ar, int k){
        Map<Integer,Integer>mem=new HashMap<>();
        int sum=0,count=0,zeroCount=0;
        for(int i=0;i<ar.length;i++){
            sum=(sum+ar[i]+k)%k;

            if(sum==0)zeroCount++;

            else if(mem.get(sum)!=null){
            	count+=mem.get(sum);
            	mem.put(sum,mem.get(sum)+1);
            }
            else mem.put(sum,1);
        }
        count+=zeroCount*(zeroCount+1)/2;
        return count;
    }
	public static void main (String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		while(t-->0){
		    int n=Integer.parseInt(br.readLine());
		    int k=Integer.parseInt(br.readLine());
		    String[]in=br.readLine().split(" ");
		    int[]ar=new int[n];
		    for(int i=0;i<n;i++)
		        ar[i]=Integer.parseInt(in[i]);

		    System.out.println(count(ar,k));
		}
	}
}
