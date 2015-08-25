import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    /**
     * this method ,given a linkedlist iterator and the size of the list, 
     * creates a "balanced" tree by taking the center of the list and 
     * making it the node and calling this method recursively on the left and
     * right side of the list with half of the list on each side...
     * @param iter
     *        is a linkedlist iterator  
     * @param n
     *          the size of the linked list
     * @return
     *        the Root of the linked list....
     */
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	 int n1;
    	 int n2;
    	if (n%2==0){
    		 n1= Math.round(n/2); 
    		 n2= n1-1;
    	}else{
    		n1= n/2; 
   		     n2= n1;
    	}
              BSTNode node= new BSTNode();
             if (n==1){
              node.myItem= iter.next();
              node.myLeft=null ;
              node.myRight=null ;
              return node;
             
              }else if (n>1){
            	  node.myLeft=linkedListToTree (iter,n1);
            	  node.myItem= iter.next();
                  node.myRight=linkedListToTree (iter,n2) ;
                  return node;
              }else{
            	  return null;
              }
     }
    public void printInorder() {
		if (myRoot == null) {
			System.out.println("(empty tree)");
		} else {
			myRoot.printInorder();
			System.out.println();
		}
	}
    
    public static void main(String[] arg){
    	LinkedList<Integer> L= new LinkedList<Integer>();
    	for(int i=1; i<8; i++){
    		L.add(i);
    	}
    	BST B= new BST(L);
    	B.printInorder();	
    }
    private static class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
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
