import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree<T extends Comparable<T>> implements Iterable<T> {

    protected TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public BinaryTree(List<T> preorder, List<T> inorder) {
    	myRoot = subtree(preorder, inorder);
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
//        List<String> preorder = new ArrayList<String>(6);
////		preorder.add("A");
////		preorder.add("B");
////		preorder.add("C");
////		preorder.add("D");
////		preorder.add("E");
////		preorder.add("F");
//		List<String> inorder = new ArrayList<String>(6);
////		inorder.add("B");
////		inorder.add("A");
////		inorder.add("E");
////		inorder.add("D");
////		inorder.add("F");
////		inorder.add("C");
//		BinaryTree<String> tree = new BinaryTree<String>(preorder, inorder);
//		BinaryTree.print(tree, "sub");
        
    }

    protected static void print(BinaryTree<?> t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }
    
    public TreeNode subtree(List<T> preorder, List<T> inorder) {
    	if (preorder.size() == 0) return null;
    	if (preorder.size() == 1) return new TreeNode(preorder.get(0));
    	TreeNode result= new TreeNode(preorder.get(0));
    	result.myLeft = subtree(preorder.subList(1, preorder.indexOf(inorder.get(inorder.indexOf(result.myItem)-1))+1), 
    			inorder.subList(0, inorder.indexOf(result.myItem)));
    	result.myRight = subtree(preorder.subList(preorder.indexOf(inorder.get(inorder.indexOf(result.myItem)-1))+1, 
    			preorder.size()), 
    			inorder.subList(inorder.indexOf(result.myItem)+1, inorder.size()));
    	return result;
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
        public int mySize;

        public TreeNode(T item) {
            myItem = item;
            myLeft = myRight = null;
            mySize = 1;
        }

        public TreeNode(T item, TreeNode left, TreeNode right) {
            myItem = item;
            myLeft = left;
            myRight = right;
            mySize = 1 + myLeft.mySize + myRight.mySize;
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
        public boolean contains(TreeNode t, T key){
        	if (t == null){
        		return false;
        	}
        	if (t.myItem.equals(key)){
        		return true;
        	}
        	if (key.compareTo(t.myItem)<0){
        		return contains(t.myLeft,key);
        	} else {
        		return contains(t.myRight,key);
        	}
        }
        public TreeNode add(TreeNode t, T key) {
            if (t == null) {
                return new TreeNode(key);
            } else if (key.compareTo(t.myItem) < 0) {
            	t.mySize++;
                t.myLeft = add(t.myLeft, key);
                return t;
            } else {
            	t.mySize++;
                t.myRight = add(t.myRight, key);
                return t;
            }
        }
        
       
    }
}