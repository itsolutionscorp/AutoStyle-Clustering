


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{
	
	public BinarySearchTree(){
		super();
	}

	
	public boolean contains(T key){
		if(super.myRoot!= null){
			return containsHelper(super.myRoot, key);
		}
		return false;
	}
	
	
	private boolean containsHelper (TreeNode t, T key){
		if(key.compareTo(t.myItem)== 0){
			return true;
		}
		if(key.compareTo(t.myItem)> 0){
			if(t.myRight != null)
			return containsHelper(t.myRight, key);
		}
		if(key.compareTo(t.myItem)< 0){
			if(t.myLeft != null)
			return containsHelper(t.myLeft, key);
		}
		return false;
		
	}
	
//	public void add(T key){
//		if(!contains(key)){
//			myRoot = addHelper(super.myRoot, key);
//		}
//		
//	}
//	
//	
//	private TreeNode addHelper (TreeNode t, T key){
//		if(t == null){
//			t = new TreeNode(key);
//		}else{
//			if(key.compareTo(t.myItem) > 0){
//				 t.myRight = addHelper(t.myRight, key);
//			}
//			if(key.compareTo(t.myItem) < 0){
//				t.myLeft = addHelper(t.myLeft, key);
//			}
//		}
//		return t;
//	}
	
	
	public void add(T key) {
	    myRoot = add(myRoot, key);
	    myRoot.size = myRoot.size + 1;	  
	}

	private TreeNode add(TreeNode t, T key) {
		int k = 0;
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	    	if (t.myLeft != null) {
	    		k = 1;
	    	}
	        t.myLeft = add(t.myLeft, key);
	        t.myLeft.size= t.myLeft.size+ k;
	        return t;
	    } else if (key.compareTo(t.myItem) > 0) { 
	    	if (t.myRight != null) {
	    		k = 1;
	    	}
	        t.myRight = add(t.myRight, key);
	        t.myRight.size = t.myRight.size +k;
	        return t;
	    }else{
	    	t.myItem = key;
	    }
	    return t;
	}
	 public Comparable kthLargest (int k){
		 Comparable ser = kthLargestHelper(myRoot, k);
		 return ser;
 		
     }
	 public T removeContent(T t) {
			if (super.myRoot == null) 
				return null;
			if (removeHelper(super.myRoot, t) != null) 
				super.myRoot = removeHelper(super.myRoot, t);
			return null;
		}
		
		public TreeNode removeHelper(TreeNode t, T key) {
			
			if (t == null) {
				return null;
			}
			
			if (key.compareTo(t.myItem) > 0) {
				t.myRight = removeHelper(t.myRight, key);
				return t;
			} else if (key.compareTo(t.myItem) < 0) {
				t.myLeft = removeHelper(t.myLeft, key);
				return t;
			} else {
				if (t.myRight == null) return t.myLeft;
	            if (t.myLeft  == null) return t.myRight;
	            t.myItem = getData(t.myLeft);
	            t.myLeft = removeHelper (t.myLeft, key);
	            return t;
			}
			
		}
		
		private T getData(TreeNode p)
		   {
		      while (p.myRight != null) p = p.myRight;

		      return p.myItem;
		   }
	
	 public T getNode(T node) {
			if (super.myRoot != null) {
				
			return getNodeHelper(super.myRoot, node);
			}
			return null;
		}
		
		public T getNodeHelper(TreeNode t, T key) {
			if (key.compareTo(t.myItem) > 0) {
				if (t.myRight != null)
					return getNodeHelper(t.myRight, key);
			} else if (key.compareTo(t.myItem) < 0) {
				if (t.myLeft != null)
					return getNodeHelper(t.myLeft, key);
			} else {
				return t.myItem;
			}
			return null;
		}
	 public Comparable kthLargestHelper(TreeNode t, int k){
//		
		 int right;
	     	if (t.myRight == null) {
	     		right = 0;	
	     	} else {
	     		right = t.myRight.getSize();
	     	}
	     	
	     	if (k==right) {
	 			return (Comparable) t.myItem;   		
	 		} else if (k<right) {
	 			return kthLargestHelper(t.myRight, k);
	 		} else if(t.myLeft != null){
	 			return kthLargestHelper(t.myLeft, k-(right +1));
	     	}
	     	return null;
	 }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 BinarySearchTree<Integer> i = new BinarySearchTree<Integer>();
	        i.myRoot = i.new TreeNode(5, i.new TreeNode(3, i.new TreeNode(2, i.new TreeNode(1), null), i.new TreeNode(4)), i.new TreeNode(6));
	       System.out.println(i.myRoot.getSize());
	        System.out.println(i.kthLargest(6));
	}

}
