import java.io.*;
class MergeSort{
	static void mergeSort(int[]ar, int left, int right){
		if(left<right){
			int mid=(left+right)/2;
			mergeSort(ar,left,mid);
			mergeSort(ar,mid+1,right);
			merge(ar,left,mid,right);
		}
	}
	static void merge(int[]ar,int l,int mid,int r){
		int temp[]=new int[r-l+1];
		int i=l,j=mid+1,k=0;
		while(i<=mid&&j<=r){
			if(ar[i]<=ar[j]){
				temp[k]=ar[i];
				i++;
			}else{
				temp[k]=ar[j];
				j++;
			}
			k++;
		}
		while(i<=mid){
			temp[k]=ar[i];
			i++;k++;
		}
		while(j<=r){
			temp[k]=ar[j];
			j++;
			k++;
		}
		i=l;
		while(i<=r){
			ar[i]=temp[i-l];
			i++;
		}
	}
	public static void main(String[] args)throws IOException {
		// Driver program
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[]in=br.readLine().split(" ");
		int[]ar=new int[in.length];
		for(int i=0;i<in.length;i++)ar[i]=Integer.parseInt(in[i]);
		mergeSort(ar,0,ar.length-1);
		for(int i=0;i<in.length;i++)System.out.print(ar[i]+" ");
		System.out.println();
	}
}