import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testAdd() {
		IntSequence obj = new IntSequence(5);
		for (int i =0; i<5;i++)
		{
			obj.add(i);
		}
		assertEquals(obj.toString(),"0 1 2 3 4");
		
	}
	
	public void testRemove() {
		IntSequence obj = new IntSequence(5);
		for (int i =0; i<5;i++)
		{
			obj.add(i);
		}
		obj.remove(5);
		obj.remove(-1);
		assertEquals(obj.toString(),"0 1 2 3 4");
		obj.remove(4);
		assertEquals(obj.toString(),"0 1 2 3");
		obj.remove(0);
		assertEquals(obj.toString(),"1 2 3");
		
	}
	
	public void testInsert() {
		IntSequence obj = new IntSequence(10);
		for (int i =0; i<5;i++)
		{
			obj.add(i);
		}
		obj.insert(5,2);
		//obj.insert(4,-1);
		//obj.insert(4,6);
		
		assertEquals(obj.toString(),"0 1 5 2 3 4");
		obj.insert(4,0);
		assertEquals(obj.toString(),"4 0 1 5 2 3 4");
		obj.insert(0,5);
		assertEquals(obj.toString(),"4 0 1 5 2 0 3 4");

	}
	
	public void testContain ()
	{
		IntSequence obj = new IntSequence(10);
		for (int i =0; i<5;i++)
		{
			obj.add(i);
		}
		
		assertEquals(obj.contains(4),true);
		assertEquals(obj.contains(10),false);
		obj.remove(4);
		assertEquals(obj.contains(4),false);
	}
}
