import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BinaryTree<T> implements Iterable<T> {

    protected TreeNode myRoot;
    
    public BinaryTree(List<T> preorder, List<T> inorder) {
    	myRoot = constructorHelper(preorder, inorder);
    }
    
    public TreeNode constructorHelper(List<T> preorder, List<T> inorder) {
    	 if (preorder.size() != inorder.size()) {
    		 throw new IllegalArgumentException("ArrayLists must be the same size"); 
    	 }
    	 int size = preorder.size(); 
    	 if (preorder.isEmpty()) {
    		 return null;
    	 }
    	 if (size == 1) {
    		 return new TreeNode(preorder.get(0));
    	 }
    	 T root = preorder.get(0); 
    	 int index = inorder.indexOf(root); 
    	 if (index == 0) {
    		 TreeNode right = constructorHelper( preorder.subList(1, size), inorder.subList(1, size));
    		 return new TreeNode(root, null, right); 
    	 }
    	 if (index == size - 1) {
    		 TreeNode left = constructorHelper(preorder.subList(1, size),  inorder.subList(0, size-1));
    		 return new TreeNode(root, left, null); 
    	 } else {
    		 List<T> inorderLeft = inorder.subList(0, index);
    		 List<T> inorderRight = inorder.subList(index+1, size);
    		 int rightRootIndex = 0;  
    		 for (int i = 2; i < size; i++) {
    			 if (!inorderLeft.contains(preorder.get(i))) {
    				 rightRootIndex = i; 
    				 break;
    			 }
    		 }
    		 TreeNode left = constructorHelper(preorder.subList(1, rightRootIndex), inorderLeft);
    		 TreeNode right = constructorHelper(preorder.subList(rightRootIndex, size), inorderRight); 
    		 return new TreeNode(root, left, right); 
    		 
    	 }
    }

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

    public static void main(String[] args) {
        BinaryTree<String> t = new BinaryTree<String>();
        print(t, "the empty tree");
        BinaryTree<String> s = fillSampleTree1();
        print(s, "sample tree 1");
        BinaryTree<String> r = fillSampleTree2();
        print(r, "sample tree 2");
        
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("A"); 
        list1.add("B");
        list1.add("G");
        list1.add("H");
        list1.add("C");
        list1.add("D");
        list1.add("E");
        list1.add("F");
        
        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("G");
        list2.add("B");
        list2.add("H");
        list2.add("A");
        list2.add("E");
        list2.add("D");
        list2.add("F");
        list2.add("C"); 
        
        BinaryTree<String> test = new BinaryTree<String>(list1, list2);
        test.print(); 
        
        ArrayList<String> list3 = new ArrayList<String>();
        list3.add("A"); 
        list3.add("B");
        list3.add("C");
        list3.add("D");
        list3.add("E");
        list3.add("F");
        list3.add("G");
        list3.add("H");
        
        ArrayList<String> list4 = new ArrayList<String>();
        list4.add("A"); 
        list4.add("B");
        list4.add("C");
        list4.add("D");
        list4.add("E");
        list4.add("F");
        list4.add("G");
        list4.add("H");
        
        BinaryTree<String> test2 = new BinaryTree<String>(list3, list4);
        test2.print();
    }

    protected static void print(BinaryTree<?> t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
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
        private static final String indent1 = "    ";	

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
        
        private void print(int indent) {
        	if (myRight != null) {
        		myRight.print(indent + 1);
        	}
            println(myItem, indent);
            if (myLeft != null) {
            	myLeft.print(indent+1);
            }
        }
        
        private void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
    }
}