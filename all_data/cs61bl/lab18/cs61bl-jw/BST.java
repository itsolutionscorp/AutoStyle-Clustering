import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    /* passed in an iterator as argument, 
     * 
     * 
     */
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	int leftlenght,rightlength;
    	double LenOfleft = n/2;
    	leftlenght = (int)java.lang.Math.floor(LenOfleft);
    	rightlength = n - leftlenght -1;
    	
    	//linkedListToTree(iter,leftlenght);
    	//Object item = iter.next();
    	//linkedListToTree(iter,rightlength);
    	
        return new BSTNode(linkedListToTree(iter,leftlenght),iter.next(),linkedListToTree(iter,rightlength)) ;
    }

    


	private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        public BSTNode( BSTNode myLeft,Object myItem,BSTNode myRight){
        	this.myItem =  myItem;
        	this.myLeft =  myLeft;
        	this.myRight =  myRight;
        }
    }
    
    
}
