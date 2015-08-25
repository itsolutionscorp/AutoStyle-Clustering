import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BinaryTree<T> implements Iterable<T> {

    protected TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }

    public BinaryTree (ArrayList<T> preOrder, ArrayList<T> inOrder) {
    	if (preOrder.size() == 1 && inOrder.size() == 1) {
    		myRoot = new TreeNode(preOrder.get(0));
    	} else if (preOrder.size() == 2 && inOrder.size() == 2) {
    		if (preOrder.get(1).equals(inOrder.get(1))) {
    			myRoot = new TreeNode(preOrder.get(0));
    			myRoot.myRight = new TreeNode(preOrder.get(1));
    		} else {
    			myRoot = new TreeNode(preOrder.get(0));
    			myRoot.myLeft = new TreeNode(preOrder.get(1));
    		}
    	} else if (preOrder.size() == 0) {
    		myRoot = null;
    	} else {
	    	T root = preOrder.get(0);
	    	ArrayList<T> leftTreeIO = new ArrayList<T>();
	    	for (T elem : inOrder) {
	    		if (elem.equals(root)) break;
	    		leftTreeIO.add(elem);
	    	}
	    	int rightPos = leftTreeIO.size() + 1;
	    	ArrayList<T> rightTreePO = new ArrayList<T>(preOrder.subList(rightPos, preOrder.size()));
	    	ArrayList<T> rightTreeIO = new ArrayList<T>(inOrder.subList(rightPos, inOrder.size()));
	    	ArrayList<T> leftTreePO = new ArrayList<T>(preOrder.subList(1, rightPos));
	    	BinaryTree<T> leftTree = new BinaryTree<T>(leftTreePO, leftTreeIO);
	    	BinaryTree<T> rightTree = new BinaryTree<T>(rightTreePO, rightTreeIO);
	    	myRoot = new TreeNode(root);
	    	myRoot.myLeft = leftTree.myRoot;
	    	myRoot.myRight = rightTree.myRoot;
    	}
    }

    
    // Print the values in the tree in preorder: root { value first,
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
    	ArrayList<String> preOrder = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F"));
    	ArrayList<String> inOrder = new ArrayList<String>(Arrays.asList("B", "A", "E", "D", "F", "C"));
    	BinaryTree<String> tree = new BinaryTree<String>(preOrder, inOrder);
    	tree.printPreorder();
    	tree.printInorder();
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