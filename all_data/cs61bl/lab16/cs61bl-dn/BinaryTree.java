import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Stack;
import java.util.NoSuchElementException;
import java.util.List;

public class BinaryTree<T> implements Iterable<T> {

    protected TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public BinaryTree(ArrayList<T> preorder, ArrayList<T> inorder) {
    	T item = preorder.get(0);
    	int index = inorder.indexOf(item);
    	myRoot = new TreeNode(item);
    	myRoot.myLeft = combineHelper(preorder.subList(1, 1+index), inorder.subList(0, index));
    	List<T> pre = preorder.subList(1+index, preorder.size());
    	List<T> in = inorder.subList(1+index, inorder.size());
    	myRoot.myRight = combineHelper(pre, in);
    }
    
    public TreeNode combineHelper(List<T> preorder, List<T> inorder) {
    	if (preorder.size() == 0) {
    		return null;
    	}
    	T item = preorder.get(0);
    	int index = inorder.indexOf(item);
    	TreeNode node = new TreeNode(item);
    	node.myLeft = combineHelper(preorder.subList(1, 1+index), inorder.subList(0, index));
    	List<T> pre = preorder.subList(1+index, preorder.size());
    	List<T> in = inorder.subList(1+index, inorder.size());
    	node.myRight = combineHelper(pre, in);
    	return node;
    }

    // Print the values in the tree in preorder: root value first,
    // then values in the left subtree (in preorder), then values
    // in the right subtree (in preorder).
    public void printPreorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printPreorder();
            System.out.println();
        }
    }

    // Print the values in the tree in inorder: values in the left
    // subtree first (in inorder), then the root value, then values
    // in the right subtree (in inorder).
    public void printInorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printInorder();
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
//        BinaryTree<String> t = new BinaryTree<String>();
//        print(t, "the empty tree");
//        BinaryTree<String> s = fillSampleTree1();
//        print(s, "sample tree 1");
//        BinaryTree<String> r = fillSampleTree2();
//        print(r, "sample tree 2");
        BinaryTree<String> y = new BinaryTree<String>(new ArrayList<String>(Arrays.asList("A","B","C","D","E","F")),
        		new ArrayList<String>(Arrays.asList("B","A","E","D","F","C")));
        y.printInorder();
        y.printPreorder();
    }

    protected static void print(BinaryTree<?> t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    // Method for the BinaryTree class
    public Iterator<T> iterator(){
        return new InorderIterator();
    }

    // Inner class inside of the BinaryTree class.
    // Also, it uses java.util.Iterator and java.util.Stack.
    private class InorderIterator implements Iterator<T> {
        private Stack<TreeNode> nodeStack;
        private TreeNode currentNode;

        public InorderIterator() {
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
}