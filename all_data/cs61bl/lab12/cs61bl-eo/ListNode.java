
public class ListNode {

    private Object myKey1;
    private Object myValue;
    private ListNode myNext;

    public ListNode(Object item, Object value, ListNode next) {
        myKey1 = item;
        myValue = value;
        myNext = next;
    }
    public ListNode(Object item,Object value) {
        this(item,value, null);
    }

    public Object item() {
        return myKey1;
    }
    public Object value(){
    	return myValue;
    }
    public ListNode next() {
        return myNext;
    }
    public boolean containsKey1(Object k){
    	ListNode current;
    	for(current=this;current!=null;current=current.myNext){
    		if (current.myKey1 == k){
    			return true;
    		}
    	}
    	return false;
    }
    public boolean containsValue1(Object k){
    	ListNode current;
    	for(current=this;current!=null;current=current.myNext){
    		if (current.myValue == k){
    			return true;
    		}
    	}
    	return false;
    }
    public void add1(Object k,Object v){
    	ListNode current;
    	if (this.containsValue1(k)){
    		for (current=this; current.myNext!=null;current=current.myNext){
    			if (current.myKey1==k){
    				current.myValue=v;
    				return;
    			}
    		}
    	}
    	for (current=this;current.myNext!=null;current=current.myNext){}
    	current.myNext = new ListNode(k,v);
    }
    public ListNode remove1(ListNode a,Object k){
    	if (a.next()==null){
    		return a;
    	}
    	if (a.myKey1==k){
    		return remove1(a.next(),k);
    	}else{
    		return new ListNode(a.item(),a.value(),remove1(a.next(),k));
    	}
    }
    public Object get(Object K){
    	for(ListNode current = this; current!=null;current=current.next()){
    		if (current.myKey1==K){
    			return current.myValue;
    		}
    	}
    	return null;
    }

}