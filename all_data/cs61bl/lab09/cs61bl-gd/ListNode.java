package lab09;

public class ListNode {

    private Object myItem;
    private ListNode myNext;

    public ListNode(Object item, ListNode next) {
        myItem = item;
        myNext = next;
    }
    public ListNode(Object item) {
        this(item, null);
    }

    public Object item() {
        return myItem;
    }
    public ListNode next() {
        return myNext;
    }
    public Object get(int index){
    	Object check;
    	
    		if(myItem == null){throw new IllegalArgumentException();}else{
    			if(index==0){check = this.item();}else{
    					check = myNext.get(index-1);
    				}
    			}return check;
    		}
    	
    }




