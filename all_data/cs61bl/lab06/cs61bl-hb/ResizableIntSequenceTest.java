import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {
	public void addTest()
	{
			  ResizableIntSequence a =new ResizableIntSequence(5);
				int [] array1= new int [] {1,2,3,4,5};
				int [] array2= new int [] {1,2,3,4,5,6};
				a.myValues=array1;
				a.myCount=5;
				a.add(6);	
				check(a.myValues,array2);
				int [] array3 = new int [] {1,2,3,0,0};
				int [] array4 = new int [] {1,2,3,4,0};
				a.myValues=array3;
				a.myCount=3;
				a.add(4);
				check(a.myValues,array4);
		
	}
	public void insertTest(){
		  ResizableIntSequence a =new ResizableIntSequence(5);
			int [] array1= new int [] {1,2,3,4,5};
			int [] array2= new int [] {1,2,3,6,4,5};
			a.myValues=array1;
			a.myCount=5;
			a.insert(6,3);
			check(a.myValues,array2);
			int [] array3 = new int [] {1,2,3,0,0};
			int [] array4 = new int [] {1,2,4,3,0};
			a.myValues=array3;
			a.myCount=3;
			a.insert(4,2);	
			check(a.myValues,array4);
	}
	public void removeTest(){
	  ResizableIntSequence a =new ResizableIntSequence(5);
		int [] array1= new int [] {1,2,3,4,5};
		int [] array2= new int [] {1,2,3,4};
		a.myValues=array1;
		a.myCount=5;
		a.remove(4);
		check(a.myValues,array2);
		int [] array3 = new int [] {1,2,3,0,0};
		int [] array4 = new int [] {1,2,0,0};
		a.myValues=array3;
		a.myCount=3;
		a.remove(2);
		check(a.myValues,array4);
	}
	  private void check (int[] array1, int[] array2) {
		assertTrue (array1.length == array2.length);
		for (int k = 0; k < array1.length; k++) {
			assertTrue (array1[k] == array2[k]);
		}
}
	 
	  
}

