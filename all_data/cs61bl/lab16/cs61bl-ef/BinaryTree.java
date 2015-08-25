import java.util.Iterator;
import java.util.*;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BinaryTree<T> implements Iterable<T> {

    protected TreeNode myRoot;
    protected ArrayList<T> preorder;
    protected ArrayList<T> inorder;
    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public BinaryTree(ArrayList<T> a, ArrayList<T> b) {
    	preorder = a;
    	inorder = b;
    	if(!a.isEmpty() && !b.isEmpty()) {
    		LinkedList<T> n = new LinkedList<T>();
    		for(T object : a) {
    			n.add(object);
    		}
    		T first = n.remove();
    		int prevIndex = b.indexOf(first);
    		int middle = prevIndex;
    		TreeNode root = new TreeNode(first);
    		myRoot = root;
    		while(n.size() >0) {
    			T object = n.remove();
    			int currIndex = b.indexOf(object);
    			TreeNode root2 = add(root, object, prevIndex, currIndex);
    			if(Math.abs(prevIndex-currIndex) > 1) {
	    			root = root2;
	    			prevIndex = currIndex;
    			}
    			else if((currIndex < prevIndex && currIndex > 0) || (currIndex > prevIndex && currIndex<b.size()-1)) {
    	    			root = root2;
    	    			prevIndex = currIndex;
    				}
    			else {
    				root = myRoot;
    				prevIndex = middle;
    			}
    		}
    	}
    		
    }
    public TreeNode add(TreeNode node, T object, int prevIndex, int currIndex) {
    	if(currIndex < prevIndex) {
    		node.myLeft = new TreeNode(object);
    		return node.myLeft;
    	}
    	else {
    		node.myRight = new TreeNode(object);
    		return node.myRight;
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

    public static void main(String[] args) {
        BinaryTree<String> t = new BinaryTree<String>();
        print(t, "the empty tree");
        BinaryTree<String> s = fillSampleTree1();
        print(s, "sample tree 1");
        BinaryTree<String> r = fillSampleTree2();
        print(r, "sample tree 2");
		BinarySearchTree<String> a = new BinarySearchTree<String>();
		a.add("C");
		a.add("A");
		a.add("E");
		a.add("B");
		a.add("D");
		print(a, "Hey there");
		ArrayList<String> preorder = new ArrayList<String>();
		preorder.add("A");
		preorder.add("B");
		preorder.add("Q");
		preorder.add("C");
		preorder.add("D");
		preorder.add("E");
		preorder.add("F");
		ArrayList<String> inorder = new ArrayList<String>();
		inorder.add("Q");
		inorder.add("B");
		inorder.add("A");
		inorder.add("E");
		inorder.add("D");
		inorder.add("F");
		inorder.add("C");
		BinaryTree<String> bin = new BinaryTree<String>(preorder, inorder);
		print(bin, "here goes!");
		
		ArrayList<String> preOrder = new ArrayList<String>();
		preOrder.add("a");
		preOrder.add("b");
		preOrder.add("c");
		preOrder.add("h");
		preOrder.add("i");
		preOrder.add("d");
		preOrder.add("e");
		preOrder.add("f");
		preOrder.add("g");

		ArrayList<String> inOrder = new ArrayList<String>();
		inOrder.add("i");
		inOrder.add("h");
		inOrder.add("c");
		inOrder.add("b");
		inOrder.add("a");
		inOrder.add("d");
		inOrder.add("e");
		inOrder.add("f");
		inOrder.add("g");

		BinaryTree<String> test = new BinaryTree<String>(preOrder, inOrder);
		test.printPreorder();
		test.printInorder();
        
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