import static org.junit.Assert.*;

import org.junit.Test;


public class ResizableIntSequenceTest {

	@Test
	public void testAdd() {
		ResizableIntSequence a = new ResizableIntSequence(1);
		a.add(1);
		a.add(2);
		assertTrue (a.myCap == 6);
		
		ResizableIntSequence b = new ResizableIntSequence(10);
		for (int i=0; i<100; i++) {
			b.add(i);} 
		assertTrue(b.myCap == 113);
		
		ResizableIntSequence c = new ResizableIntSequence(1);
		c.add(1);
		assertTrue (c.myCap == 1);
		
		ResizableIntSequence blah = new ResizableIntSequence(20);
		blah.add(3);
		blah.add(2);
		assertTrue(blah.myValues[0]==3);
		assertTrue(blah.myValues[1]==2);
		
	
		
	}
	@Test
	public void testRemove() {
		
		ResizableIntSequence blah = new ResizableIntSequence(20);
		blah.add(3);
		blah.add(2);
		blah.add(1);
		blah.remove(1);
		assertTrue(blah.myValues[0]==3);
		assertTrue(blah.myValues[1]==1);
		assertTrue(blah.myValues[2]==0);
		assertTrue(blah.myCount==2);
		
		ResizableIntSequence b = new ResizableIntSequence(11);
		for (int i=0; i<11; i++) {
			b.add(i);
		}
		assertTrue(b.myCount == 11);
		
		for (int i=0; i<5; i++) {
			b.remove(0);		
		}
	
		assertTrue(b.myCap == 11);
		for (int i=0; i<3; i++) {
			b.remove(0);
		}
		assertTrue(b.myCap == 5);
	}

}
