import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testConstructors() {
		IntSequence mySequence = new IntSequence(3);
		assertEquals(mySequence.myCount, 0);
}

public void testadd() {
        ResizableIntSequence mySequence = new ResizableIntSequence(20);
        mySequence.add(9);
        mySequence.add(4);
        mySequence.add(3);
        mySequence.add(7);

        assertEquals(mySequence.toString(), "9 4 3 7");

}

public void testsize() {
        ResizableIntSequence a = new ResizableIntSequence(1);
        a.add(9);
        a.add(8);
        assertEquals(a.size(), 2);
        ResizableIntSequence b = new ResizableIntSequence(1);
        b.add(6);
        b.add(0);
        b.add(2);
        b.add(2);
        assertEquals(b.size(), 4);
}

public void testremove() {
        ResizableIntSequence mySequence = new ResizableIntSequence(20);
        mySequence.add(9);
        mySequence.add(1);
        mySequence.remove(0);
        System.out.println(mySequence.toString());
        assertEquals(mySequence.toString(), "1");
       
       

        ResizableIntSequence b = new ResizableIntSequence(20);
        b.add(3);
        b.add(1);
        b.add(4);
        b.add(1);
        b.add(5);
        b.add(9);
        b.remove(0);
        b.remove(1);
        assertEquals(b.toString(), "1 1 5 9");

}

public void testtoString() {
        ResizableIntSequence mySequence = new ResizableIntSequence(20);
        mySequence.add(0);
        mySequence.add(5);
        mySequence.add(0);
        assertEquals("0 5 0", mySequence.toString());
}

public void testinsert() {
        ResizableIntSequence mySequence = new ResizableIntSequence(20);
        mySequence.add(1);
		mySequence.add(2);
		mySequence.add(3);
		mySequence.add(4);
		mySequence.insert(9, 2);
		assertEquals(mySequence.elementAt(2), 9);
		assertEquals(mySequence.elementAt(3), 3);
		assertEquals(mySequence.elementAt(4), 4);
}

public void testisEmpty() {
        ResizableIntSequence mySequence = new ResizableIntSequence(20);
        assertTrue(mySequence.isEmpty());
}

public void testelementAt() {
        ResizableIntSequence mySequence = new ResizableIntSequence(20);
        mySequence.add(10);
        mySequence.add(2);
        mySequence.add(9);
        assertTrue(mySequence.elementAt(2) == 9);
}

}
