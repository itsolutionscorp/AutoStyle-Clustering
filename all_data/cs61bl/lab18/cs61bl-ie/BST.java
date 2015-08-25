import java.util.*;

public class BST {
	BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	// takes in a iterator over a linkedList and creates a balanced BST
	private BSTNode linkedListToTree (Iterator iter, int n) {
		if(n != 0){
			BSTNode b = new BSTNode();
			b.myLeft = linkedListToTree(iter, (n-1)/2);
			b.myItem = iter.next();
			b.myRight = linkedListToTree(iter,n-1-((n-1)/2));
			return b;
		} else{
			return null;
		}
	}
	
	public void print(BSTNode b){
		if(b == null){
			return;
		}else{
			print(b.myLeft);
			System.out.println(b.myItem);
			print(b.myRight);
		}
	}

	private class BSTNode {
		Object myItem;
		BSTNode myLeft;
		BSTNode myRight;
		
		
	}	
}
