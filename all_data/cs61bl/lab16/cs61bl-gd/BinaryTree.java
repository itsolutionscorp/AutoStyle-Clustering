import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BinaryTree<T> implements Iterable<T> {

    protected TreeNode myRoot;
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }else{
        System.out.println("It is a empty tree");}
    }
  
   
    public BinaryTree() {
        myRoot = null;
    }
    public BinaryTree(List<T> preorder,List<T> inorder){
    	if(preorder.size()!=inorder.size()){
    		throw new IllegalStateException();
    	}
    	if(preorder.isEmpty())
    	{myRoot = null; return;}  
    	if(preorder.size()==1&&inorder.size()==1&&preorder.get(0).equals(inorder.get(0)))
    	{myRoot = new BinaryTree(new TreeNode(preorder.get(0))).myRoot;
    	return;}
    	T head= preorder.get(0);
    	if(!inorder.contains(head)){
    		throw new IllegalStateException("Mismatch lists");
    	}
    	int leftindex=inorder.indexOf(head);
    	System.out.println(leftindex);
    	
    	List LeListPre=new ArrayList();
    	List LeListInO = new ArrayList();
    	if(leftindex>0){
    	 LeListPre= preorder.subList(1, 1+leftindex);
    	LeListInO=inorder.subList(0,leftindex);}
    	System.out.println(LeListPre);
    	System.out.println(LeListInO);
    	
    	
    	List RiListPre = new ArrayList();
		List RiListInO=new ArrayList();
    	if(leftindex+1<inorder.size()){
    		RiListPre= preorder.subList(leftindex+1, preorder.size());
    		RiListInO= inorder.subList(leftindex+1, inorder.size());
    		
    	}
    	System.out.println(RiListPre);
    	System.out.println(RiListInO);
    	
    	
    	 
    	TreeNode left=new  BinaryTree(LeListPre,LeListInO).myRoot;
    	TreeNode right =new  BinaryTree(RiListPre,RiListInO).myRoot;
    	myRoot=new TreeNode(head,left,right);
    	
    	
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
        //extra print
        private void print(int indent) {
            // TODO your code here
        	if(myRight!=null){
        	myRight.print(indent+1);}
            println (myItem, indent);
            // TODO your code here
            if(myLeft!=null){
            myLeft.print(indent+1);}
        }
        private static final String indent1 = "    ";
        
        private void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
     
    }
}