/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

        push(x) – Push element x onto stack.
        pop() – Removes the element on top of the stack.
        top() – Get the top element.
        getMin() – Retrieve the minimum element in the stack.

Note that all the operations have to be constant time operations.

URL: https://www.interviewbit.com/problems/min-stack/


Q: What should getMin() do on empty stack? 
A: In this case, return -1.

Q: What should pop do on empty stack? 
A: In this case, nothing. 

Q: What should top() do on empty stack?
A: In this case, return -1

*/


/*
	Approach:
	=========

    primarily our goal is to store the smallest element index towards the head of the deque and the subsequent smaller element indices towards the end.
    at any point of time we are interested in keeping track of elements that are smaller than the first element in the deque or last element in it.
    this way we can keep track of minimum element for every stage after popping the stack.
	How so?

	- since the index idx of the smallest element is at the head of the deque, we can simply access it when the size S of the stack is >=idx.
	- when S<idx, we have removed idx from the head of the deque. Now what remains are the next smaller elements in the deque.
	
	The sequence in the deque at any point is as following:

	A1 <A2 <A3 ..<Ai >= Aj >=Aj.. for some i>=0 and i<j.

	For this reason we can obtain the min by choosing Minimum of {deque.getFirst(), deque.getLast()}.

	Also, since the deque keeps track of indices of subsequent smaller elements as we progress with pushing elements into the stack, either of first element of deque
	or the last element of deque can coincide with the size of the stack while popping. So we must check both and remove from the deque if the size of the stack has 
	become <= first element of last element of deque.
*/
public class MinStack{
	// dequeue to store minimum element indices.
    Deque<Integer>dq=new LinkedList<>();

    // arraylist(instead of linkedlist) to support stack logics and random access.
    List<Integer>list=new ArrayList<>();
    int top=-1;
    public void push(int x) {
        top++;
        list.add(x);

        if(dq.isEmpty())
            dq.addLast(top);
        else if(list.get(top)<list.get(dq.getFirst()))
            dq.addFirst(top);
        else if(list.get(top)<list.get(dq.getLast()))
            dq.addLast(top);
    }

    public void pop() {
        if(top!=-1){
            if(top<=dq.getFirst())
                dq.removeFirst();
            else if(top<=dq.getLast())
                dq.removeLast();
            list.remove(top);
            top--;
        }
    }

    public int top() {
        if(top==-1)
            return -1;
        return list.get(top);
    }

    public int getMin() {
        if(top==-1)
            return -1;
        return Math.min(list.get(dq.getFirst()), list.get(dq.getLast()));
    }
}