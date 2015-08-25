import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	public void testRemove(){
		IntSequence a = new IntSequence(5);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.remove(3);
		//System.out.println (a.toString());
		assertTrue (a.toString().equals("1 2 3 5"));
	}
	
	public void testInsert(){
		IntSequence a = new IntSequence(10);
		a.add(10);
		a.add(20);
		a.add(30);
		a.add(40);
		a.add(50);
		a.add(100);
		a.insert(3, 2);
		//System.out.println (a.toString());
		assertTrue (a.toString().equals("10 20 3 30 30 30"));
	}
	
	public void testContains(){
		IntSequence a = new IntSequence(10);
		a.add(10);
		a.add(20);
		a.add(30);
		a.add(40);
		a.add(50);
		a.add(100);
		System.out.println (a.toString());
		assertTrue (a.contains(20) == true);
		assertTrue (a.contains(1000) == false);
		assertTrue (a.contains(50) == true);
	}
	
	
	

}
