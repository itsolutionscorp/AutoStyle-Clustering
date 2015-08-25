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
    
    public BinaryTree(ArrayList<T> preOrder, ArrayList<T> inOrder){
    	
    	myRoot = treeHelper(preOrder, inOrder);
    	/*myRoot = new TreeNode(preOrder.get(0));
    	int i = (inOrder.indexOf(preOrder.get(0)));
    	int j = (inOrder.indexOf(preOrder.get(1)));
    	int k = (inOrder.indexOf(preOrder.get(2)));
    	
    	//set myLeft to a BinaryTree with preOrder, inOrder
    	//set myRight to a BinaryTree with preOrder, inOrder
    	myRoot.myLeft = 
    	if(i>j){
    		myRoot.myLeft = new TreeNode(inOrder.get(i));
    		
    	} else {
    		myRoot.myRight = new TreeNode(inOrder.get(i));
    	}*/
    }
    
    private TreeNode treeHelper(ArrayList<T> preOrder, ArrayList<T> inOrder){
    	if (preOrder.size() == 0){
    		return null;
    	} else if (preOrder.size() == 1){
    		return new TreeNode(preOrder.get(0));
    	} else if (preOrder.size() == 2){
    		if (inOrder.indexOf(preOrder.get(0)) == 0){
    			return new TreeNode(preOrder.get(0), null, new TreeNode(preOrder.get(1)));
    		} else {
    			return new TreeNode(preOrder.get(0), new TreeNode(preOrder.get(1)), null);
    		}
    	} else {
    		int rootPos = inOrder.indexOf(preOrder.get(0));
    		ArrayList<T> leftPre = new ArrayList<T>(preOrder.subList(1, rootPos+1));
    		ArrayList<T> leftIn = new ArrayList<T>(inOrder.subList(0, rootPos));
    		TreeNode left = treeHelper(leftPre, leftIn);
    		ArrayList<T> rightPre = new ArrayList<T>(preOrder.subList(rootPos+1, preOrder.size()));
    		ArrayList<T> rightIn = new ArrayList<T>(inOrder.subList(rootPos+1, preOrder.size()));
    		TreeNode right = treeHelper(rightPre, rightIn);
    		return new TreeNode(preOrder.get(0), left, right);
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
        //print(t, "the empty tree");
        BinaryTree<String> s = fillSampleTree1();
        //print(s, "sample tree 1");
        BinaryTree<String> r = fillSampleTree2();
        //print(r, "sample tree 2");
        ArrayList<String> pre=new ArrayList<String>();
        pre.add("A");
        pre.add("B");
        pre.add("C");
        pre.add("D");
        pre.add("E");
        pre.add("F");
        ArrayList<String> in=new ArrayList<String>();
        in.add("B");
        in.add("A");
        in.add("E");
        in.add("D");
        in.add("F");
        in.add("C");
        t = new BinaryTree(pre, in);
        print(t, "testTree!!");
        
        ArrayList<String> pre2=new ArrayList<String>();
        pre2.add("A");
        pre2.add("B");
        
        ArrayList<String> in2=new ArrayList<String>();
        in2.add("B");
        in2.add("A");
        t = new BinaryTree(pre2, in2);
        print(t, "testTree2!!");
        
        ArrayList<String> pre3=new ArrayList<String>();
        pre3.add("A");
        ArrayList<String> in3=new ArrayList<String>();
        in3.add("A");
        t = new BinaryTree(pre3, in3);
        print(t, "testTree3!!");
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