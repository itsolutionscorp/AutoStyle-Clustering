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
    
    public BinaryTree(ArrayList<T> preorder, ArrayList<T> inorder){    	      	
    	// PREORDER ["A", "B", "C", "D", "E", "F"]  root, left, right
    	// INORDER ["B", "A", "E", "D", "F", "C"]  left, root, right
    	myRoot = createTree(preorder, inorder);
    	
    }
    public TreeNode createTree(ArrayList<T> preorder, ArrayList<T> inorder){
    	
    	if(preorder.isEmpty() || inorder.isEmpty()){
    		return null;
    	}
    	
    	T root = preorder.get(0);
    	
    	// index of root inside INORDER
    	int indexOfInorderRoot = inorder.indexOf(root);
    	
    	// create a LEFT TREE sublist for the INORDER ArrayList
    	ArrayList<T> inorderLeftList = new ArrayList<T>(inorder.subList(0, indexOfInorderRoot));
    	

    	    	
    	// create a RIGHT TREE sublist for the INORDER ArrayList
    	ArrayList<T> inorderRightList = new ArrayList<T>(inorder.subList(indexOfInorderRoot + 1, inorder.size()));
    	
    	
    	// index of right tree root inside PREORDER
    	int indexOfRightRoot = -1;
    	for(int i = 1; i < preorder.size(); i++){
    		if (!inorderLeftList.contains(preorder.get(i))){
    				indexOfRightRoot = i;
    				break;
    		}
    	}
    	

    	if(indexOfRightRoot == -1){
    		indexOfRightRoot = preorder.size();
    	}
    	
		// create a LEFT LIST subtree for PREORDER ArrayList   
    	ArrayList<T> preorderLeftList = new ArrayList<T>(preorder.subList(1, indexOfRightRoot));    	    	
    	
    	// create a RIGHT LIST subtree for the PREORDER ArrayList	
    	ArrayList<T> preorderRightList = new ArrayList<T>(preorder.subList(indexOfRightRoot, preorder.size()));    	    	
    	
    	TreeNode left = createTree(preorderLeftList, inorderLeftList);
    	TreeNode right = createTree(preorderRightList, inorderRightList);
    	return new TreeNode(root, left, right);    	    	    	
    }
    
    


	//===========FROM LAB 15==============
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
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
        	
        	
        	
       
        //=============TAKEN FROM LAB 15 FOR CONVENIENCE=============
        private static final String indent1 = "    ";

        private void print(int indent) {        	
        	if(myRight != null) myRight.print(indent + 1);
            println (myItem, indent);
            if(myLeft != null) myLeft.print(indent + 1);
        }

        private void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
    }
}