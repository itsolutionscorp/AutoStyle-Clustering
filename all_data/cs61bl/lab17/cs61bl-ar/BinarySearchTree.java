


public class BinarySearchTree <T extends Comparable<T>>extends BinaryTree<T>{
	public BinarySearchTree(){
		super();
	}
	
	public boolean contains(T key){
		BinarySearchTree<T> myTree = new BinarySearchTree<T>();
		if(myRoot == null){
			return false;
		}else{
			if(myRoot.myItem.compareTo(key)==0){
				return true;
			}else if(myRoot.myItem.compareTo(key)>0){
				myTree.myRoot = myRoot.myLeft;
				return myTree.contains(key);
			}else{
				myTree.myRoot = myRoot.myRight;
				return myTree.contains(key);
			}
		}
	}
	public T find(T toFind){
		BinarySearchTree<T> myTree = new BinarySearchTree<T>();
			if(myRoot.myItem.compareTo(toFind)==0){			
				return myRoot.myItem;
			}else if(myRoot.myItem.compareTo(toFind)>0){
				myTree.myRoot = myRoot.myLeft;
				return myTree.find(toFind);
			}else{
				myTree.myRoot = myRoot.myRight;
                return myTree.find(toFind);
			}
		
	}
    public T remove(T key){
    	if(delete(key))
    	{
    		return key;
    	}
    	return null;
    }
	   public void removes(TreeNode root, T item){
	        if(root==null){
	            return;
	        }
	        if(item==root.myItem){
	            if(root.myLeft==null){
	                root = root.myRight;
	            }
	            else if(root.myRight==null){
	                root = root.myLeft;
	            }
	            else{
	            }
	        }
	        else if(item.compareTo(root.myItem)<0){
	            removes(root.myLeft, item);
	        }
	        else{
	            removes(root.myRight, item);
	        }
	    }
    public boolean delete(T Key) { 
    	
        TreeNode current = myRoot;  
        TreeNode parent = myRoot;  
        boolean ismyLeft = true;  
        while (current.myItem.compareTo(Key) !=0){  
            parent = current;  
            if (Key.compareTo(current.myItem)<0) {  
                ismyLeft = true;  
                current = current.myLeft;  
            } else {  
                ismyLeft = false;  
                current = current.myRight;  
            }  
        }  
        if (current.myLeft == null && current.myRight == null) {  
            if (current == myRoot) { 
                myRoot = null;  
            } else if (ismyLeft) {
                parent.myLeft = null;  
            } else {
                parent.myRight = null;  
            }  
        } else if (current.myLeft == null) {                         
            if (current == myRoot) {   
                myRoot = current.myRight;  
            } else if (ismyLeft) {  
                parent.myLeft = current.myRight;  
            } else {  
                parent.myRight = current.myRight;  
            }  
        } else if (current.myRight == null) {                         
            if (current == myRoot) {   
                myRoot = current.myLeft;  
            } else if (ismyLeft) {  
                parent.myLeft = current.myLeft;  
            } else {  
                parent.myRight = current.myLeft;  
            }  
        } else {                                                         
            TreeNode successor = findSuccessor(current);  
            if(current == myRoot){  
                myRoot = successor;  
            }else if(ismyLeft){  
                parent.myLeft = successor;  
            }else{  
                parent.myRight = successor;  
            }  
            successor.myLeft = current.myLeft;  
        }  
        return true;  
    }  
    private TreeNode findSuccessor(TreeNode delNode){  
        TreeNode parent = delNode;  
        TreeNode successor = delNode;  
        TreeNode current = delNode.myRight;  
        while(current != null){  
            parent = successor;  
            successor = current;  
            current = current.myLeft;  
        }  
          
        if(successor != delNode.myRight){  
            parent.myLeft = successor.myRight;  
            successor.myRight = delNode.myRight;  
        }  
        return successor;  
    }  
	public void add(T key) {
		if(!this.contains(key)){
			myRoot = add(myRoot, key);
		}
	}
	public void REfind(T toFind,T toRE){
		BinarySearchTree<T> myTree = new BinarySearchTree<T>();
		if(myRoot.myItem.compareTo(toFind)>0){
			myTree.myRoot = myRoot.myLeft;
			myTree.REfind(toFind,toRE); 
		}else if(myRoot.myItem.compareTo(toFind)==0){
			myRoot.myItem = toRE;
		}else{
			myTree.myRoot = myRoot.myRight;
			myTree.REfind(toFind,toRE);
		}
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        t.size++;
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        t.size++;
	        return t;
	    }
	}
	
	private Comparable kthLargestHelper (TreeNode t, int k){
		int rightCount;
		if(t.myRight!=null){
			rightCount = t.myRight.size;
			if(rightCount+1 == k){
				return t.myItem;
			}else if(rightCount+1<k){
				return kthLargestHelper(t.myLeft, k-(rightCount+2));
			}else{
				return kthLargestHelper(t.myRight, k);
			}
		}else{
			rightCount = 0; 
			if(rightCount == k){
				return t.myItem;
			}else if(rightCount<k){
				return kthLargestHelper(t.myLeft, k-1);
			}
		}
		
		return null;
	}
		
		
	Comparable kthLargest (int k){
		if(myRoot!= null){
			return kthLargestHelper(myRoot,k);
		}
		return null;
	}
	public static void main(String[]args){
		BinarySearchTree<String> t = new BinarySearchTree<String>();
        print(t, "the empty tree");
        t.add("C");
        print(t, "C");
        t.add("A");
        t.add("B");
        t.add("E");
        t.add("D");
        

   
        print(t,"aaa");
	}
}
 