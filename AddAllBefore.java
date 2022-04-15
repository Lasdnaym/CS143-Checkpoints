// A LinkedIntList stores a list of integers
public class LinkedIntList {
    private ListNode front;

    public void addAllBefore(int x, int y) {
        // We check front because we could have a null list to begin with.
        if (front != null) {
            // current: will help us parse through the list.
            // previous: will be used for when we have to add in a value.
            ListNode current = front;
            ListNode previous = null;
            // Two situations, either the very first value is a target value
            // meaning we want to add a number before it or it isn't and we check
            // the rest of the list.
            if (front.data == x) {
                ListNode newNodeTwo = new ListNode(y);
                newNodeTwo.next = front;
                front = newNodeTwo;
            }
            // While the current node we are on is not null, making sure we reach the end
            // of the list.
            while (current != null) {
                // If the node matches the target value we need to add a number
                // before it. This whole process is going to insert a node and then
                // shift all values after it to the left.
                if (current.data == x) {
                    // Make a new node with the number we are adding.
                    ListNode newNode = new ListNode(y);
                    // Node after newNode becomes our current.
                    newNode.next = current;
                    // If the previous node is not null then set the node after
                    // previous to be newNode.
                    if (previous != null) {
                        previous.next = newNode;
                    }
                }
                // Above did the insertion of the node we added and here we shift
                // the remaining values.
                previous = current;
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {
        LinkedIntList list = new LinkedIntList(1);
        list.addAllBefore(1, 1);
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
