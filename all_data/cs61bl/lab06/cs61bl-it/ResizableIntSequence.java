
public class ResizableIntSequence extends  IntSequence {

	
	public ResizableIntSequence(int size){
		super(size);
	}
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
	
	public void add(int toBeAdded) {
		if (myCount>= myValues.length){
			int[] oldSeq= new int[myCount];
			for (int i=0; i<myCount;i++){
				oldSeq[i]=this.myValues[i] ;
			}
			 this.myValues= new int[myCount*2];
			for (int i=0; i<myCount;i++){
				this.myValues[i]= oldSeq[i];
			}
			this.add(toBeAdded);
		}else {
			super.add(toBeAdded);
		}
	 }
	

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	if (myCount>= myValues.length || myCount==0){
    		int[] oldSeq= new int[myCount];
			for (int i=0; i<myCount;i++){
				oldSeq[i]=this.myValues[i] ;
			}
			int k;
			if (myCount==0){
				k=5;
				
			}else{
				k=myCount; 	
			}
			this.myValues= new int[k*2];
			for (int i=0; i<myCount;i++){
				this.myValues[i]= oldSeq[i];
			}
			super.insert(toInsert,insertPos);
    	}else{
    		super.insert(toInsert,insertPos);
    	}
    	
    	
    }
    public void remove (int pos){
    	super.remove(pos);
    	if ((myCount*100/myValues.length)< 25){
    		int[] oldSeq= new int[myCount];
    		for (int i=0; i<myCount;i++){
    			oldSeq[i]=this.myValues[i] ;
    		}
    		this.myValues= new int[Math.round(myValues.length/2)];
    		for (int i=0; i<myCount;i++){
    			this.myValues[i]= oldSeq[i];
    		}
    	}	
    }
    
    public static void main (String[] arg){
//    	ResizableIntSequence s = new ResizableIntSequence (5); 
//		s.add(2);
//		s.add(3);
//		s.add(11);
//		s.add(7);
//		String exp = "2 3 11 7";
//		System.out.println(exp);
//		System.out.println(s);
//		s.add(7);
//		String exp1 = "2 3 11 7 7";
//		System.out.println(exp1);
//		System.out.println(s);
//		s.add(7);
//		System.out.println(s);
//		System.out.println(""+s.myValues.length);
//		
		ResizableIntSequence R= new ResizableIntSequence(10);
    	R.add(2);
		R.add(3);
		R.add(11);
		R.add(7);
		R.add(7);
		R.add(7);
		R.add(7);
		System.out.println(""+R.myValues.length);
		System.out.println(""+R.myCount);
		System.out.println(""+(R.myCount*100/R.myValues.length));
		
		R.remove(0);
		System.out.println(""+R.myValues.length);
		System.out.println(""+R.myCount);
		R.remove(0);
		System.out.println(""+R.myValues.length);
		System.out.println(""+R.myCount);
		R.remove(0);
		System.out.println(""+R.myValues.length);
		System.out.println(""+R.myCount);
		R.remove(0);
		System.out.println(""+R.myValues.length);
		System.out.println(""+R.myCount);
		R.remove(0);
		System.out.println(""+R.myValues.length);
		System.out.println(""+R.myCount);

    }

}
