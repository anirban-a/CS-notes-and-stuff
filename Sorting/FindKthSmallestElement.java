import java.io.*;

class FindKthSmallestElement{

	static void quickSort(int[] ar, int left, int right){
		if(left<right){
			int pivotIdx = partition(ar,left,right);
			quickSort(ar,left,pivotIdx-1);
			quickSort(ar,pivotIdx+1,right);
		}
	}

	static int findKSmallest(int[]ar, int left, int right, int k){
		if(0<=k && k<=right-left){
			int pivotIdx = partition(ar,left, right);
			if(left+k==pivotIdx)
				return ar[pivotIdx];
			if(left+k<pivotIdx)
				return findKSmallest(ar,left, pivotIdx-1, k);
			return findKSmallest(ar,pivotIdx+1,right,left+k-pivotIdx-1);
		}
		else return -1;
	}

	static int partition(int[]ar, int left, int right){
		int i=left,j=right-1;
		while(i<=j){
			if(ar[i]<ar[right])i++;
			if(ar[j]>=ar[right])j--;

			if(i<j && ar[i]>ar[right]&&ar[j]<ar[right]){
				int temp = ar[i];
				ar[i]=ar[j];
				ar[j]=temp;
			}
		}
		int temp = ar[i];
		ar[i]=ar[right];
		ar[right] = temp;
		return i;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[]in = br.readLine().split(" ");
		int[]ar = new int[in.length];
		for(int i=0;i<in.length;i++)ar[i]=Integer.parseInt(in[i]);

		int k=Integer.parseInt(br.readLine());
		// quickSort(ar,0,ar.length-1);
		// for(int i=0;i<ar.length;i++){
		// 	System.out.print(ar[i]+" ");
		// }	
		System.out.println(findKSmallest(ar,0,ar.length-1,k-1));
	}
}