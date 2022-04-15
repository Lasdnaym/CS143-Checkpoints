import java.util.*;

public class Rearrange {
    public static void rearrange(Queue<Integer> q) {
        // New stack for storage
        Stack<Integer> s = new Stack<Integer>();
        // Find the size of the Queue
        int size = q.size();
        // We do this twice because the first time we run through the program, it may store the values in a
        // different order compared to the original list.
        // Given: [3, 5, 4, 17, 6, 8] our starting Queue
        // n = 0: [4, 6, 8, 17, 5, 3] the first time we run through our sorting system - notice that odds are out of order
        // n = 1: [4, 6, 8, 3, 5, 17] which is what we want
        for (int n = 0; n < 2; n++) {
            // For all the elements we want to run the following check
            for (int i = 0; i < size; i++) {
                // If it is even
                if(q.peek() % 2 == 0) {
                    // Put it at the end of the Queue
                    q.add(q.remove());
                } else {
                    // Else take it out and store it in our storage Stack
                    s.push(q.remove());
                }  
            }
            // While the stack is not empty, add the odd numbers back into the Queue but at the end
            while(!s.isEmpty()) {
                q.add(s.pop());
            }        
        }
    }

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(3);
        q.add(5);
        q.add(4);
        q.add(17);
        q.add(6);
        q.add(8);
        rearrange(q);
        System.out.println(q);
    }
}
