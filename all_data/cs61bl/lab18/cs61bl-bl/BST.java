import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    /** Iterate through the objects in a sorted LinkedList and create
     *  a balanced BST.
     */
    private BSTNode linkedListToTree(Iterator iter, int n) {
        BSTNode root;
        LinkedList leftList = new LinkedList();
        int count = 1;
        while (count <= n/2) {
            leftList.add(iter.next());
            count += 1;
        }
        root = new BSTNode(iter.next());
        if (n/2 >= 1) {
            root.myLeft = linkedListToTree(leftList.iterator(), n/2);
        }
        if (n - count >= 1) {
            root.myRight = linkedListToTree(iter, n - count);
        }
        return root;
    }

    public void printPreorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printPreorder();
            System.out.println();
        }
    }

    public void printInorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printInorder();
            System.out.println();
        }
    }

    protected static void print(BST t) {
        System.out.println("in preorder");
        t.printPreorder();
        System.out.println("in inorder");
        t.printInorder();
        System.out.println();
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;

        public BSTNode(Object item) {
            myItem = item;
            myLeft = myRight = null;
        }

        public BSTNode(Object item, BSTNode left, BSTNode right) {
            myItem = item;
            myLeft = left;
            myRight = right;
        }

        private void printPreorder() {
            System.out.print(myItem + " ");
            if (myLeft != null) {
                myLeft.printPreorder();
            }
            if (myRight != null) {
                myRight.printPreorder();
            }
        }

        private void printInorder() {
            if (myLeft != null) {
                myLeft.printInorder();
            }
            System.out.print(myItem + " ");
            if (myRight != null) {
                myRight.printInorder();
            }
        }
    }

    public static void main(String[] args) {
        String[] one = {"a", "b", "c", "d", "e", "f", "g"};
        LinkedList treeOne = new LinkedList(Arrays.asList(one));
        BST bstOne = new BST(treeOne);
        print(bstOne);

        String[] two = {"a", "b", "c"};
        LinkedList treeTwo = new LinkedList(Arrays.asList(two));
        BST bstTwo = new BST(treeTwo);
        print(bstTwo);

        Integer[] three = {1, 2, 3, 4, 5};
        LinkedList treeThree = new LinkedList(Arrays.asList(three));
        BST bstThree = new BST(treeThree);
        print(bstThree); // wtf
    }
}
