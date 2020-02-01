import java.util.*;
import java.io.*;
class TrappingRainWater{
	public int solution(int[]bar){
		int n=bar.length;
		Deque<Integer>q=new LinkedList<>();

		int i=0,sum=0;
		while(i<n){
			if(q.isEmpty()||bar[i]<=bar[q.getLast()]){
				q.add(i);
				i++;
			}
			else{
				while(!q.isEmpty() && bar[i]>bar[q.getLast()]){
					int height=bar[q.removeLast()]; // height of the bar under consideration
					int left=q.isEmpty()?0:q.getLast(); // index of the bar to its left
					int leftHeight=q.isEmpty()?0:bar[left]; // height of that bar
					sum+=Math.max(0,(i-left-1)*(Math.min(leftHeight,bar[i])-height)); // total quantity of water between (exclusive of) left bar and i-th bar
					/*
					2 cases possible:
					case 1:
                          
							   __
	                     __   |  |
						|  |__|  |
						----------

					where the left most bar has some height.

					case 2:
	                           __
	                        __|  |
	  					 __|  |  |
						|  |  |  |
					--------------

					where left bars decrease in height gradually.
					In this case, leftHeight can be 0, height will be > 0, therefore Math.min(leftHeight,bar[i])-height will become < 0.
					So it is necessary to take Math.max(0, (i-left-1)*(Math.min(leftHeight,bar[i])-height)))
					*/
				}

			}
		}
		return sum;
	}
}