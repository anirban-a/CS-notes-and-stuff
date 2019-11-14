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
                    ans=(ans%mod+unsetCount[bit]%mod)%mod;
                    setCount[bit]++;
                }else{
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