import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;
import java.util.*;

public class BinaryTree<T> implements Iterable<T> {

    protected TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public BinaryTree(ArrayList<T> batman, ArrayList<T> robin) {
    	myRoot = Helper(batman, robin);
    }
    
    private TreeNode Helper(ArrayList<T> batman, ArrayList<T> robin) {
    	if(batman == null && robin == null){
    		return null;
    	}
    	if (batman.size() == 1 && robin.size() == 1) {
    		return new TreeNode(batman.get(0));
    	}
    	else {	
    		T root = batman.get(0);
	    	int robinRoot = robin.indexOf(root);
	    	int batmanLeft = batman.indexOf(robin.get(robinRoot - 1));
	    	ArrayList<T> batmanLeftSub = null;
	    	ArrayList<T> robinLeftSub = null;
			if (robinRoot > 0) { 
				batmanLeftSub = new ArrayList<>(batman.subList(1, batmanLeft + 1));
				robinLeftSub = new ArrayList<>(robin.subList(0, robinRoot));
			}
	    	ArrayList<T> batmanRightSub = null;
	    	ArrayList<T> robinRightSub = null;
			if (robinRoot < robin.size() - 1) {
				batmanRightSub = new ArrayList<>(batman.subList(batmanLeft + 1, batman.size()));
				robinRightSub = new ArrayList<>(robin.subList(robinRoot + 1, robin.size()));
			}
	    	return new TreeNode(root, Helper(batmanLeftSub, robinLeftSub), Helper(batmanRightSub, robinRightSub));
    	}
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
    
    public static BinarySearchTree<String> fillSampleTree3() {
    	BinarySearchTree<String> t = new BinarySearchTree<String>();
    	t.myRoot = t.new TreeNode("K", t.new TreeNode("D", t.new TreeNode("B", null, null), t.new TreeNode("E", null, null)), t.new TreeNode("L", t.new TreeNode("J", null, null), t.new TreeNode("U", null, null)));
    	return t;
    }

    public static void main(String[] args) {
        BinaryTree<String> t = new BinaryTree<String>();
        print(t, "the empty tree");
        BinaryTree<String> s = fillSampleTree1();
        print(s, "sample tree 1");
        BinaryTree<String> r = fillSampleTree2();
        print(r, "sample tree 2");
        BinarySearchTree<String> q = fillSampleTree3();
        System.out.println(q.contains("A"));
        q.add("F");
        print(q, "sample tree 3");
        String[] batmanArray = new String[] {"A", "B", "D", "H", "I", "E", "J", "K", "C", "F", "L", "M", "G", "N", "O"};
        String[] robinArray = new String[] {"H", "D", "I", "B", "J", "E", "K", "A", "L", "F", "M", "C", "N", "G", "O"};
        ArrayList<String> batman = new ArrayList<>(Arrays.asList(batmanArray));
        ArrayList<String> robin = new ArrayList<>(Arrays.asList(robinArray));
        BinaryTree yggdrasil = new BinaryTree(batman, robin);
        print(yggdrasil, "The Tree of Life");
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