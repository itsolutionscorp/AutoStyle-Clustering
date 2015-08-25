


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{
	
	public void add(T key) {
	    myRoot = add(myRoot, key);
	}
	public void delete(T KV){
		if(myRoot!=null){
			myRoot = delete(myRoot,KV);
		}
	}
	public TreeNode delete(TreeNode t, T KV){
		if(t==null){return null;}
		int comparision = t.myItem.compareTo(KV);
		if(comparision ==0){
			if((t.myLeft==null)&&(t.myRight==null)){
				t = null;
				System.out.println("complete empty");
				return t;
			}
			if(t.myLeft==null){
				System.out.println("left complete empty");
				t=t.myRight;
				return t;
			}
			if(t.myRight==null){
				System.out.println("right complete empty");
				t= t.myLeft;
				return t;
			}
			else{
				TreeNode temp=t.myRight;
				if(temp.myLeft==null){
					t.myItem=temp.myItem;
					t.myRight=temp.myRight;
					return t;
				}
				while(temp.myLeft!=null){
				
					temp=temp.myLeft;
				}
				T toInsert = temp.myItem;
				t.myItem=toInsert;
				t=t.myRight;
				return t;
			}
			
		}
		if(comparision>0){
			TreeNode newLeft =delete(t.myLeft,KV);
			t.myLeft=newLeft;
			return t;
			
		}
		if(comparision<0){
			TreeNode newRight= delete(t.myRight,KV);
			t.myRight=newRight;
			return t;
		}
		return null;
	}
	
	
//	public void replace(T key,KVPair temp){
//	if(myRoot!=null){replace(myRoot,key,temp);}
//	}
//	private void replace(TreeNode t, T key, T toBe){
//		if(t!=null){
//			int comparision = t.myItem.compareTo(key);
//			if(comparision==0){
//				t.myItem= toBe;
//			}
//			if(comparision<0){
//				replace(t.myRight,key,toBe);
//			}
//			if(comparision>0){
//				replace(t.myLeft,key,toBe);
//			}
//		}
//	}
	
	public void addToLeft(TreeNode t, Object key){
	  t.myLeft=new TreeNode((T) key);
	}
	public void addToRight(TreeNode t, Object key){
		t.myRight=new TreeNode((T)key);
	}
	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	    	
	        return new TreeNode(key);
	        
	    } else if (key.compareTo(t.myItem) < 0) 
	    {   
	    	int riDepth;
	    	if(t.myRight==null){
	    	 riDepth = 0;
	    }else{ riDepth = t.myRight.depth();}
	    
	        t.myLeft = add(t.myLeft, key);
	        int leDepth;
	        
	       
	        leDepth = t.myLeft.depth();
	        t.setDepth(1+Math.max(riDepth,leDepth));
	        return t;
	    } else {
	    	   
		    	int leDepth;
		    	int riDepth;
		    	if(t.myLeft==null){
		    	 leDepth = 0;
		    }else{ leDepth = t.myLeft.depth();}
	        t.myRight = add(t.myRight, key);
	        riDepth = t.myRight.depth();
	        t.setDepth(1+Math.max(riDepth,leDepth));
	        return t;
	    }
	}
	
	public boolean contains(T key){
		if(myRoot!=null){
			return contains(myRoot,key);
			}
		
		return false;
	}
	public boolean contains(TreeNode t, T key){
		if(t==null){
			return false;
		}
		if(t.myItem.compareTo(key)==0){
			return true;
		}
		if(t.myItem.compareTo(key)<0){
			return contains(t.myRight,key);
		}
		else{
			return contains(t.myLeft,key);
		}
	}
	
	public Comparable kthLargest (int k){
		if(myRoot==null){
			throw new IllegalArgumentException();}
		return kthLargest(myRoot,k);
	}
	public Comparable kthLargest(TreeNode t, int k){
		if(k>=t.size()||k<0){
			throw new IllegalArgumentException();
			
		}
		if(t.myLeft==null){
			if(k==t.size()-1){
				return t.myItem;
			}else{
				return kthLargest(t.myRight,k);
			}
		}
		if(t.myRight ==null){
			if(k==0){return t.myItem;}
			else{
				return kthLargest(t.myLeft,k-1);
			}
		}
		
		// regular case
		else{
			if(k==0){
				return kthLargest(t.myRight,k);
			}else
		
	  if(k==t.myRight.size()){
			return t.myItem;
		}else if(k<t.myRight.size()){
			return kthLargest(t.myRight,k);}
		else{return kthLargest(t.myLeft,(k-(t.myRight.size()+1)));}
		}
		
		
			
		
				
			
				
				
			}
		
		
		
	

	}