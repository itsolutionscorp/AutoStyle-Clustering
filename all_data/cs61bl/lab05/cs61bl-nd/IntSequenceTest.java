import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {


	public void testInsert() {
		IntSequence s = new IntSequence(8);
		s.insert(1,0);
		s.insert(10,7);
		s.insert(8,5);
		assertTrue(s.myValues[0]==1);
		assertTrue (s.myValues[7]==10);
		assertTrue (s.myValues[5]==8);

	}
	
	public void testRemove() {
		IntSequence s = new IntSequence(8);
		int i;
		for(i=0;i<8;i++){
		s.myValues[i]=i;	
		}
		s.remove(s.myValues,0);
		s.remove(s.myValues,7);
		s.remove(s.myValues,5);
		assertTrue(s.myValues[0]==2);
		assertTrue (s.myValues[7]==0);
		assertTrue(s.myValues[5]==7);
	}
	public void testContain(){
		IntSequence s = new IntSequence(8);
		int i;
		for(i=0;i<8;i++){
			s.myValues[i]=i;	
			}
		assertTrue(s.contains(2)==3);
		
	}
	public void testtostring(){
		IntSequence s= new IntSequence(2);
		s.myValues[0]=0;
		s.myValues[1]=1;
		assertEquals("01",s.toString());
		
	}

}
