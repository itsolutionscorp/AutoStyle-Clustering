import java.util.List;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;


public class BinaryTree<T extends Comparable<T>> implements Iterable<T> {

    protected TreeNode myRoot;
    protected int positionI = -1;
//    protected ArrayList list1;
//    protected ArrayList list2;
    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public BinaryTree(List<T> preorder,List<T> inorder) {
    	myRoot = (BinaryTree<T>.TreeNode) preorder.get(0);
    	makeTree(myRoot,preorder,inorder);
    }
    
    public void makeTree(TreeNode t, List<T> preorder, List<T> inorder){
    	if (myRoot != null) {
    		myRoot.makeTree(t, preorder, inorder);
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
    
//    public void add(T key) {
//        myRoot = add(myRoot, key);
//    }

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
        public int posInorder;
        public int indexPreorder = 1;

        public TreeNode(T item) {
            myItem = item;
            myLeft = myRight = null;
        }

        public TreeNode(T item, TreeNode left, TreeNode right) {
            myItem = item;
            myLeft = left;
            myRight = right;
        }
        public TreeNode makeTree(TreeNode t, List<T> preorder,List<T> inorder) {
        	if (preorder.size() -1 == indexPreorder) {
        		return null;
        	}
        	if (inorder.contains(t)) {
        	posInorder = inorder.indexOf(t);
        	}
        	else {
        		throw new IllegalArgumentException("Error");
        	}
        	if (preorder.get(indexPreorder) == inorder.get(posInorder-1)) {
        		t.myLeft = (BinaryTree<T>.TreeNode) preorder.get(indexPreorder);
        		indexPreorder++;
        		makeTree(t.myLeft, preorder, inorder);
        	}
        	else if (preorder.get(indexPreorder) == inorder.get(posInorder+1)) {
        		t.myRight = (BinaryTree<T>.TreeNode) preorder.get(indexPreorder);
        	}
			return t;
        }
        private TreeNode add(TreeNode t, T key) {
            if (t == null) {
                return new TreeNode(key);
            } else if (key.compareTo(t.myItem) < 0) {
                t.myLeft = add(t.myLeft, key);
                return t;
            } else {
                t.myRight = add(t.myRight, key);
                return t;
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