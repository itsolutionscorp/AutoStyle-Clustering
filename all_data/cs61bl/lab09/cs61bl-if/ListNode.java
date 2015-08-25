public class ListNode extends AbstractListNode{

    private Object myItem;
    private AbstractListNode myNext;

    public ListNode(Object item, AbstractListNode next) {
        myItem = item;
        myNext = next;
    }
    public ListNode(Object item) {
        this(item, null);
    }

    public Object item() {
        return myItem;
    }
    public AbstractListNode next() {
        return myNext;
    }
    
    
	@Override
	public boolean isEmpty() {
		if (myItem == null) return true;
		return false;
	}
	@Override
	public int size() {
		int count = 1;
		AbstractListNode temp = next();
		
		while (temp != null){
			temp = temp.next();
			count++;
		}
		return count;
	}
	@Override
	public String toString() {
		String TempString = "( ";
		AbstractListNode temp = this;
		
		while(temp != null){
			TempString = TempString + temp.item() + " ";
			temp = temp.next();
		}
		
		return TempString + ")";
	}
	@Override
	public boolean equals(Object t) {		
		if (t == null)
			return false;		
		
		AbstractListNode t1 = (ListNode) t;		
		AbstractListNode temp = this;
		
		while (temp != null && t1 != null){
			if (temp.item() != t1.item()){
				return false;
			}
			if ( (temp.next() == null && t1.next() != null) || (temp.next() != null && t1.next() == null))					
				return false;
			
			temp = temp.next();
			t1 = t1.next();
		}		
		return true;
		
	}
	@Override
	public String Type() {
		// TODO Auto-generated method stub
		return "ListNode";
	}

    
    
    
    
}

