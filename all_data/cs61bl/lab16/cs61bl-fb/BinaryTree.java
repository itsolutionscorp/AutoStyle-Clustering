import java.util.Iterator;
import java.util.ArrayList;
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
    public BinaryTree(ArrayList<T> input_order, ArrayList<T> compare_order){
    	BinaryTree result = new BinaryTree();
    	Iterator<T> input_order_iterator = input_order.iterator();
    	while(input_order_iterator.hasNext()){
            T k = input_order_iterator.next();
            myRoot = addHelper(myRoot, k, compare_order); 
    	    
            
//    		if(result.myRoot==null){
//    			result.myRoot = new TreeNode(k);
//    		}else{
//    			if(compare_order.indexOf(k) < compare_order.indexOf(myRoot.myItem)){
//    				if(result.myRoot.myLeft!= null){
//    					input_order.remove(k);
//    					result.myRoot = 
//    				}
//    					
//    			}
//    			
//    		}
    		
    		
    	}
    }


    	private TreeNode addHelper (TreeNode t, T key, ArrayList<T> compare_order) {
    	    if (t == null) {
    	        return new TreeNode(key);
    	    }
    	    if (compare_order.indexOf(key) < compare_order.indexOf(t.myItem)) {
    	        t.myLeft = addHelper(t.myLeft, key, compare_order);
    	        return t;
    	    } else {
    	    	t.myRight = addHelper(t.myRight, key, compare_order);
    	        return t;
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
        ArrayList<String> input_order = new ArrayList<String>();
        ArrayList<String> compare_order = new ArrayList<String>();
        input_order.add("A");
        input_order.add("B");
        input_order.add("C");
        input_order.add("D");
        input_order.add("E");
        input_order.add("F");
        compare_order.add("B");
        compare_order.add("A");
        compare_order.add("E");
        compare_order.add("D");
        compare_order.add("F");
        compare_order.add("C");
        BinaryTree<String> b = new BinaryTree<String>(input_order,compare_order);
        print(b, "Binary Tree sample");
        System.out.println(b.myRoot.myItem);
        System.out.println(b.myRoot.myLeft.myItem);
        System.out.println(b.myRoot.myRight.myItem);
        System.out.println(b.myRoot.myRight.myLeft.myItem);
        System.out.println(b.myRoot.myRight.myLeft.myLeft.myItem);
        System.out.println(b.myRoot.myRight.myLeft.myRight.myItem);
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