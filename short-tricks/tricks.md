1) If you are given a pair of intervals [[a,b],[c,d]], then to find if the two intervals overlap or not check:
	if c<b and d>a

Reason: Consider the following cases:
1) a<c<b and d<b
2) a<c<b and d<b
3) d>b for above 2 cases
4) c<a and a<d<b
5) c<a and d>b
6) c=a and a<d<b
7) c=a and d>b
8) c<a and a<d<=b
