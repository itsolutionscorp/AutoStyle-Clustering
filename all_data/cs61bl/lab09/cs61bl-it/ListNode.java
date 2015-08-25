public class ListNode {

	public Object myItem;
	public ListNode myNext;

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

	public ListNode get(int pos){
    	if (pos<0){
    		throw new IllegalArgumentException("pos is out of bounds!");
    	}
    	if (pos>0 && myNext== null ){
    		throw new IllegalArgumentException("pos is out of bounds!");
    		}
    	ListNode Ret= null;
    	boolean end= true;
    	while (end){
    		if (pos>0){
    			Ret=myNext.get(pos-1);
    		}else if (pos==0){
    			 end= false;
    			 Ret= this;
    		}else 
    			throw new IllegalArgumentException("pos is out of bounds!");
    			end= false;
    		}
    	return Ret;
    }

	public static void main(String[] args) {
		String object = "got him... lol";
		ListNode L = new ListNode(object);
		String object1 = "with what";
		ListNode L1 = new ListNode(object1, L);
		String object2 = "I want to love God Again!!!";
		ListNode L2 = new ListNode(object2, L1);
		String object3 = "lets";
		ListNode L3 = new ListNode(object3, L2);

		System.out.println(L3.get(2).myItem);

	}
}
