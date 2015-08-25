import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class BinaryTree<T> implements Iterable<T> {

    protected TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }

    public BinaryTree (ArrayList<T> preorder, ArrayList<T> inorder) {
        myRoot = constructTree(preorder, inorder);
    }

    public TreeNode constructTree(ArrayList<T> preorder, ArrayList<T> inorder) {
        if ((preorder.size() == 0) || (inorder.size() == 0)) {
            return null;
        }
        TreeNode node = new TreeNode(preorder.get(0));
        int inorderPos = inorder.indexOf(preorder.get(0));
        ArrayList<T> inorderLeft =
            new ArrayList<T>(inorder.subList(0, inorderPos));
        ArrayList<T> inorderRight =
            new ArrayList<T>(inorder.subList(inorderPos + 1, inorder.size()));
        int preorderPos = inorderLeft.size();
        ArrayList<T> preorderLeft =
            new ArrayList<T>(preorder.subList(1, preorderPos + 1));
        ArrayList<T> preorderRight =
            new ArrayList<T>(preorder.subList(preorderPos+1, preorder.size()));

        node.myLeft = constructTree(preorderLeft, inorderLeft);
        node.myRight = constructTree(preorderRight, inorderRight);
        return node;
    }

    // Print the values in the tree in preorder: root value first,
    // then values in the left subtree (in preorder), then values
    // in the right subtree (in preorder).
    public void printpreorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printpreorder();
            System.out.println();
        }
    }

    // Print the values in the tree in inorder: values in the left
    // subtree first (in inorder), then the root value, then values
    // in the right subtree (in inorder).
    public void printinorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printinorder();
            System.out.println();
        }
    }

    public static BinaryTree<String> fillSampleTree1() {
        BinaryTree<String> t = new BinaryTree<String>();
        t.myRoot = t.new TreeNode("a", t.new TreeNode("b"), t.new TreeNode("c"));
        return t;
    }

    public static BinaryTree<String> fillSampleTree2() {
        BinaryTree<String> t = new BinaryTree<String>();
        t.myRoot = t.new TreeNode("a", t.new TreeNode("b", t.new TreeNode("d", t.new TreeNode("e"), t.new TreeNode("f")), null), t.new TreeNode("c"));
        return t;
    }

    public static void main(String[] args) {
        BinaryTree<String> t = new BinaryTree<String>();
        print(t, "the empty tree");
        BinaryTree<String> s = fillSampleTree1();
        print(s, "sample tree 1");
        BinaryTree<String> r = fillSampleTree2();
        print(r, "sample tree 2");
        ArrayList<String> preorder = new ArrayList<String>();
        preorder.add("A");
        preorder.add("B");
        preorder.add("C");
        preorder.add("D");
        preorder.add("E");
        preorder.add("F");
        ArrayList<String> inorder = new ArrayList<String>();
        inorder.add("B");
        inorder.add("A");
        inorder.add("E");
        inorder.add("D");
        inorder.add("F");
        inorder.add("C");
        BinaryTree<String> testorder =new BinaryTree<String>(preorder, inorder);
        print(testorder, "fill by order");
    }

    protected static void print(BinaryTree<?> t, String description) {
        System.out.println(description + " in preorder");
        t.printpreorder();
        System.out.println(description + " in inorder");
        t.printinorder();
        System.out.println();
    }

    // Method for the BinaryTree class
    public Iterator<T> iterator(){
        return new inorderIterator();
    }

    // Inner class inside of the BinaryTree class.
    // Also, it uses java.util.Iterator and java.util.Stack.
    private class inorderIterator implements Iterator<T> {
        private Stack<TreeNode> nodeStack;
        private TreeNode currentNode;

        public inorderIterator() {
            nodeStack = new Stack<TreeNode>();
            currentNode = myRoot;
        }

        public boolean hasNext() {
            return !nodeStack.isEmpty() || (currentNode != null);
        }

        public T next() {
            TreeNode nextNode = null;

            // find leftmost node with no left child
            while (currentNode != null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.myLeft;
            }

            // get leftmost node, then move to its right subtree
            if (!nodeStack.isEmpty()) {
                nextNode = nodeStack.pop();
                assert nextNode != null;    // since nodeStack was not empty before the pop
                currentNode = nextNode.myRight;
            } else {
                throw new NoSuchElementException();
            }

            return nextNode.myItem;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    protected class TreeNode {

        public T myItem;
        public TreeNode myLeft;
        public TreeNode myRight;

        public TreeNode(T item) {
            myItem = item;
            myLeft = myRight = null;
        }

        public TreeNode(T item, TreeNode left, TreeNode right) {
            myItem = item;
            myLeft = left;
            myRight = right;
        }

        private void printpreorder() {
            System.out.print(myItem + " ");
            if (myLeft != null) {
                myLeft.printpreorder();
            }
            if (myRight != null) {
                myRight.printpreorder();
            }
        }

        private void printinorder() {
            if (myLeft != null) {
                myLeft.printinorder();
            }
            System.out.print(myItem + " ");
            if (myRight != null) {
                myRight.printinorder();
            }
        }
    }
}
