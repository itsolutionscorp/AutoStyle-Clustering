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
    
    public BinaryTree(ArrayList<T> preorder, ArrayList<T> inorder) {
    	myRoot = buildHelper(preorder, inorder);
    }
    
    private TreeNode buildHelper(ArrayList<T> preorder, ArrayList<T> inorder) {
    	if (preorder.isEmpty()) {
    		return null;
    	} else {
    		T root = preorder.get(0);
    		int rootLoc = inorder.indexOf((T) root);
    		int i = 0;
    		ArrayList<T> preorderLeft = new ArrayList<T>();
    		ArrayList<T> inorderLeft = new ArrayList<T>();
    		ArrayList<T> preorderRight = new ArrayList<T>();
    		ArrayList<T> inorderRight = new ArrayList<T>();
    		while (i < rootLoc) {
    			preorderLeft.add(preorder.get(i + 1));
    			inorderLeft.add(inorder.get(i));
    			i++;
    		}
    		i++;
    		while (i < preorder.size()) {
    			preorderRight.add(preorder.get(i));
    			inorderRight.add(inorder.get(i));
    			i++;
    		}
    		return new TreeNode(root, buildHelper(preorderLeft, inorderLeft), buildHelper(preorderRight, inorderRight));
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
        ArrayList<String> s2_po = new ArrayList<String>();
        s2_po.add("a");
        s2_po.add("b");
        s2_po.add("c");
        ArrayList<String> s2_io = new ArrayList<String>();
        s2_io.add("b");
        s2_io.add("a");
        s2_io.add("c");
        BinaryTree<String> s2 = new BinaryTree<String>(s2_po, s2_io);
        print(s, "sample tree 1");
        print(s2, "sample tree 1 by new constructor");
        
        
        BinaryTree<String> r = fillSampleTree2();
        print(r, "sample tree 2");
        ArrayList<String> r2_po = new ArrayList<String>();
        r2_po.add("a");
        r2_po.add("b");
        r2_po.add("d");
        r2_po.add("e");
        r2_po.add("f");
        r2_po.add("c");
        ArrayList<String> r2_io = new ArrayList<String>();
        r2_io.add("e");
        r2_io.add("d");
        r2_io.add("f");
        r2_io.add("b");
        r2_io.add("a");
        r2_io.add("c");
        BinaryTree<String> r2 = new BinaryTree<String>(r2_po, r2_io);
        print(r2, "sample tree 2 by new constructor");
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