import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }
    

    // Your comment here
    //It can not result in a max balanced tree but can get a fairly balanced tree
    private static BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	
    	int splitpoint = Math.floorDiv(n, 2);
    	LinkedList left = new LinkedList();
    	LinkedList right = new LinkedList();
    	Object item = null;
    	BSTNode result= new BSTNode();
    	if(!iter.hasNext()){
    		return null;
    	}
       for(int i=0;i<n;i++){
    	   Object next = iter.next();
    	   if(i<splitpoint){
    		   left.add(next);
    	   }
    	   if(i==splitpoint){
    		   item= next;
    	   }
    	   if(i>splitpoint&&i<n){
    		   right.add(next);
    	   }
    	   
       }
       result.myItem=item;
       result.myLeft=(linkedListToTree(left.iterator(),left.size()));
       
       result.myRight=(linkedListToTree(right.iterator(),right.size()));
       
       return result;
       
    }
  

    static class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
}
