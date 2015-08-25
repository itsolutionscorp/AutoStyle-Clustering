import static org.junit.Assert.*;

import org.junit.Test;


public class SequenceTest {

	@Test
	public void testIterator() {
		Sequence<String> Values = new Sequence<String>(4);
		Values.add("zero");
		Values.add("one");
		Values.add("two");
		Values.add("three");
		
		Sequence.Iterator Val = Values.Iterator();
		while(Val.hasNext()) {
			System.out.println(Val.next());
		}
		
		
	}
	@Test
	public void testadd() {
		Sequence<String> Values = new Sequence<String>(2);
		assertTrue (Values.isEmpty() == true);
		assertTrue (Values.size() == 0);
		
		Values.add("zero");
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 1);
		assertEquals (Values.elementAt(0), "zero");
		assertTrue (Values.contains("zero") == true);
		
		Sequence<Integer> Value1 = new Sequence<Integer>(5);
		assertTrue (Value1.isEmpty() == true);
		assertTrue (Value1.size() == 0);
		
		Value1.add(4);
		assertFalse (Value1.isEmpty() == true);
		assertTrue (Value1.size() == 1);
		assertTrue (Value1.elementAt(0) == 4);
		assertTrue (Value1.contains(4) == true);
		
		
		
	}
	@Test
	public void testinsert() {
		Sequence<String> Values = new Sequence<String>(3);
		assertTrue (Values.isEmpty() == true);
		assertTrue (Values.size() == 0);
		
		Values.add("zero");
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 1);
		assertEquals (Values.elementAt(0), "zero");
		assertTrue (Values.contains("zero") == true);
		
		Values.add("one");
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 2);
		assertEquals (Values.elementAt(1), "one");
		assertTrue (Values.contains("one") == true);
		
		Values.insert("two", 1);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 3);
		assertEquals (Values.elementAt(1), "two");
		assertTrue (Values.contains("two") == true);
		
		System.out.println(Values.toString());
		System.out.println(Values.size());
		
		Sequence<Integer> Value1 = new Sequence<Integer>(5);
		assertTrue (Value1.isEmpty() == true);
		assertTrue (Value1.size() == 0);
		
		Value1.add(4);
		assertFalse (Value1.isEmpty() == true);
		assertTrue (Value1.size() == 1);
		assertTrue (Value1.elementAt(0) == 4);
		assertTrue (Value1.contains(4) == true);
	
		Value1.insert(5,0);
		assertFalse (Value1.isEmpty() == true);
		assertTrue (Value1.size() == 2);
		assertTrue (Value1.elementAt(0) == 5);
		assertTrue (Value1.contains(5) == true);
		
	}
	@Test
	public void testremove() { //will reduce size if less than 25 percent, not that the factor is floored so if the number is 2.5 then it will have to be udner 2
		
			Sequence<String> Values = new Sequence<String>(4);
			assertTrue (Values.isEmpty() == true);
			assertTrue (Values.size() == 0);
			
			Values.add("zero");
			assertFalse (Values.isEmpty() == true);
			assertTrue (Values.size() == 1);
			assertEquals (Values.elementAt(0), "zero");
			assertTrue (Values.contains("zero") == true);
			
			Values.add("one");
			assertFalse (Values.isEmpty() == true);
			assertTrue (Values.size() == 2);
			assertEquals (Values.elementAt(1), "one");
			assertTrue (Values.contains("one") == true);
			
			Values.remove(1);
			assertFalse (Values.isEmpty() == true);
			assertTrue (Values.size() == 1);
			assertEquals (Values.elementAt(0), "zero");
			assertTrue (Values.contains("one") == false);
			
			System.out.println(Values.toString());
			
			Sequence<Integer> Value1 = new Sequence<Integer>(5);
			assertTrue (Value1.isEmpty() == true);
			assertTrue (Value1.size() == 0);
			
			Value1.add(4);
			assertFalse (Value1.isEmpty() == true);
			assertTrue (Value1.size() == 1);
			assertTrue (Value1.elementAt(0) == 4);
			assertTrue (Value1.contains(4) == true);
		
			Value1.insert(5,0);
			assertFalse (Value1.isEmpty() == true);
			assertTrue (Value1.size() == 2);
			assertTrue (Value1.elementAt(0) == 5);
			assertTrue (Value1.contains(5) == true);
			
			Value1.remove(0);
			assertFalse (Value1.isEmpty() == true);
			assertTrue (Value1.size() == 1);
			assertTrue (Value1.elementAt(0) == 4);
			assertTrue (Value1.contains(5) == false);
	}
		

}

