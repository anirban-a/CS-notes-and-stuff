/*

A Program to check if strings are rotations of each other or not

Given a string s1 and a string s2, write a snippet to say whether s2 is a rotation of s1?
(eg given s1 = ABCD and s2 = CDAB, return true, given s1 = ABCD, and s2 = ACBD , return false)
*/

import java.util.*;
import java.lang.*;
import java.io.*;


class CheckRotatedStrings
{
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a=br.readLine();
		String b=br.readLine();


		if(a.length()!=b.length()){
			System.out.println("NO");
		}else{
			int offset=-1,i=0,j=0;
			while(i<a.length()){
				if(a.charAt(i)==b.charAt(j)){
					if(offset==-1)offset=i;
					j++;
				}
				else{
					j=0;
					offset=-1;
				}
				i++;
			}
			if(j==b.length()){
				System.out.println("YES");
			}else{
				i=0;
				while(i<offset){
					if(a.charAt(i)!=b.charAt(j))break;
					i++;
					j++;
				}
				if(i==offset)
					System.out.println("YES");
				else
					System.out.println("NO");
			}
			
		}
	}
}