import java.util.NoSuchElementException;


abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    public int size() {
    	if (next().isEmpty()) {
    		return 1;
    	} else {
    		return 1 + next().size();
    	}
    }
    
    public Object get(int index) {
    	if (index==0){
    		return item();
    	} else {
    		return next().get(index-1);
    	}
    }
    
    public String stringHelper() {
    	if (!isEmpty() && !next().isEmpty()) {
    		return item().toString()  + " " + next().stringHelper();
    	} else if (!isEmpty() && next().isEmpty()) {
    		return item().toString();
    	} else {
    		return "";
    	}
    }
   public String toString() {
	   if (isEmpty()) {
		   return "( )";
	   }else {
		   return "( " + stringHelper() + " )";
	   }
	  
   }
   
   public boolean equalsHelper(AbstractListNode o, AbstractListNode p) {
	   if (!o.next().isEmpty()){
		   if (!(o.item().equals(p.item()))) {
			   return false;
		   } else {
			   return equalsHelper(o.next(), p.next());
		   }
	   } else {
		   return (o.item().equals(p.item()));
	   }
	   
   }
   
   public boolean equals(Object q) {
	   if (!(q instanceof AbstractListNode)) {
		   return false;
	   } else {
		  return equalsHelper((AbstractListNode) q, this); 
	   }
   }
   
   public Comparable smallest() {
	   if (isEmpty()) {
	     throw new NoSuchElementException("Can't find smallest in empty list.");
	   }
	   return smallestHelper(item()) ;
	 }

	 public Comparable smallestHelper(Comparable smallestSoFar) {
		if (this.next().isEmpty()) {
			return smallestSoFar;
		} else {
			return min(smallestSoFar, this.next().smallest());
		}
	   }

	 public static Comparable min(Comparable c1, Comparable c2) {
	   if (c1.compareTo(c2) < 0) {
	     return c1;
	   } else {
	     return c2;
	   }
	 }
	 
	 public AbstractListNode add(Comparable c) {
		
		 if(this.isEmpty()){
			 return new NonemptyListNode(c,null);
		 }else{
			 return new NonemptyListNode(this.item(),this.next().add(c));
		 }  
	 }
	 
	 public AbstractListNode append(AbstractListNode lst){
		 
		 AbstractListNode newList=new EmptyListNode();
 
		 AbstractListNode current=this;
		 while(!current.isEmpty()){
			 newList=newList.add(current.item());
			 current=current.next();
		 }
		 if(lst==null||lst.isEmpty())return newList;
		 current = lst;
		 while(!current.isEmpty()){
			 newList=newList.add(current.item());
			 current=current.next();
		 }
		 return newList;
	 }
	 
	 public AbstractListNode reverse(){
		 if(this.isEmpty()) return reverseHelper(this,null);
		 AbstractListNode newList=new EmptyListNode();
		 AbstractListNode current=this;
		 Comparable last = item();
		 while(!current.isEmpty()){
			 if(current.next().isEmpty()){
				 last=current.item();
			 } else {
				 newList=newList.add(current.item());
			 }
			 current=current.next();	
		 }
		 return reverseHelper(newList,last);
	 }
	 
	 public AbstractListNode reverseHelper(AbstractListNode lst, Comparable last){
		 AbstractListNode current=lst;
		 if (last==null) {
			 return lst;
		 }else if (lst.isEmpty()){
			 return new NonemptyListNode(last);
		 }else {
			 return new NonemptyListNode(last, lst.reverse());
		 }
	 }
	 
	 public AbstractListNode appendInPlace(AbstractListNode list2){
		 if (isEmpty()) {
			 return list2;
		 } else {
			 this.setNext(list2);
			 return this;
		 }
	 }
	 
	 public void setItem(Comparable item) {
		 return;
	 }
	 
	 public void setNext(AbstractListNode n) {
		 return;
	 }
	 
public static AbstractListNode merge(AbstractListNode a, AbstractListNode b){
		while (!b.isEmpty()) {
			System.out.println(a);
			if (!a.next().isEmpty() && a.item().compareTo(b.item())<0){
				while (!a.next().isEmpty() && a.item().compareTo(b.item())<0){
					System.out.println(a);
					a = a.next();
				}
				a.add(b.item());
				b = b.next();
			} else if (!b.next().isEmpty() && a.item().compareTo(b.item())>0) {
				while (!b.next().isEmpty() && a.item().compareTo(b.item())>0){
					System.out.println(b);
					b = b.next();
				}
				a.add(b.item());
				b = b.next();
			}
		}
		return a;
		
}
			 
			 //if (a.item().compareTo(b.item()) < 0){
//			 while (a.item().compareTo(b.item()) < 0) {
//				 if (!a.next().isEmpty()){
//					 a = a.next();
//				 } else {
//					 break;
//				 }
//			 }
//			 a.appendInPlace(b);
//			 return merge(a, b.next());
//		 } else if (a.item().compareTo(b.item()) > 0) {
//			 while (a.item().compareTo(b.item()) > 0) {
//				 if (!b.next().isEmpty()){
//					 b = b.next();
//				 } else {
//					 break;
//				 }
//			 }
//			 b.appendInPlace(a);
//			 return merge(a.next(), b);
//			 
//		 } else {
//		return merge(a.next(), b.next());
//		 }
//	 }

}

class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;

    public NonemptyListNode (Comparable item, AbstractListNode next) {
        myItem = item;
        if (next == null) {
            myNext = new EmptyListNode();
        } else {
            myNext = next;
        }
    }
    
    

    public NonemptyListNode (Comparable item) {
         this(item, new EmptyListNode());
    }

    public Comparable item() {
        return myItem;
    }

    public AbstractListNode next() {
        return myNext;
    }
    
    public boolean isEmpty() {
        return false;
    }


    @Override
	public void setItem(Comparable myItem) {
		this.myItem = myItem;
	}


    @Override
	public void setNext(AbstractListNode myNext) {
		this.myNext = myNext;
	}

}

class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    
    public Comparable item() {
        throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }
    
    public AbstractListNode next() {
        throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }
    
    public boolean isEmpty() {
        return true;
    }

}

