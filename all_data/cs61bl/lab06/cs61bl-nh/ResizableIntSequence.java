import java.util.*;
public class ResizableIntSequence extends IntSequence{
	public ResizableIntSequence(int cap){
		super(cap);
	}
	public void add(int toBeAdded){
		if (myCount >=myValues.length){
			int [] output = new int[myCount+10];
			output[myCount]=toBeAdded;
			myValues=output;
			myCount++;
		}
		else{
			myValues[myCount]=toBeAdded;
			myCount++;
		}
		
	}
	public void insert(int toInsert, int insertPos){
		if (myCount >=myValues.length){
			int [] output = new int[myCount+10];
	        for (int k=insertPos+1; k<output.length; k++) {
	            output[k] = output[k-1];
	        }
			output[insertPos]=toInsert;
			myValues=output;
			myCount++;
		}
		else{
	        for (int k=insertPos+1; k<myCount; k++) {
	            myValues[k] = myValues[k-1];
	        }
	        myValues[insertPos] = toInsert;
	        myCount++;
		}
	}
	public void remove(int k){
		if (k < 0 || k >= myValues.length) {
			return;
		}
		for (int i =k;i<myCount;i++){
			myValues[i]=myValues[i+1];	
		}
		myValues[myCount]=0;
		int [] output = new int[myCount-1];
		for (int i =0; i < myCount-1; i++){
			output[i]=myValues[i];
		}
		myValues = output;
		myCount --;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResizableIntSequence myIntSeq = new ResizableIntSequence(10);
		myIntSeq.add(1);
		myIntSeq.add(2);
		myIntSeq.add(3);
		System.out.println(myIntSeq);
		myIntSeq.insert(0, 1);
		System.out.println(myIntSeq);
		myIntSeq.remove(3);
		System.out.println(myIntSeq);

	}

}
