// An IntTree represents a binary tree of integers
public class IntTree {
    private IntTreeNode overallRoot;

    public int evenBranches() {
        return evenBranches(overallRoot);
    }

    private int evenBranches(IntTreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 0;
        } else {
            if (root.data % 2 == 0) {
                return 1 + evenBranches(root.left) + evenBranches(root.right);
            } else {
                return evenBranches(root.left) + evenBranches(root.right);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new IntTree("[2 [8 [0] null] [1 [7 [4] null] [6 null [9]]]]").evenBranches());
    }

    // Constructs a tree with default numbers
    public IntTree() {
        overallRoot = null;
    }

    // Constructs a tree from the given text representation
    public IntTree(String s) {
        overallRoot = fromString(s);
    }

    // post: Prints the numbers in this tree in a pre-order fashion.
    public void print() {
        print(overallRoot);
    }

    private void print(IntTreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            print(root.left);
            print(root.right);
        }
    }

    // post: Returns true if o is an IntTree with the same values
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof IntTree)) {
            return false;
        } else {
            IntTree other = (IntTree) o;
            return toString().equals(other.toString());
        }
    }

    // post: Returns a text representation of the tree
    public String toString() {
        return toString(overallRoot);
    }

    private String toString(IntTreeNode root) {
        if (root == null) {
            return "null";
        } else if (root.left == null && root.right == null) {
            return "[" + root.data + "]";
        } else {
            return "[" + root.data + " "
                + toString(root.left) + " "
                + toString(root.right) + "]";
        }
    }

    // An IntTreeNode represents a single node in a binary tree
    private static class IntTreeNode {
        public int data;
        public IntTreeNode left;
        public IntTreeNode right;

        // post: Constructs a leaf node with given data
        public IntTreeNode(int data) {
            this(data, null, null);
        }

        // post: Constructs a leaf or branch node with given data and links
        public IntTreeNode(int data, IntTreeNode left, IntTreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private static IntTreeNode fromString(String s) {
        s = s.trim();
        if (s.isEmpty() || s.equals("null")) {
            return null;
        }
        s = s.substring(1, s.length() - 1);
        try {
            return new IntTreeNode(Integer.parseInt(s.trim()));
        } catch (NumberFormatException e) {
            String[] pair = s.trim().split(" +", 2);
            int data = Integer.parseInt(pair[0]);
            int index = splitIndex(pair[1]);
            String left = pair[1].substring(0, index);
            String right = pair[1].substring(index);
            return new IntTreeNode(data, fromString(left), fromString(right));
        }
    }

    private static int splitIndex(String s) {
        if (s.startsWith("null")) {
            return 4;
        }
        int brackets = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                brackets += 1;
            } else if (c == ']') {
                brackets -= 1;
            }
            if (brackets == 0) {
                return i + 1;
            } else if (brackets < 0) {
                throw new IllegalArgumentException("bad brackets: " + s);
            }
        }
        throw new IllegalArgumentException("bad brackets: " + s);
    }
}
