// A LinkedIntList stores a list of integers
public class LinkedIntList {
    private ListNode front;

    // Using a public private pair.
    public void addPrefixSum(int index, int sum) {
        front = addHelp(index, sum, front, 0, 0);
    }

    // We have integers index and sum from the problem statement.
    // current: the current node we are on.
    // counter: keeps track of what node we are on.
    // total: keeps track of the sum of node data while recursing.
    // In this method, we aren't going to return an entire list. Instead, we're going to add nodes
    // and contstruct a list over time in this manner. 
    private ListNode addHelp(int index, int sum, ListNode current, int counter, int total) {
        // Per the problem, we want to be at index - 1 so if the counter is equal to that, we stop
        // recursing and return a new node with the appropriate data.
        if (counter == index - 1) {
            return new ListNode(sum - total, current);
        // Else, we want to recurse. Increment the counter by one, account for the data at the current
        // node for total, and call the method again except we pass in current.next so we move down the
        // list.
        } else {
            counter++;
            total = total + current.data;
            current.next = addHelp(index, sum, current.next, counter, total);
            // Return current to keep the nodes from our original list. Recall that since we
            // are not constructing a list in this method, we should return the current node we are on
            // so we can add it to our growing list.  
            return current;
        }
    }

    public static void main(String[] args) {
        LinkedIntList list = new LinkedIntList(1);
        list.addPrefixSum(2, 6);
        System.out.println(list);
    }

    // Constructs a list containing the given elements
    public LinkedIntList(int... elements) {
        if (elements.length > 0) {
            front = new ListNode(elements[0]);
            ListNode current = front;
            for (int i = 1; i < elements.length; i++) {
                current.next = new ListNode(elements[i]);
                current = current.next;
            }
        }
    }

    // post: Returns true if o is a LinkedIntList with the same values
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof LinkedIntList)) {
            return false;
        } else {
            return this.toString().equals(o.toString());
        }
    }

    // post: Returns a text representation of the list
    public String toString() {
        if (front == null) {
            return "[]";
        } else {
            ListNode current = front;
            String result = "[";
            while (current.next != null) {
                result += current.data + ", ";
                current = current.next;
            }
            result += current.data + "]";
            return result;
        }
    }

    // A ListNode represents a single node in a linked list
    private static class ListNode {
        public final int data;
        public ListNode next;

        // post: Constructs a node with data 0 and null link
        public ListNode() {
            this(0, null);
        }

        // post: Constructs a node with given data and null link
        public ListNode(int data) {
            this(data, null);
        }

        // post: Constructs a node with given data and given link
        public ListNode(int data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }
}
