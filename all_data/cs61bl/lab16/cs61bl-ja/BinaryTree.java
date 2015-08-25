import java.util.ArrayList;
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
    
    public BinaryTree(ArrayList<T> l1, ArrayList<T> l2) { 
    	
        //myRoot = new TreeNode(l1.get(0), null, null);
        TreeNode root = myRoot;        
        boolean SawLeft = false;
        boolean SawRight = false;

        for (int i = 0; i < l1.size(); i++){

           if (root == null){
                 root = TreeNode(l1.get(i), null, null);
            }
            else if (l2.indexOf(l1.get(index)) < l2.indexOf(root.myItem) ){
                if (root.myLeft == null){
                   if (SawLeft!){
                   root.myLeft = TreeNode(l1.get(i), null, null); 
                   SawLeft = true;
                   SawRight = false;
                   }
                   else{
                    root = root.myLeft;                    
                    SawLeft = false;
                    SawRight = false;
                    i--;
                   }
                } 
            }
            else {
                if (root.myRight == null){
                   if (!SawRight){
                   root.myRight = TreeNode(l1.get(i), null, null); 
                   SawRight = true;
                   SawLeft = false;
                   }
                   else{
                    root = root.myRight;                    
                    SawLeft = false;
                    SawRight = false;
                    i--;
                   }
                } 
            }           
            
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
    
    private boolean contains (TreeNode t, T key){    	
    	if (t == null) return false;    	
    	int comp = t.myItem.compareTo(key);
    	if (comp == 0){
    		return true;
    	}    	
    	else if(comp < 0){
    		return contains(t.myRight, key);
    	}
    	else{
    		return contains(t.myLeft, key);
    	} 
    }
    
    protected boolean containHelper(T key){
    	return contains(myRoot, key);
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
    	ArrayList<String> yes = new ArrayList<String>();
    	yes.add("A");
    	yes.add("B");
    	yes.add("C");
    	yes.add("D");
    	yes.add("E");
    	yes.add("F");
    	
    	
    	ArrayList<String> yes1 = new ArrayList<String>();
    	yes1.add("B");
    	yes1.add("A");
    	yes1.add("E");
    	yes1.add("D");
    	yes1.add("F");
    	yes1.add("C");
    	
        BinaryTree<String> t = new BinaryTree<String>(yes,yes1);
        print(t,"Hello World");
        //print(t, "the empty tree");
        //BinaryTree<String> s = fillSampleTree1();
        //print(s, "sample tree 1");
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