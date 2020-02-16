import java.io.*;
import java.util.*;
/*

Problem:

Given a string ” ABABCABABCD”, you have to compress it into the following format: AB*C*D .

Explanation:

Here, till AB* of the output, AB repeats twice, but till AB*C*, ABABC repeats twice.

    Test cases:
    ===========
    Input 1:
    ABABCABABCD

    Output:
    AB*C*D

    Input 2:
    ABABCABABCDABABCABABCD

    Output:
    AB*C*D*

    Input 3:
    ABACABAC

    Output:
    ABAC*

    Input 4:
    ABC

    Output:
    ABC
*/
class CompressRepeatedSubString{
    static String compress(String str){

        // append a random character to the beginning for the convenience.
        str=" "+str;

        // to build the pie-table
        int[]p=new int[str.length()];
        int i=0,j=2;
        while(j<str.length()){
            if(str.charAt(i+1)==str.charAt(j)){
                p[j]=i+1;
                i++;
                j++;
            }else{
                i=p[i];
                if(str.charAt(i+1)!=str.charAt(j))
                    j++;
            }
        }

        // We will use temp string to store the characters of the substring which cannot cover the threshold we have set.
        StringBuilder temp=new StringBuilder();

        // We will build use it to build the answer.
        StringBuilder ans=new StringBuilder();


        i=0;
        j=1; // j points to the actually beginning of the string.
        int last=0; // last tracks the threshold that i needs to cross to determine if a substring covered by j has been repeated earlier.
        while(j<str.length()){
            if(p[j]==0){
                /*
                    if this character has not been repeated, then we are interested in the following:

                    1) make this index as the threshold, so that when we encounter repeating characters and if we can cover this threshold we know we have a repeating substring.
                    2) keep adding the non-repeating characters to our answer.
                */
                last=j;
                ans.append(str.charAt(j));
                j++;
            }else{
                if(i==0){
                    // if i is not pointing to any repeating character, make it point to the appropriate repeated character from the pie-table.
                    i=p[j];
                }

                /*
                    We want to find the highest value of i till which str[i] matches with str[j].
                    2 cases to note here:
                    
                    1) If i crosses the threshold, then clearly the substring covered by j is repeated. We just need to add a '*' to our answer and flush the temp string.
                    
                    2) If above case fails, then we don't have a repeated substring that covers the threshold set by us, so we just add the characters in temp to our answer
                    and then flush the temp string.
                */
                while(j<str.length() && str.charAt(i)==str.charAt(j)){
                    temp.append(str.charAt(i));
                    i++;
                    j++;
                }

                // if i has crossed the threshold.
                if(i>last)
                    ans.append("*");
                else ans.append(temp.toString());
                // flush the temporary string.
                temp=new StringBuilder();
                i=0;
            }
        }
        return ans.toString();
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str=br.readLine();
        System.out.println(compress(str));
    }
}