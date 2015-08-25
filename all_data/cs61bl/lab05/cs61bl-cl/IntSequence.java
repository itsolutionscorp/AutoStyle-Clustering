public class IntSequence {

	protected int[] myValues;   // sequence elements
	int myCount;                // number of array cells used by sequence

	// constructor
	// capacity: actual size of the array or the (temporary) maximum
	// number of elements it can hold
	public IntSequence(int capacity) {
		this.myValues = new int[capacity];
		this.myCount= 0;
	}

	// Add the argument to the sequence by placing it in the first
	// unused spot in the array and incrementing the count.
	// Assume that the sequence isn't full.
	public void add(int toBeAdded) {
		if (this.myCount == this.myValues.length) {
			System.err.println("There is no space!");
			System.exit(1);
		}
		this.myValues[this.myCount] = toBeAdded;
		this.myCount = this.myCount + 1;
	}

	// Insert toInsert into the sequence at position insertPos,
	// shifting the later elements in the sequence over to make room
	// for the new element.
	// Assumptions: The array isn't full, i.e. myCount < myValues.length
	// Also, insertPos is between 0 and myCount, inclusive.
	public void insert(int toInsert, int insertPos) {
		for (int k = insertPos + 1; k <= myCount; k++) {
			myValues[k] = myValues[k-1];
		}
		myValues[insertPos] = toInsert;
		myCount++;
	}
	
	public boolean isEmpty() {
		if(this.myCount==0){
			return true;
		} 
		
		return false;
		

	}
	// other methods go here
	public int size(){
		return this.myCount;

	}
	public int elementAt(int pos) {
		if (pos < 0 || pos > myCount) {
			System.out.println("No element");
			System.exit(1);
		}
		return myValues[pos];
	}
	
	public String toString () {
		String St = new String();
		for (int i=0; i< myCount-1; i++) {
			St = St +Integer.toString(myValues[i])+ " ";
		}
		St = St + Integer.toString(myValues[myCount-1]);
		return St;
	}
	public int remove(int pos) {
		int Rem = this.myValues[pos];
		
		for(int i=pos+1; i<this.myCount; i++) {
			this.myValues[i-1] = this.myValues[i];
		}
		
		this.myValues[this.myCount-1] = 0;
		
		this.myCount = this.myCount -1;
		return Rem;

	}
	public boolean contains (int k) {
		for (int i=0; i<this.myCount; ) {
			if(this.myValues[i] == k);
			return true;
		}
		return false;
	}

	public int add() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert() {
		// TODO Auto-generated method stub
		return 0;
	}
}

