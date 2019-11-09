/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
class NumberOfSubarraysWithProductK {
    public static int count(int[]ar,int k){
        int pro=1,count=0;
        int i=0,j=0;
        while(j<ar.length){
            while(j<ar.length && pro<=k){
                pro*=ar[j];
                j++;
                if(pro==k)count++;
            }
            while(i<=j && pro>k){
                if(ar[i]==1)count+=2;
                pro/=ar[i];
                i++;
            }
        }
        while(i<ar.length && ar[i]==1){
            i++;
            count+=2;
        }
        return count;
    }
	public static void main (String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[]in=br.readLine().split(" ");
        int[]ar=new int[in.length];
        for(int i=0;i<in.length;i++)ar[i]=Integer.parseInt(in[i]);
        int k=Integer.parseInt(br.readLine());
        if(k==1){
            int count=0;
            for(int i=0;i<ar.length;i++)count+=ar[i]==1?1:0;
            System.out.println(count);
        }
        else System.out.println(count(ar,k));
	}
}