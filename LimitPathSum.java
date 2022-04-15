// An IntTree represents a binary tree of integers
public class IntTree {
    private IntTreeNode overallRoot;

    // Public private pair.
    public void limitPathSum(int max) {
        overallRoot = limitPathSum(overallRoot, max, 0);
    }

    // We have max from the problem statement.
    // root: used to parse the tree.
    // total: used to keep track of the total sum.
    private IntTreeNode limitPathSum(IntTreeNode root, int max, int total) {
        // If the root is null then we have reached the end of our tree and thus cannot
        // traverse further.
        if (root != null) {
            // Sum the data as we go.
            total += root.data;
            // If the total exceeds the max then we need to disconnect the rest of the tree
            // from that point onwards.
            if (total > max) {
                root = null;
            // Traverse both the right and left side of each root since it is a binary tree.
            } else {
                root.left = limitPathSum(root.left, max, total);
                root.right = limitPathSum(root.right, max, total);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        IntTree tree = new IntTree("[29 [17 [-7 [11] [12]] [37 null [16]]] [15 [4] [14 [-9] [19]]]]");
        tree.limitPathSum(50);
        System.out.println(tree);
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
