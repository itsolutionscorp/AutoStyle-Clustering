import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
    
    protected boolean contains(T key){
    	if (myRoot == null) return false;
    	return contains(myRoot, key);
    }

    public boolean contains (TreeNode t, T key){
    	if (t == null) return false;
    	if (t.myItem.compareTo(key) == 0) return true;
    	if (t.myLeft != null) contains(t.myLeft, key);
    	if (t.myRight != null) contains(t.myRight, key);
    	return false;
    }
    
    public void add(T key) {
        myRoot = add(myRoot, key);
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
    
    public BinaryTree<T> recon(ArrayList<T> a1, ArrayList<T> a2){
        BinaryTree<T> result = new BinaryTree<T>();
        result.myRoot = result.reconstruct(a1, a2);
        return result;
    }
    
    public TreeNode reconstruct(ArrayList<T> a1, ArrayList<T> a2){
    	if (a1.size() <= 3){
    		if (a1.size() == 3){
    			T item = a1.get(0);
    			TreeNode left = new TreeNode(a1.get(1));
    			TreeNode right = new TreeNode(a1.get(2));
    		    return new TreeNode(item, left, right);
    		}
    		else if (a1.size() == 2){
    			if (a1.get(1).compareTo(a2.get(1)) != 0) 
    				return new TreeNode(a1.get(0), new TreeNode(a1.get(1)), null);
    		    else return new  TreeNode(a1.get(0), null, new TreeNode(a1.get(1)));
    		}
    		else return new TreeNode(a1.get(0));
    	}
    	int split = a2.indexOf(a1.get(0));
    	if(split != 0 && split != a1.size()-1){
    		ArrayList<T> b1,b2,c1,c2;
    		b1 = new ArrayList<T>();
    		b2 = new ArrayList<T>();
    		c1 = new ArrayList<T>();
    		c2 = new ArrayList<T>();
    		for (int i = 1; i < a1.size(); i++){
    			if (i <= split) {
    				b1.add((T)(a1.get(i)));
    				c1.add((T)(a2.get(i-1)));
    			}
    			else {
    				b2.add((T)(a1.get(i)));
    				c2.add((T)(a2.get(i)));
    			}
    		}
    		return new TreeNode(a1.get(0), reconstruct(b1, c1), reconstruct(b2, c2));
    	}
    	else if (split == 0 && split != a1.size()-1){
    		ArrayList<T> b1,c1;
    		b1 = new ArrayList<T>();
    		c1 = new ArrayList<T>();
    		for (int i = 1; i < a1.size(); i++){
    				b1.add((T)(a1.get(i)));
    				c1.add((T)(a2.get(i)));
    		}
    		return new TreeNode(a1.get(0), null, reconstruct(b1, c1));
    	}
    	else {
    		ArrayList<T> b1,c1;
    		b1 = new ArrayList<T>();
    		c1 = new ArrayList<T>();
    		for (int i = 1; i < a1.size(); i++){
    				b1.add((T)(a1.get(i)));
    				c1.add((T)(a2.get(i-1)));
    		}
    		return new TreeNode(a1.get(0), reconstruct(b1, c1), null);
    	}
    	
    }


    public static void main(String[] args) {
        BinaryTree<String> t = new BinaryTree<String>();
        print(t, "the empty tree");
        BinaryTree<String> s = fillSampleTree1();
        print(s, "sample tree 1");
        BinaryTree<String> r = fillSampleTree2();
        print(r, "sample tree 2");
        System.out.println(r.contains("i"));
        r.add("g");
        print(r, "sample tree 2");
        BinaryTree<String> a = new BinaryTree<String>();
        ArrayList<String> a1 = new ArrayList<String>(
        	    Arrays.asList("A", "B", "C", "D", "E", "F"));
        ArrayList<String> a2 = new ArrayList<String>(
        	    Arrays.asList("B", "A", "E", "D", "F", "C"));
        a = a.recon(a1, a2);
        a.printInorder();
        a.printPreorder();

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