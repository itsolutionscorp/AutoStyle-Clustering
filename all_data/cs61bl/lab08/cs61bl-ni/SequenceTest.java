import junit.framework.TestCase;
import java.util.Iterator;


public class SequenceTest extends TestCase {
	
	public void testRemove(){
		Sequence a = new Sequence(5);
		a.add("hello");
		a.add("yo");
		a.add("me");
		a.add("ay");
		a.add("hola");
		a.remove(1);
		//System.out.println (a.toString());
		assertTrue (a.toString().equals("hello me ay hola"));
	}
	
	public void testInsert(){
		Sequence a = new Sequence(10);
		a.add("hello");
		a.add("yo");
		a.add("me");
		a.add("ay");
		a.add("hola");
		a.insert("hey", 2);
		//System.out.println (a.toString());
		assertTrue (a.toString().equals("hello yo hey me ay hola"));
	}
	
	public void testContains(){
		Sequence a = new Sequence(10);
		a.add("hello");
		a.add("yo");
		a.add("me");
		a.add("ay");
		a.add("hola");
		assertTrue (a.contains("me") == true);
		assertTrue (a.contains("niehao") == false);
		assertTrue (a.contains("ay") == true);
	}
	
	public void testIter() {
		Sequence a = new Sequence(10);
		a.add("hello");
		a.add("yo");
		a.add("me");
		a.add("ay");
		a.add("hola");
		Iterator<String> b = a.iterator();
		assertTrue(b.hasNext());
		assertEquals(b.next(), "hello");
		assertEquals(b.next(), "yo");
		assertEquals(b.next(), "me");
		assertEquals(b.next(), "ay");
		assertTrue(b.hasNext());
		assertEquals(b.next(), "hola");
		assertTrue(!b.hasNext());
	}
	
	
	

}