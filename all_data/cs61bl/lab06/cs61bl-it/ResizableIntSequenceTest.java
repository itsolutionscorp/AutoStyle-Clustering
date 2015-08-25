import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	
	
	public void testconstructor(){
		ResizableIntSequence R= new ResizableIntSequence(5);
		assertTrue(R.myCount==0); 
		assertTrue(R.isEmpty()==true);
	}
	
	public void testadd(){
		ResizableIntSequence s = new ResizableIntSequence (5); 
		s.add(2);
		s.add(3);
		s.add(11);
		s.add(7);
		String exp = "2 3 11 7";
		assertEquals(s.toString(), exp);
		s.add(7);
		String exp1 = "2 3 11 7 7";
		assertEquals(s.toString(), exp1);
		s.add(7);
		String exp2 = "2 3 11 7 7 7";
		assertEquals(s.toString(), exp2);
	}
	
	
    public void testinsert(){
		ResizableIntSequence R= new ResizableIntSequence(2);
		R.insert(2,0) ;
		R.insert(4,1);
		equals(R.toString()=="2 4");
		R.insert(4,2);
		equals(R.toString()=="2 4 4");
		R.insert(4,2);
		equals(R.toString()=="2 4 4 4");
		R.insert(4,2);
		equals(R.toString()=="2 4 4 4");
	}
    public void testremove(){
    	ResizableIntSequence R= new ResizableIntSequence(10);
    	R.add(2);
		R.add(3);
		R.add(11);
		R.add(7);
		R.add(7);
		R.add(7);
		R.add(7);
		assertTrue(R.myCount==7);
		assertTrue(R.myValues.length==10);
		R.remove(0);
		assertTrue(R.myCount==6);
		assertTrue(R.myValues.length==10);
		R.remove(0);
		assertTrue(R.myCount==5);
		assertTrue(R.myValues.length==10);
		R.remove(0);
		assertTrue(R.myCount==4);
		assertTrue(R.myValues.length==10);
		R.remove(0);
		assertTrue(R.myCount==3);
		assertTrue(R.myValues.length==10);
		R.remove(0);
		assertTrue(R.myCount==2);
		assertTrue(R.myValues.length==5);
		
    }

}
