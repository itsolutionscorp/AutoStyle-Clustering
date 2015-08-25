import static org.junit.Assert.*;

import org.junit.Test;


public class ResizableIntSequenceTest {

	@Test
	public void testAdd() {
		ResizableIntSequence a = new ResizableIntSequence(6); 
		a.add(0);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.add(7);
		assertTrue (a.toString().equals("0 1 2 3 4 5 6 7"));
		assertTrue (a.myValues.length == 12);
	}
	@Test
	public void testInsert(){
		ResizableIntSequence a = new ResizableIntSequence(3); 
		a.add(0);
		a.add(1);
		a.add(2);
		a.insert(100, 2);
		assertTrue (a.toString().equals("0 1 100 2"));
		assertTrue (a.myValues.length == 6);
	}

	@Test
	public void testRemove() {
		ResizableIntSequence a = new ResizableIntSequence(20); 
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(1); 
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(1);
		a.add(2); 
		a.add(3);
		a.add(4);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4); //myCount == 16; capacity = 20
		a.add(1);
		a.add(2); 
		a.add(3);
		a.add(4); //myCount == 20; capacity doubles to 40
		a.add(1);
		assertTrue (a.remove(2) == 3); //myCount = 20; excess = 20
		assertTrue (a.size() == 20);
		assertTrue (a.toString().equals("1 2 4 1 2 3 4 1 2 3 4 1 2 3 4 1 2 3 4 1"));
		assertTrue (a.myValues.length == 30);
	}
}
