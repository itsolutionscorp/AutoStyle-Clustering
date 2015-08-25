import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
    	if(list!=null){
    		myRoot = linkedListToTree(list.iterator(), list.size());
    	}
    }

    // Your comment here
    // Operates on an ordered linked list by splitting the list into sublists until a list size
    // of 1 is reached, and then the tree is constructed. This allows for construction of the tree in log(n) time. 
    private static BSTNode linkedListToTree (Iterator iter, int n) {
    	ArrayList contents = new ArrayList();
        while(iter.hasNext()){
        	contents.add(iter.next());
        }
//        Object root = contents.get(contents.size()/2);
//		int rootIndex = contents.indexOf(root);
//		ArrayList left = new ArrayList(contents.subList(0, rootIndex - 1));
//		ArrayList right = new ArrayList(contents.subList(rootIndex + 1, contents.size()-1));
        BST tree = new BST(null);
        BST.BSTNode groot = null;
//        BSTNode groot = tree.new BSTNode(root,toTreeHelper(left,groot.myLeft),toTreeHelper(right,groot)); 
        //root = toTreeHelper(contents,root);
        return toTreeHelper(contents, groot);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private static BSTNode toTreeHelper(ArrayList contents, BSTNode t){
    	BST tree = new BST(null);
    	if (contents==null)
    		return null;
    	if (contents.size() == 0) {
    		return null;
    	}
    	if (contents.size()==1){
    		t = tree.new BSTNode(contents.get(0), null, null);
    		return t;
    	}
    	else{
    		Object root = contents.get((contents.size()-1) / 2);
    		int rootIndex = contents.indexOf(root);
    		ArrayList left = new ArrayList(contents.subList(0, rootIndex));
    		ArrayList right = new ArrayList(contents.subList(rootIndex + 1, contents.size()));
    		t = tree.new BSTNode(root, null, null);
    		t.myLeft = toTreeHelper(left, t.myLeft);
    		t.myRight = toTreeHelper(right, t.myRight);
    	}
    	return t;
    }

    public void printInorder(){
    	if(myRoot == null){
    		System.out.println("(empty tree)");
    	}
    	else{
    		myRoot.printInorder();
    		System.out.println();
    	}
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        public BSTNode(Object myItem, BSTNode myLeft, BSTNode myRight){
        	this.myItem = myItem;
        	this.myLeft = myLeft;
        	this.myRight = myRight;
        }
        
        private void printInorder(){
        	if(myLeft != null){
        		myLeft.printInorder();
        	}
        	System.out.print(myItem + " ");
        	if(myRight != null) {
        		myRight.printInorder();
        	}
        }
    }
    
    
    
    public static void main(String args[]) {
    	LinkedList l = new LinkedList();
    	l.add("For Doomhammer!");
    	l.add("I must protect the wild!");
    	l.add("I will fight with honor.");
    	l.add("Let the hunt begin!");
    	l.add("The Light shall bring victory!");
    	l.add("Victory or death!");
    	l.add("Watch your back...");
    	l.add("You asked for it.");
    	l.add("Your soul shall be mine!");
    	BST yggdrasil = new BST(l);
    	yggdrasil.printInorder();
    }
}
