import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.ArrayList;

public class BinaryTree<T> implements Iterable<T> {

    protected TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    public BinaryTree(ArrayList<T> a, ArrayList<T> b){
    	if (a.isEmpty()){
    		myRoot = null;
    	}else{
    		if (a.size() == 1){
    			myRoot= new TreeNode(a.get(0));
    		}else{
    			if (b.get(0).equals(a.get(0))){
    				myRoot = new TreeNode(a.get(0));
    				myRoot.myLeft = null;
    				List<T> c = a.subList(1, a.size());
    				List<T> d = b.subList(1, b.size());
    				ArrayList<T> h = new ArrayList<T>(c);
    				ArrayList<T> i = new ArrayList<T>(d);
    				myRoot.myRight = (new BinaryTree(h, i)).myRoot;
    			}else if(b.get(b.size() - 1) == a.get(0)){
    				myRoot = new TreeNode(a.get(0));
    				myRoot.myRight = null;
    				List<T> c = a.subList(1, a.size());
    				List<T> d = b.subList(0, b.size() - 1);
    				ArrayList<T> h = new ArrayList<T>(c);
    				ArrayList<T> i = new ArrayList<T>(d);
    				myRoot.myLeft = (new BinaryTree(h,i)).myRoot;
    			}else{
    				myRoot = new TreeNode(a.get(0));
    				int index1 = b.indexOf(a.get(0));
    				int index2 = a.indexOf(b.get(b.size()-1));
    				List<T> c = a.subList(index2, a.size());
    				List<T> d = b.subList(index1, b.size());
    				List<T> e = a.subList(1, index2);
    				List<T> f = b.subList(0, index1);
    				ArrayList<T> h = new ArrayList<T>(c);
    				ArrayList<T> i = new ArrayList<T>(d);
    				ArrayList<T> j = new ArrayList<T>(e);
    				ArrayList<T> k = new ArrayList<T>(f);
    				myRoot.myRight = (new BinaryTree(h, i)).myRoot;
    				myRoot.myLeft = (new BinaryTree(j, k)).myRoot;

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
        //BinaryTree<String> t = new BinaryTree<String>();
        //print(t, "the empty tree");
        //BinaryTree<String> s = fillSampleTree1();
        //print(s, "sample tree 1");
        //BinaryTree<String> r = fillSampleTree2();
        //print(r, "sample tree 2");
        ArrayList<String> a = new ArrayList<String>();
        a.add("A");
        a.add("B");
        a.add("C");
        a.add("D");
        a.add("E");
        a.add("F");
        ArrayList<String> b = new ArrayList<String>();
        b.add("B");
        b.add("A");
        b.add("E");
        b.add("D");
        b.add("F");
        b.add("C");
        BinaryTree<String> z = new BinaryTree(a, b);
        print(z, "testing");
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
 // This code gets put inside the BinaryTree class.



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