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
    //construct a in preorder traversal; construct b in an inorder traversal;
    public BinaryTree(ArrayList<T> a, ArrayList<T> b){
    		try{
    			myRoot = subTreeHelper(a, b);
    		} catch(IllegalArgumentException e){
    			System.out.println(e.getMessage());
    		}
    }
    
    public  TreeNode subTreeHelper (ArrayList<T> a, ArrayList<T> b){
    		TreeNode t;
    		if(a == null || b == null){
    			throw new IllegalArgumentException("The ArrayList does not exist.");
    		}
    		if(a.size() != b.size()){
    			throw new IllegalArgumentException("The argument is not legal.");
    		}    		
    		if(a.isEmpty() || b.isEmpty()){
    			throw new IllegalArgumentException("The ArrayList is empty.");
    		}
    		if(!compareArrayHelper(a,b)){
    			throw new IllegalArgumentException("These 2 arraylist does not contain same elements.");
    		}
    		t = new TreeNode(a.get(0));
    		ArrayList<T> bLeft = new ArrayList<T>();
    		ArrayList<T> bRight = new ArrayList<T>();
    		ArrayList<T> aLeft = new ArrayList<T>();
    		ArrayList<T> aRight = new ArrayList<T>();
    		if(b.indexOf(a.get(0)) == 0 && a.size() > 1){
    			t.myLeft = null;
    			for(int i = 1; i < a.size(); i++){
    				aRight.add(a.get(i));
    				bRight.add(b.get(i));
    			}
    			t.myRight = subTreeHelper(aRight, bRight);
    		} else if(a.get(0) == b.get(b.size() - 1) && a.size() > 1){
    			t.myRight = null;
    			for(int i = 1; i < a.size(); i++){
    				aLeft.add(a.get(i));
    				bLeft.add(b.get(i-1));
    			}
    			t.myLeft = subTreeHelper(aLeft, bLeft);
    		} else if(a.size() == 1){
    			t.myLeft = null;
    			t.myRight = null;
    		} else {
    			for(int i = 0; i < b.indexOf(a.get(0)); i++){
        			bLeft.add(b.get(i));
        		}
    			for(int p = 1; p <= bLeft.size(); p++){
        			aLeft.add(a.get(p));        			
        		}
    			for(int j = b.indexOf(a.get(0))+1; j < b.size(); j++){
        			bRight.add(b.get(j));
        		}
    			for(int q = aLeft.size()+1; q < a.size();q++ ){
        			aRight.add(a.get(q));
        		}   			
    			t.myLeft = subTreeHelper(aLeft, bLeft);
    			t.myRight = subTreeHelper(aRight, bRight);
    		}   		
    		return t;   		
    }
    
    public boolean compareArrayHelper(ArrayList a, ArrayList b){
    		for(int i = 0; i < a.size(); i++){
    			if(!b.contains(a.get(i))){
    				return false;
    			}
    		}
    		return true;
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
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }
    
    

    public static void main(String[] args) {
 /*   		ArrayList<String> a = new ArrayList<String>();
    		a.add("A");
    		a.add("B");
    		a.add("D");
    		a.add("E");
    		a.add("C");
    		a.add("F");
    		a.add("G");
    		a.add("H");
    		//System.out.println(a.size());
    		ArrayList<String> b = new ArrayList<String>();
    		b.add("D");
    		b.add("B");
    		b.add("E");
    		b.add("A");
    		b.add("G");
    		b.add("F");
    		b.add("H");
    		b.add("C");   

        BinaryTree<String> t = new BinaryTree<String>(a,b);
        t.print(); */
        //print(t, "the empty tree");
        //BinaryTree<String> s = fillSampleTree1();
        //print(s, "sample tree 1");
        //BinaryTree<String> r = fillSampleTree2();
        //print(r, "sample tree 2");
        BinarySearchTree<String> tree = new BinarySearchTree<String>();
        tree.add("C");
        tree.add("A");
        tree.add("E");
        tree.add("D");
        tree.add("B");
        tree.add("A");
        tree.add("E");
        tree.print(); 
    		
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
        private static final String indent1 = "    ";
        private void print(int indent) {
            // TODO your code here
        	if(myRight != null){
        		myRight.print(indent + 1);
        	}
        		println (myItem, indent);
    		if(myLeft != null){
        		myLeft.print(indent + 1);
        	}
            // TODO your code here
        }

        private  void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
        
 /*       public  TreeNode subTree (ArrayList<T> a, ArrayList<T> b){
        	//if either arrayList is null, should return null.
        		if(a == null || b == null){
        			myItem = null;
        			myLeft = null;
        			myRight = null;
        		}
        	//Should we check whether these 2 arraylist have the same element? or we can assume
        // that they have the same arrayList element?        		
        		myItem = a.get(0);
        		//get myLeft subtree
        		if(b.get(0) == a.get(0)){
        			myLeft = null;
        		} else {
	        		ArrayList<T> bLeft = new ArrayList<T>();
	        		for(int i = 0; i < b.indexOf(a.get(0)); i++){
	        			bLeft.add(b.get(i));
	        		}
	        		
	        		ArrayList<T> aLeft = new ArrayList<T>();
	        		for(int p = 1; p <= a.indexOf(b.get((b.indexOf(a.get(0)) - 1))); p++){
	        			aLeft.add(a.get(p));
	        		}	        		
	        		myLeft = subTree(aLeft, bLeft);
        		}
        		if(b.get(a.size()-1) == a.get(0)){
        			myRight = null;
        		} else {
        			ArrayList<T> bRight = new ArrayList<T>();
	        		for(int j = b.indexOf(a.get(0)); j < b.size(); j++){
	        			bRight.add(b.get(j));
	        		}
	        		ArrayList<T> aRight = new ArrayList<T>();
	        		for(int q = a.indexOf(b.get((b.indexOf(a.get(0)) - 1))); q < a.size();q++ ){
	        			aRight.add(a.get(q));
	        		}
	        		
	        		myRight = subTree(aRight, bRight);
        			
        		}
        		return this;
        } */

		
    }
}