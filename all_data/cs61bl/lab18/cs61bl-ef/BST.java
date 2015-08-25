import java.util.*;


public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    public static void main(String[] args) {
    	LinkedList<Integer> l = new LinkedList<Integer>();
    	l.add(1);
    	l.add(2);
    	l.add(3);
    	l.add(4);
    	l.add(5);
    	l.add(6);
    	l.add(7);
    	l.add(8);
    	l.add(9);
    	l.add(10);
    	l.add(11);
        l.add(12);
        l.add(13);
        l.add(14);
        l.add(15);
    	BST billy = new BST(l);
    	billy.printInorder();
    	billy.print();
    	
    }
    public void printInorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printInorder();
            System.out.println();
        }
    }
    
	public void print() {
		if(myRoot!=null){
			myRoot.print();
		}
	}
    // BSTNode 
    private static BSTNode linkedListToTree (Iterator iter, int n) {
    	if(!iter.hasNext()) {
        	return null;
        }
    	int count = 0;
    	BST b = new BST(new LinkedList());
    	LinkedList left = new LinkedList();
    	while(iter.hasNext() && count < n/2) {
    		count++;
    		left.add(iter.next());
    	}
        if(!iter.hasNext()) {
            return b.new BSTNode(left.pop(), null, null);
        }
    	BSTNode middle = b.new BSTNode(iter.next(), null, null);
    	Iterator leftSide = left.iterator();
    	middle.myLeft = linkedListToTree(leftSide, n/2);
    	Iterator rightSide = iter;
    	middle.myRight = linkedListToTree(rightSide, n/2);
    	return middle;
    }
   

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        String space = "    ";
        
        public BSTNode(Object item, BSTNode left, BSTNode right) {
        	myItem=item;
        	myLeft = left;
        	myRight = right;
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
        public void print(){
        	prettyPrint(0);
        }
        
        public void prettyPrint(int spaces) {
        	String pre = "";
        	for(int i = 0; i<spaces; i++) {
        		pre += space;
        	}
        	if(myRight!=null) {
        		myRight.prettyPrint(spaces+1);
        	}
        	System.out.println(pre + myItem);
        	if(myLeft!=null){
        		myLeft.prettyPrint(spaces + 1);
        	}
        }
    }
}
