import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
    //public void isEmpty()
    //public void size()
    //public void elementAt(int pos)
	public void testInit(){
		IntSequence i = new IntSequence(10);
		assertTrue (i.isEmpty());
		assertTrue (i.size()==0);
	}
    public void testAdd(){
		IntSequence i = new IntSequence(10);
		i.add(3);
		assertFalse (i.isEmpty());
		assertTrue (i.size()==1);
		assertTrue(i.elementAt(0)==3);
		assertTrue(i.contains(3));
    	
    }
    public void testInsert(){
		IntSequence i = new IntSequence(5);
		i.add(1);
		i.add(2);
		i.add(3);
		i.insert(4, 1);
		assertFalse(i.isEmpty());
		assertTrue (i.size()==4);
		assertTrue (i.contains(4));
		assertTrue(i.elementAt(1)==4);
    }

    public void remove(int pos){
		IntSequence i = new IntSequence(10);
		i.add(1);
		i.add(2);
		i.add(3);
		i.remove(1);
		assertFalse(i.isEmpty());
		assertTrue (i.size()==3);
		assertTrue(i.elementAt(1)==0);
		
    }

    public void testString(){
		IntSequence i = new IntSequence(3);
		i.add(1);
		i.add(2);
		i.add(3);
		assertEquals(i.toString(),"123");
    }
}
