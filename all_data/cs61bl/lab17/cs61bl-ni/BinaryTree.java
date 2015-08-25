import java.util.ArrayList;
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
    
    public BinaryTree(ArrayList<T> preorder, ArrayList<T> inorder) {
		myRoot = null;
		if (preorder == null) {
			return;
		}
		for (int pos = 0; pos < preorder.size(); pos++) {
			myRoot = helper(myRoot, pos, preorder, inorder);
		}
	}

	private TreeNode helper(TreeNode t, int pos, ArrayList<T> preorder,
			ArrayList<T> inorder) {
		if (t == null) {
			return new TreeNode(preorder.get(pos));
		}
		if (inorder.indexOf(preorder.get(pos)) < inorder.indexOf(t.myItem)) {
			t.myLeft = helper(t.myLeft, pos, preorder, inorder);
		} else {
			t.myRight = helper(t.myRight, pos, preorder, inorder);
		}
		return t;
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
        BinaryTree<String> arr = new BinaryTree<String>(preorder, inorder);
        print(arr, "test arraylist");
        
        preorder.add("G");
        preorder.add("H");
        preorder.add("J");
        preorder.add("I");       
        ArrayList<String> inorder2 = new ArrayList<String>();
        inorder2.add("C");
        inorder2.add("B");
        inorder2.add("E");
        inorder2.add("D");
        inorder2.add("F");
        inorder2.add("A");
        inorder2.add("J");
        inorder2.add("H");
        inorder2.add("G");
        inorder2.add("I");
        BinaryTree<String> arr2 = new BinaryTree<String>(preorder, inorder2);
        
        print(arr2, "test arraylist2");
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
        public int size;

        public TreeNode(T item) {
            myItem = item;
            myLeft = myRight = null;
            size = 1;
        }

        public TreeNode(T item, TreeNode left, TreeNode right) {
            myItem = item;
            myLeft = left;
            myRight = right;
            size = 1;
            if (left != null) {
            	size += left.size;
            }
            if (right != null) {
            	size += right.size;
            }
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