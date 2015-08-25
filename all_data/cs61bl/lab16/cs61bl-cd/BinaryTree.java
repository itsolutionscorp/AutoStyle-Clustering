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
    
    public BinaryTree(ArrayList a1, ArrayList a2){
    	myRoot = new TreeNode(null);
    	myRoot = myRoot.haji(a1, a2);
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
        ArrayList<String> a1 = new ArrayList<String>();
        a1.add("a");
        a1.add("b");
        a1.add("c");
        a1.add("d");
        a1.add("e");
        a1.add("f");
        ArrayList<String> a2 = new ArrayList<String>();
        a2.add("b");
        a2.add("a");
        a2.add("e");
        a2.add("d");
        a2.add("f");
        a2.add("c");
        BinaryTree<String> haji = new BinaryTree<String>(a1, a2);
        print(haji, "haji");
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
        
        private TreeNode haji(ArrayList a1, ArrayList a2){
        	TreeNode hajiNode = new TreeNode(null);
        	if (a1.size() != 0 && a2.size() != 0) {
        		Object root = a1.get(0);
            	hajiNode.myItem = (T) root;
            	int rootIndex = a2.indexOf(root);
        		ArrayList lefta2 = new ArrayList<Object>(a2.subList(0, rootIndex));
        		ArrayList righta2 = new ArrayList<Object>(a2.subList(rootIndex + 1, a2.size()));
        		ArrayList lefta1 = new ArrayList<Object>(a1.subList(1, lefta2.size() + 1));
        		ArrayList righta1 = new ArrayList<Object>(a1.subList(lefta2.size() + 1, a1.size()));
        		if (lefta1.size() != 0){
        		hajiNode.myLeft = haji(lefta1, lefta2);
        		}
        		if (righta1.size() != 0){
        		hajiNode.myRight = haji(righta1, righta2);
        		}
        	}
        	return hajiNode;
        }
        
        
        
    }
}