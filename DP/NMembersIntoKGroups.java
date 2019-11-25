/*
	Given N members, count the number of ways to form K groups such that the number of members in i-th group is greater than or equalt to number of members in (i-1)-th group.

	Example:
	--------
	Input:

	N=8, K=4
	
	Output: 5

	Input:

	N=4,K=2

	Output: 2

	Explanation:
	the groups can be as- [1,3],[2,2] so that i-th member is greater than (i-1)th member
*/

import java.io.*;
class NMembersIntoKGroups{
	static int[][][]dp;
	static int f(int cap,int sum, int k){
		if(dp[cap][sum][k]!=-1)
			return dp[cap][sum][k];
		if(sum==0&&k==0)return 1;
		if(cap==0|| sum==0||k==0)return 0;
		int x=Math.min(cap,sum);
		return dp[cap][sum][k]=f(x,sum-x,k-1)+f(x-1,sum,k);
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[]in=br.readLine().split(" ");
		int n=Integer.parseInt(in[0]),k=Integer.parseInt(in[1]);

		dp=new int[n+1][n+1][k+1];
		
		for(int p=0;p<=n;p++)
			for(int q=0;q<=n;q++)
				for(int r=0;r<=k;r++)
					dp[p][q][r]=-1;

		int sum=f(n,n,k);
		System.out.println(sum);

	}
}