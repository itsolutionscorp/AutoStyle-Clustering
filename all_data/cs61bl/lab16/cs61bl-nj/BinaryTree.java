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
    public BinaryTree(ArrayList<T> preorder, ArrayList<T> inorder) {
    	myRoot = constructorHelper(preorder, inorder, 0, preorder.size(), 0, inorder.size());
    }
    
    private TreeNode constructorHelper(ArrayList<T> preorder, ArrayList<T> inorder, int preStart, int preEnd, int inStart, int inEnd) {
    	if (preStart==preEnd) {
    		return null;
    	}
    	if (preStart==preEnd-1) {
    		return new TreeNode(preorder.get(preStart));
    	}
    	TreeNode result = new TreeNode(preorder.get(preStart));
    	for (int i=inStart; i<inEnd; i++) {
    		if (inorder.get(i).equals(preorder.get(preStart))) {
    			if (i==0) {
    				result.myLeft = null;
    				result.myRight = constructorHelper(preorder, inorder, preStart+1, preEnd, inStart+1, inEnd);
    				return result;
    			}
    			if (i==inEnd-1) {
    				result.myRight = null;
    				result.myLeft = constructorHelper(preorder, inorder, preStart+1,preEnd,inStart,i);
    				return result;
    			}
    			result.myLeft = constructorHelper(preorder,inorder,preStart+1,preStart+i-inStart+1,inStart,i);
    			result.myRight = constructorHelper(preorder, inorder, preStart+i-inStart+1, preEnd,i+1,inEnd);
    			return result;
    			}
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
    	ArrayList<String> preorder = new ArrayList<String>();
    	preorder.add("A");
    	preorder.add("B");
    	preorder.add("C");
    	preorder.add("D");
    	preorder.add("E");
    	preorder.add("F");
    	ArrayList<String> inorder = new ArrayList<String>();
    	inorder.add("B");
    	inorder.add("A");
    	inorder.add("E");
    	inorder.add("D");
    	inorder.add("F");
    	inorder.add("C");
    	BinaryTree<String> strange = new BinaryTree<String>(preorder, inorder);
    	print(strange, "the arraylist tree");
    	ArrayList<String> preorder2 = new ArrayList<String>();
    	preorder2.add("A");
    	preorder2.add("B");
    	preorder2.add("C");
    	preorder2.add("D");
    	preorder2.add("F");
    	ArrayList<String> inorder2 = new ArrayList<String>();
    	inorder2.add("A");
    	inorder2.add("C");
    	inorder2.add("B");
    	inorder2.add("F");
    	inorder2.add("D");
    	BinaryTree<String> strange2 = new BinaryTree<String>(preorder2, inorder2);
    	print(strange2, "the arraylist tree2");
    	ArrayList<String> preorder3 = new ArrayList<String>();
    	preorder3.add("A");
    	preorder3.add("B");
    	preorder3.add("C");
    	preorder3.add("D");
    	preorder3.add("E");
    	ArrayList<String> inorder3 = new ArrayList<String>();
    	inorder3.add("C");
    	inorder3.add("B");
    	inorder3.add("E");
    	inorder3.add("D");
    	inorder3.add("A");
    	BinaryTree<String> strange3 = new BinaryTree<String>(preorder3, inorder3);
    	print(strange3, "the arraylist tree3");
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
    }
}