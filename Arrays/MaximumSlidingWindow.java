/*

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7


*/

/*

Approach to solve:
------------------
1) we can use priority queue to solve this. Time complexity will be O(N*log(K)).

2) the following is a linear time approach.

We will use a deque to solve the problem. In the deque we will store indices [index1,index2,index3..] such that nums[index1]>nums[index2] and so on.
Initially we will capture the indices for all first k elements in the above mentioned way.

i will be our beginning index and j will be our ending index.

After we have captured data for k elements in the deque, we will check if i == index1. If so, then we poll the index1 from deque and store its corresponding
value in our result array since when i>index1 we have moved past the previous window and we won't need index1 anymore.

If i<index1 we just get its corresponding value and store in the result array without removing index1.
*/

class MaximumSlidingWindow{
    public int[] maxSlidingWindow(int[] nums, int k) {
    	// if there are no elements in the array then return the array itself.
        if(nums.length==0)return nums;

        int[]ans=new int[nums.length-k+1];

        Deque<Integer> q=new LinkedList<>();
        int i=0,j=0,idx=0;
        while(j<nums.length){
            while(q.size()>0 && nums[j]>nums[q.getLast()])
                q.removeLast();
            q.add(j);
            if(j==i+k-1){
            	if(i==q.peek()){
            		// poll and remove the index
            		ans[idx]=nums[q.poll()];
            	}else{
            		ans[idx]=nums[q.peek()];
            	}
                i++;
                idx++;
            }
            j++;
        }
        return ans;
    }
}