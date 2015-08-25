import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    
    public BinaryTree(ArrayList<T> PREORDER, ArrayList<T> INORDER) {
    	System.out.println("this is INORDER: " + INORDER.toString());
    	System.out.println("this is PREORDER: " + PREORDER.toString());
    	TreeNode CONSTRUCTING;
    	if (PREORDER.isEmpty()) {
    		myRoot = null;
    	} else {
        	T root_item = PREORDER.get(0);
        	System.out.println(root_item);
        	CONSTRUCTING = new TreeNode(root_item);					// construct a node without its child first
        	// build the child recursively
        	if (PREORDER.size() == 1) {
        		myRoot = CONSTRUCTING;
        	} else {																	// recursive case 
        		// get the root position in INORDER 
            	int root_index_INORDER = INORDER.indexOf(root_item);
            	// split inorder to get left and right side of the tree
            	System.out.println("root index in order is: " + root_index_INORDER);
            	System.out.println("inorder size is: " + INORDER.size());
            	ArrayList<T> RIGHT_INORDER = new ArrayList<T>(INORDER.subList(root_index_INORDER + 1, INORDER.size()));
            	ArrayList<T> LEFT_INORDER = new ArrayList<T>(INORDER.subList(0, root_index_INORDER));

            	// get left and right items of the tree
            	ArrayList<T> LEFT_PREORDER;
            	ArrayList<T> RIGHT_PREORDER;
            	try {
            		T left_item = LEFT_INORDER.get(LEFT_INORDER.size() - 1);
            		LEFT_PREORDER = new ArrayList<T>(PREORDER.subList(1, PREORDER.indexOf(left_item) + 1));
            	} catch (IndexOutOfBoundsException e) {
            		T left_item = null;
            		LEFT_PREORDER = new ArrayList<T>();
            	}
            	try {
            		T right_item = RIGHT_INORDER.get(RIGHT_INORDER.size() - 1);
            		RIGHT_PREORDER = new ArrayList<T>( PREORDER.subList(PREORDER.indexOf(right_item), PREORDER.size()));
            	} catch (IndexOutOfBoundsException e) {
            		T right_item = null;
            		RIGHT_PREORDER =  new ArrayList<T>();
            	}
            	//Split things into sublists 

            	
            	
            	BinaryTree left_TREE = new BinaryTree(LEFT_PREORDER, LEFT_INORDER);
            	BinaryTree right_TREE = new BinaryTree(RIGHT_PREORDER, RIGHT_INORDER);
            	CONSTRUCTING.myLeft = (TreeNode) left_TREE.myRoot;
            	CONSTRUCTING.myRight = (TreeNode) right_TREE.myRoot;
        	}
        	myRoot = CONSTRUCTING;
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
//        BinaryTree<String> t = new BinaryTree<String>();
//        print(t, "the empty tree");
//        BinaryTree<String> s = fillSampleTree1();
//        print(s, "sample tree 1");
//        BinaryTree<String> r = fillSampleTree2();
//        print(r, "sample tree 2");
        
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
        
        BinaryTree<String> a = new BinaryTree<String>(preorder, inorder);
        print(a, "please work");
        
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