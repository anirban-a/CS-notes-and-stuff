import java.util.*;
class FindKthSmallestElement{
    void swap(int[]ar,int x,int y){
        int temp=ar[x];
        ar[x]=ar[y];
        ar[y]=temp;
    }

    int find(int[]ar, int l, int r, int k){
        if(l==r)
            return ar[l];
        int idx=paritition(ar,l,r);
        if(k==idx+1)
            return ar[idx];
        if(k<idx+1)
            return find(ar,l,idx-1,k);
        return find(ar,idx+1,r,k);
    }

    int paritition(int[] ar,int l, int r){
        Random rand=new Random();
        // choose a random element to place it in it's correct position.
        int randIdx=l+rand.nextInt(r-l+1);
        swap(ar,randIdx,r);
        int key=ar[r];
        int i=l,j=r-1;
        while(i<j){
            if(ar[i]<=key)i++;
            if(ar[j]>key)j--;
            if(i<j && ar[i]>key && ar[j]<=key)
                swap(ar,i,j);
        }
        if(ar[i]<=key){
            swap(ar,i+1,r);
            return i+1;
        }
        swap(ar,i,r);
        return i;
    }
    public int findKthLargest(int[] nums, int k) {
        int n=nums.length;
        // kth largest = (n-k+1)th smallest
        k=n-k+1;
        return find(nums,0,n-1,k);
    }

    public int findKthSmallest(int[] nums, int k) {
    	return find(nums,0,nums.length-1,k);
    }

}