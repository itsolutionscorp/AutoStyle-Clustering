import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;



public class BinaryTree<T> implements Iterable<T> {

    protected TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public BinaryTree(ArrayList<T>preorder, ArrayList<T>inorder){ //a1 is preorder traversal, a2 is inorder traversal
    	//DOES NOT HAVE TO BE A BINARY SEARCH TREE
    	//T temp = preorder.get(0); //Trying to find index of root in the inorder traversal
    	//preorder.remove(0);
    	/*int index_of_interest = 0;
    	int temp_index = 0;
    	while( index_of_interest == 0 ){
    		if(inorder.get(temp_index).equals(temp)) //If equal, you know you have found the index
    			index_of_interest = temp_index;
    		temp_index++;
    	}List<T> leftTree = inorder.subList(0, index_of_interest);
    	List<T> rightTree = inorder.subList(index_of_interest+1,  inorder.size());
    	List<T> updated_preorder = preorder.subList(1, preorder.size());*/
    	myRoot = constructorHelper(inorder,preorder);//, constructorHelper(rightTree, preorder)); //Set the root to the first element in the preorder traversal
    	
    }
    
    public TreeNode constructorHelper(List<T> subtree, List<T> preorder){ //needs access to both arrays SOMEHOW
    	if(subtree.size() == 1 && preorder.size() > 0){
    		preorder.remove(0);
    		return new TreeNode(subtree.get(0), null, null);
    	}if (subtree.size() > 1 && preorder.size() > 0) {
    		T imp_temp = preorder.get(0);
    		preorder.remove(0);
    		int index_of_interest = -1;
    		int index = 0;
    		while(index_of_interest == -1 && index < subtree.size()){
    			if(subtree.get(index).equals(imp_temp))
    				index_of_interest = index;
    			index++;
    		}List<T> tree = subtree.subList(0,index_of_interest);
    		List<T> tree2 = subtree.subList(index_of_interest+1, subtree.size());
    		return new TreeNode(imp_temp, constructorHelper(tree,preorder ), constructorHelper(tree2, preorder)); 
    	}
    	return null;
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
        
        ArrayList<String> mystrings = new ArrayList<String>();
        mystrings.add("A"); //preorder
        mystrings.add("B");
        mystrings.add("C");
        mystrings.add("D");
        mystrings.add("E");
        mystrings.add("F");
        
        ArrayList<String> mystrings2 = new ArrayList<String>();
        mystrings2.add("B"); //inorder
        mystrings2.add("A");
        mystrings2.add("E");
        mystrings2.add("D");
        mystrings2.add("F");
        mystrings2.add("C");
        
        ArrayList<String> mystrings3 = new ArrayList<String>(); //preorder of another example
        mystrings3.add("A");
        mystrings3.add("B");
        mystrings3.add("C");
        mystrings3.add("D");
        mystrings3.add("E");
       
        ArrayList<String> mystrings4 = new ArrayList<String>(); //inorder
        mystrings4.add("B");
        mystrings4.add("A");
        mystrings4.add("D");
        mystrings4.add("C");
        mystrings4.add("E");
        
        
        BinaryTree<String> myTree = new BinaryTree<String>(mystrings, mystrings2);
        
        print(myTree, "hard tree");
        
        BinaryTree<String> myTree2 = new BinaryTree<String>(mystrings3, mystrings4);
        
        print(myTree2, "hard tree 2");
    }

    

    public void print() {
    	if (myRoot != null) {
    		myRoot.print(0);
    	}
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
    
    public int size(){
    	if(myRoot != null)
    		return myRoot.size();
    	return 0;
    }

    public class TreeNode {

        public T myItem;
        public TreeNode myLeft;
        public TreeNode myRight;
        private int myDepth, mySize;

        public TreeNode(T item) {
            myItem = item;
            myLeft = myRight = null;
            myDepth = calculateDepth();
            mySize = size();
            
        }

        public TreeNode(T item, TreeNode left, TreeNode right) {
            myItem = item;
            myLeft = left;
            myRight = right;
            myDepth = calculateDepth();
            mySize = size();
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
        
        public int calculateDepth(){
        	if(myLeft == null && myRight != null)
        		return 1 + myRight.calculateDepth();
        	else if(myRight == null && myLeft != null)
        		return 1 + myLeft.calculateDepth();
        	else if (myRight != null && myLeft != null)
        		return 1 + Math.max(myLeft.calculateDepth(), myRight.calculateDepth());
        	return 1;
        	
        	
        }
        
        public boolean is_leaf(){
        	if(myRight == null && myLeft == null)
        		return true;
        	return false;
        }
        
        public int size(){
        	if(myLeft != null && myRight != null)
        		return 1 + myLeft.size() + myRight.size();
        	else if(myLeft != null && myRight == null)
        		return 1 + myLeft.size();
        	else if(myRight != null && myLeft == null)
        		return 1 + myRight.size();
        	return 1;
        }
        
        public Iterator iterator(){
            return new InorderIterator();
        }
        
        private void print(int indent) {
            // TODO your code here
            if(myRight != null){
                myRight.print(indent + 1);
            }println (myItem, indent);
            if(myLeft != null){
                myLeft.print(indent + 1);
            }
             
             
            // TODO your code here
        }
        
        private static final String indent1 = "    ";
        
        private void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }

        // Inner class inside of the BinaryTree class.
        // Also, it uses java.util.Iterator and java.util.Stack.
        public class InorderIterator implements Iterator<T> {
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

    }
}
