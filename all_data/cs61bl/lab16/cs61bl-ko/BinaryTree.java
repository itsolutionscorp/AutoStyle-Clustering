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
    
    public BinaryTree(ArrayList<T> arr1, ArrayList<T> arr2) {
    	ArrayList<T> leftIn = new ArrayList<T>();
    	ArrayList<T> rightIn = new ArrayList<T>();
    	ArrayList<T> leftPre = new ArrayList<T>();
    	ArrayList<T> rightPre = new ArrayList<T>();
    	if (arr1.isEmpty() && arr2.isEmpty()) {
    		return;
    	}
    	int temp = arr2.indexOf(arr1.get(0)); 
    	for (int i = 0; i < temp; i++) {
    		leftIn.add(arr2.get(i));
    	}
    	for (int i = temp + 1; i < arr2.size(); i++) {
    		rightIn.add(arr2.get(i));
    	}
    	int leftLength = leftIn.size();
    	int rightLength = rightIn.size();
    	for (int i = 1; i < temp + 1; i++) {
    		leftPre.add(arr1.get(i));
    	}
    	for (int i = temp + 1; i < arr1.size(); i++) {
    		rightPre.add(arr1.get(i));
    	}
    	BinaryTree left = new BinaryTree(leftPre, leftIn);
    	BinaryTree right = new BinaryTree(rightPre, rightIn);
    	myRoot = new TreeNode(arr1.get(0));
    	myRoot.myLeft = (TreeNode) left.myRoot;
    	myRoot.myRight = (TreeNode) right.myRoot;
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
        
        BinarySearchTree<String> bst = new BinarySearchTree<String>();
		bst.add("c");
		bst.add("a");
		bst.add("b");
		bst.add("e");
		bst.add("d");
		print(bst, "binary search tree c a b e d");
		
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
		BinaryTree<String> k = new BinaryTree<String>(preorder, inorder);
		print(k, "BT constructor");
		
		ArrayList<String> preorder1 = new ArrayList<String>();
		preorder1.add("A");
		preorder1.add("B");
		preorder1.add("C");
		preorder1.add("D");
		preorder1.add("E");
		preorder1.add("F");
		ArrayList<String> inorder1 = new ArrayList<String>();
		inorder1.add("C");
		inorder1.add("B");
		inorder1.add("D");
		inorder1.add("A");
		inorder1.add("F");
		inorder1.add("E");
		BinaryTree<String> b = new BinaryTree<String>(preorder1, inorder1);
		print(b, "BT1 constructor");
		
		ArrayList<String> preorder2 = new ArrayList<String>();
		preorder2.add("A");
		preorder2.add("B");
		preorder2.add("C");
		preorder2.add("D");
		preorder2.add("E");
		preorder2.add("F");
		preorder2.add("G");
		preorder2.add("H");
		ArrayList<String> inorder2 = new ArrayList<String>();
		inorder2.add("A");
		inorder2.add("C");
		inorder2.add("D");
		inorder2.add("E");
		inorder2.add("G");
		inorder2.add("F");
		inorder2.add("B");
		inorder2.add("H");
		BinaryTree<String> n = new BinaryTree<String>(preorder2, inorder2);
		print(n, "BT2 constructor");
		
		ArrayList<String> preorder3 = new ArrayList<String>();
		ArrayList<String> inorder3 = new ArrayList<String>();
		BinaryTree<String> empty = new BinaryTree<String>(preorder3, inorder3);
		print(empty, "BT3 constructor");
		
		ArrayList<String> preorder4 = new ArrayList<String>();
		preorder4.add("A");
		ArrayList<String> inorder4 = new ArrayList<String>();
		inorder4.add("A");
		BinaryTree<String> m = new BinaryTree<String>(preorder4, inorder4);
		print(m, "BT4 constructor");
		
		ArrayList<String> preorder5 = new ArrayList<String>();
		preorder5.add("A");
		preorder5.add("B");
		preorder5.add("C");
		preorder5.add("D");
		preorder5.add("E");
		preorder5.add("F");
		preorder5.add("G");
		preorder5.add("H");
		ArrayList<String> inorder5 = new ArrayList<String>();
		inorder5.add("H");
		inorder5.add("G");
		inorder5.add("F");
		inorder5.add("E");
		inorder5.add("D");
		inorder5.add("C");
		inorder5.add("B");
		inorder5.add("A");
		BinaryTree<String> p = new BinaryTree<String>(preorder5, inorder5);
		print(p, "BT5 constructor");
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