import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	
	public void testNewAdd(){
		ResizableIntSequence a = new ResizableIntSequence(3);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		assertTrue(a.toString().equals("1 2 3 4 5 6 7 8"));	
	}
	
	public void testNewInsert(){
		ResizableIntSequence a = new ResizableIntSequence(3);
		a.add(1);
		a.add(2);
		a.add(3);
		a.insert(4, 3);
		a.add(5);
		assertTrue(a.toString().equals("1 2 3 4 5"));	
	}

	public void testRemoveInsert(){
		ResizableIntSequence a = new ResizableIntSequence(10);		
		a.add(1);
		a.add(2);
		a.add(3);
		a.insert(4, 2);
		a.add(5);
		a.remove(4);
		System.out.println(a.size());
		System.out.println(a.myValues.length); // The length would change from 10 to 5
		System.out.println(a.toString());
		assertTrue(a.toString().equals("1 2 4 3"));	
		assertTrue(a.myValues.length == 5);
	}
	
	
	
	

}
