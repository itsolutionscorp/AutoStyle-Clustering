import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BinaryTree<T extends Comparable<T>> implements Iterable<T> {

    protected TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public BinaryTree(List<T> preorder, List<T> inorder){
    	if(preorder.size() == 0)
    		myRoot = null;
    	else 
    		myRoot = constructorHelper(preorder, inorder);
    }
    
    public TreeNode constructorHelper(List<T> preorder, List<T> inorder){
    	T rootItem = preorder.get(0);
    	TreeNode t = new TreeNode(rootItem);
    	if(preorder.size() == 1)
    		return t;
    	int pos = inorder.indexOf(rootItem);
    	if(pos > 0){
    		t.myLeft = constructorHelper(preorder.subList(1, pos + 1), inorder.subList(0, pos));
    	}
    	if(pos < inorder.size() - 1){
    		t.myRight = constructorHelper(preorder.subList(pos + 1, preorder.size()),
    				inorder.subList(pos + 1, inorder.size()));
    	}
    	return t;
    }

    public boolean contains (T key) {
    	if (myRoot == null)
    		return false;
    	else
    		return containsHelper(myRoot, key);
    }
    
    private boolean containsHelper(TreeNode node, T key) {
    	
    	if (node.myItem.compareTo(key) == 0)
    		return true;
    	else if(key.compareTo(node.myItem) < 0)
    		return containsHelper(node.myLeft, key);
    	else
    		return containsHelper(node.myRight, key);
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
/*
    public static BinaryTree<String> fillSampleTree2() {
        BinaryTree<String> t = new BinaryTree<String>();
        t.myRoot = t.new TreeNode(new List("a", null), new List("a", null));
        return t;
    }
*/
    public static void main(String[] args) {
        BinaryTree<String> t = new BinaryTree<String>();
        print(t, "the empty tree");
        BinaryTree<String> s = fillSampleTree1();
        print(s, "sample tree 1");
        //BinaryTree<String> r = fillSampleTree2();
        //print(r, "sample tree 2");
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
       

        public InorderIterator() {
            nodeStack = new Stack<TreeNode>();
            if(myRoot != null){
            	nodeStack.push(myRoot);
            	while(nodeStack.peek().myLeft != null)
            		nodeStack.push(nodeStack.peek().myLeft);
            }
        }

        public boolean hasNext() {
            return !nodeStack.isEmpty();
        }

        public T next() {
        	TreeNode temp;
        	if(hasNext()){
        		temp = nodeStack.pop();
	        	if(temp.myRight != null){
	        		nodeStack.push(temp.myRight);
	        		while(nodeStack.peek().myLeft != null)
	        			nodeStack.push(nodeStack.peek().myLeft);
	        	}
        	} else {
        		throw new IllegalStateException("Ran out of elements!");
        	}
        	return (T) temp.myItem;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public class TreeNode {

        public T myItem;
        public TreeNode myLeft;
        public TreeNode myRight;
        public Integer size;

        public TreeNode(T rootItem) {
            myItem = rootItem;
            myLeft = myRight = null;
            size = 1;
        }

        public TreeNode(T item, TreeNode left, TreeNode right) {
            myItem =  item;
            myLeft = left;
            myRight = right;
            size = left.size + right.size + 1;
        }
        
        
        public int getSize(){
        	return this.size;
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