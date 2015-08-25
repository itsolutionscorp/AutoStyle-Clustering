public class IntSequence {

	// instance variables
	protected int[] myValues;   // sequence elements
	int myCount = 0;                // number of array cells used by sequence

	// constructor
	// capacity: actual size of the array or the (temporary) maximum
	// number of elements it can hold
	public IntSequence(int capacity) {
		// YOUR CODE HERE
		myValues = new int [capacity];
	}
	public boolean isEmpty (){

		if (myCount == 0){
			return true;
		}
		else {
			return false;
		}
	}
	public int size(){
		return myCount;
	}
	public int elementAt(int pos){
		if (pos < 0 || pos >= myValues.length){
			System.err.println("Postion is Out of Boundary!!!");
			System.exit(1);
			return 0;
		}else{
			return myValues[pos];
		}
	}

	// Add the argument to the sequence by placing it in the first
	// unused spot in the array and incrementing the count.
	// Assume that the sequence isn't full.
	public void add(int toBeAdded) {
		// YOUR CODE HERE
		myCount ++;
		myValues[myCount- 1] = toBeAdded;
		 
	}

	// Insert toInsert into the sequence at position insertPos,
	// shifting the later elements in the sequence over to make room
	// for the new element.
	// Assumptions: The array isn't full, i.e. myCount < myValues.length
	// Also, insertPos is between 0 and myCount, inclusive.
	public void insert(int toInsert, int insertPos) {
		ArrayOperations.insert(myValues,insertPos, toInsert);
		if (myCount<myValues.length){
			myCount++;
		}
	}

	// other methods go here
	public String toString (){
		String rValue = ""; 
		for (int i = 0; i < myCount; i++){

			if (i == myCount - 1){
				rValue = rValue + myValues[i]; 
			}else{
				rValue = rValue + myValues[i] + " ";
			}   		
		}
		return rValue;

	}
	public void remove (int pos){
		

		if (pos < 0 || pos >= myCount) {
			System.err.println("postion number is out of boundary");
			
		}
		
		// YOUR CODE HERE
		if (pos == myCount - 1) {
			myValues[myCount - 1] = 0; 
			myCount --; 
		
		}
		else {
			
			for (int i = pos; i < myCount-1; i ++){
				myValues[i] = myValues[i + 1];
			}
				myCount -- ; 
				
		}			

	}
	
	public static void main(String[] string ){
//		IntSequence s = new IntSequence (5); 
//		s.add(2);
//		s.add(3);
//		s.add(11);
//		s.add(7);
//		String exp = "2 3 11 7";
//		System.out.println(s.toString());
//		System.out.println(exp);
//		
//		IntSequence s1 = new IntSequence (5);
//		s1.add(3);
//		s1.add(1);
//		s1.add(2);
//		s1.add(5);
//		s1.add(7);
//		
//        s.insert(9,4);
//		
//        System.out.println(s.myValues[4]);
//        
        IntSequence s = new IntSequence (5);
		s.add(3);
		s.add(1);
		s.add(2);
		s.add(5);
		s.add(7);
		s.insert(9,4);
	
		String exp = "3 1 2 5 9";
		System.out.println(""+s.myCount);
		System.out.println(s.toString());
		System.out.println(exp);
	}

	
}

