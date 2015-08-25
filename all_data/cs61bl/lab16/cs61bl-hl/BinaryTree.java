import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class BinaryTree<T> implements Iterable<T> {

    protected TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public BinaryTree(ArrayList<T> preorder,ArrayList<T> inorder){
    	myRoot = preorderInorderHelper(preorder, inorder);
    }
    
    public TreeNode preorderInorderHelper(List<T> preorder,List<T> inorder){
    	TreeNode n = new TreeNode(null);
    	if (preorder.size() == 0) {
    		return null;
    	}
    	if (preorder.size() == 1) {
    		n.myItem = preorder.get(0);
    		return n;        	
    	}
    	T headItem = preorder.get(0);
    	n.myItem = headItem;
    	int headIndex = inorder.indexOf(headItem);
    	List<T> left = inorder.subList(0,headIndex);
    	List<T> right;
    	if ((headIndex + 1) < inorder.size())
    		right = inorder.subList(headIndex+1,inorder.size());
    	else
    		right = new ArrayList<T>();
    	int leftHeadPosition = -1;
    	int rightHeadPosition = preorder.size();
    	for (int i = 1; i < preorder.size(); i++) {
    		if(left.contains(preorder.get(i))) {
    			leftHeadPosition = i;
    			break;
    		}
    	}
    	for (int i = leftHeadPosition + 1; i < preorder.size(); i++) {
    		if(right.contains(preorder.get(i))) {
    			rightHeadPosition = i;
    			break;
    		}
    	}
    	if (leftHeadPosition == -1)
    		leftHeadPosition = rightHeadPosition;
    	n.myLeft =  preorderInorderHelper(preorder.subList(leftHeadPosition, rightHeadPosition), left);
    	n.myRight = preorderInorderHelper(preorder.subList(rightHeadPosition, preorder.size()), right);
    	return n;
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
        ArrayList<String> one = new ArrayList<String>();
        one.add("A");
        one.add("B");
        one.add("C");
//        one.add("D");
//        one.add("E");
//        one.add("F");
        ArrayList<String> two = new ArrayList<String>();
        two.add("B");
        two.add("A");
//        two.add("E");
//        two.add("D");
//        two.add("F");
        two.add("C");
        BinaryTree<String> t1 = new BinaryTree<String>(one, two);
        print(t1, "sample tree 3");
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
        private void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent);
            }
            System.out.println(obj);
        }
    }
}