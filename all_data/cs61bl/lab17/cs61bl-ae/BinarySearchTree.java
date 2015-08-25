

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{
    public BinarySearchTree(){
    	super();
    }
    public BinarySearchTree(TreeNode t){
    	super(t);
    }
	
	public boolean contains(T key) {
    	return containsHelper(myRoot, key);
    }
    private boolean containsHelper(TreeNode t, T key){
    	if(t==null) {
    		return false;
    	}
    	if(key.compareTo(t.myItem)==0){
    		return true;
    	} else if(key.compareTo(t.myItem)<0){
    		return containsHelper(t.myLeft, key);
    	} else {
    		return containsHelper(t.myRight, key);
    	}
    }
    
    public void add(T key) {
    	if(myRoot==null){
    		myRoot = new TreeNode(key);
    	}else
        myRoot = add(myRoot, key);
    }
    public T find(T item){
    	if(myRoot!=null){
    		return findHelper(myRoot, item);
    	}return null;
    }
    private T findHelper(TreeNode t, T item){
    	if(t==null){
    		return null;
    	}else if (t.myItem.compareTo(item)==0){
    		return t.myItem;
    	}else if(t.myItem.compareTo(item)>0){
    		return findHelper(t.myLeft, item);
    	}else return findHelper(t.myRight, item);
    }
    private T successor(TreeNode t){
    	if(t.myRight!=null){
    		return kthLargestHelper(t.myRight, 1);
    	}return null;
    }
    public boolean isLeaf(TreeNode t){
    	if(t==null){
    		return false;
    	}else return t.myLeft==null&&t.myRight==null;
    }
    
    private TreeNode removeHelper(TreeNode t, T item){
    	if(t==null){
    		return null;
    	}if(containsHelper(t, item)){
    		t.treeSize--;
    	
        if(t.myItem.compareTo(item)==0){
        	if(isLeaf(t)){
        		return null;
        	}
    		if(t.myRight!=null){
    			t.myItem = successor(t);
    			t.myRight = removeHelper(t.myRight, successor(t));
    			return t;
    		} else {
    			return t.myLeft;
    		}
    	}else if(t.myItem.compareTo(item)>0){
    		t.myLeft=removeHelper(t.myLeft, item);
    		return t;
    	}else {
    		t.myRight=removeHelper(t.myRight, item);
    		return t;
    	}}return t;
    }
    
    public BinarySearchTree remove(T item){
    	return new BinarySearchTree<T>(removeHelper(myRoot, item));
    }
    
    public T kthLargest(int k){
    	if(myRoot!=null){
    		return kthLargestHelper(myRoot, k);
    	}
    	return null;
    }
    private T kthLargestHelper(TreeNode t, int k){
    	int leftSize;
    
    	if(t.myLeft==null) leftSize=0;
    	else leftSize = t.myLeft.treeSize;
    	
    	if(leftSize >=k){
    		return kthLargestHelper(t.myLeft, k);
    	}else if (leftSize+1==k){
    		return t.myItem;
    	}else {
    		return kthLargestHelper(t.myRight, k-leftSize-1);
    	}
    }

    private TreeNode add(TreeNode t, T key) {
        if (t == null) {
            return new TreeNode(key);
        } else if (key.compareTo(t.myItem) < 0) {
            t.myLeft = add(t.myLeft, key);
            t.treeSize ++;
            return t;
        } else if(key.compareTo(t.myItem) == 0){
        	return t;
        } else {
            t.myRight = add(t.myRight, key);
            t.treeSize ++;
            return t;
        }
    }
    
   public static void main(String[] args){
	   BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
	   t.add(5);
	   t.add(4);
	   t.add(7);
	   t.add(6);
	   t.add(1);
	   t.add(2);
	   t.add(10);
	   t.add(8);
	   for(int i = 1; i<9; i++){
	   System.out.println(t.kthLargest(i));
	   }
	   
   }
	
}
