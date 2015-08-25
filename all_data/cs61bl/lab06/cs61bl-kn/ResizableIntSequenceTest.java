import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	ResizableIntSequence mySequence = new ResizableIntSequence(5);
	
	public void testConstructor() {
		assertEquals(mySequence.myCount, 0);
		assertEquals(mySequence.myValues.length, 5);
		int k = 0;
		while (k < 5) {
			assertEquals(mySequence.myValues[k],0);
			k++;
		}
	}
	
	public void testAdd() {
		mySequence.add(0);
		mySequence.add(1);
		mySequence.add(2);			
		mySequence.add(3);
		assertEquals(mySequence.myCount, 4);
		assertEquals(mySequence.myValues.length, 5);
		int k = 0;
		while (k < 4) {
			assertEquals(mySequence.myValues[k],k);
			k++;
	}
		assertEquals(mySequence.myValues[4],0);
		
		mySequence.add(4);
		assertEquals(mySequence.myCount, 5);
		assertEquals(mySequence.myValues.length, 5);
		k = 0;
		while (k < 5) {
			assertEquals(mySequence.myValues[k],k);
			k++;
	}
		mySequence.add(5);
		assertEquals(mySequence.myCount, 6);
		assertEquals(mySequence.myValues.length, 6);
		k = 0;
		while (k < 6) {
			assertEquals(mySequence.myValues[k],k);
			k++;
	}
	}
	public void testInsert() {
		mySequence.add(0);
		mySequence.add(1);
		mySequence.insert(3,2);
		assertEquals(mySequence.myValues[0], 0);
		assertEquals(mySequence.myValues[1], 1);
		assertEquals(mySequence.myValues[2], 3);
		assertEquals(mySequence.myCount, 3);
		mySequence.insert(2, 2);
		assertEquals(mySequence.myValues[0], 0);
		assertEquals(mySequence.myValues[1],1);
		assertEquals(mySequence.myValues[2], 2);
		assertEquals(mySequence.myValues[3], 3);
		assertEquals(mySequence.myCount, 4);
		mySequence.insert(-1, 0);
		assertEquals(mySequence.myValues[0], -1);
		assertEquals(mySequence.myValues[1], 0);
		assertEquals(mySequence.myValues[2], 1);
		assertEquals(mySequence.myValues[3], 2);
		assertEquals(mySequence.myValues[4], 3);
		assertEquals(mySequence.myCount, 5);
		mySequence.insert(5, 5);
		assertEquals(mySequence.myValues[0], -1);
		assertEquals(mySequence.myValues[1], 0);
		assertEquals(mySequence.myValues[2], 1);
		assertEquals(mySequence.myValues[3], 2);
		assertEquals(mySequence.myValues[4], 3);
		assertEquals(mySequence.myValues[5], 5);
		assertEquals(mySequence.myCount, 6);
		mySequence.insert(-2, 0);
		assertEquals(mySequence.myValues[0], -2);
		assertEquals(mySequence.myValues[1], -1);
		assertEquals(mySequence.myValues[2], 0);
		assertEquals(mySequence.myValues[3], 1);
		assertEquals(mySequence.myValues[4], 2);
		assertEquals(mySequence.myValues[5], 3);
		assertEquals(mySequence.myValues[6], 5);
		assertEquals(mySequence.myCount, 7);
		mySequence.insert(4, 6);
		assertEquals(mySequence.myValues[0], -2);
		assertEquals(mySequence.myValues[1], -1);
		assertEquals(mySequence.myValues[2], 0);
		assertEquals(mySequence.myValues[3], 1);
		assertEquals(mySequence.myValues[4], 2);
		assertEquals(mySequence.myValues[5], 3);
		assertEquals(mySequence.myValues[6], 4);
		assertEquals(mySequence.myValues[7], 5);
		assertEquals(mySequence.myCount, 8);
	}
	
	public void testRemove() {
		mySequence.add(0);
		mySequence.add(1);
		mySequence.add(2);			
		mySequence.add(3);
		mySequence.add(4);
		mySequence.remove(0);
		int k = 0;
		while (k < 4) {
			assertEquals(mySequence.myValues[k],k+1);
			k++;
		}
		assertEquals(mySequence.myCount, 4);
		assertEquals(mySequence.myValues.length, 5);
		mySequence.remove(3);
		assertEquals(mySequence.myCount, 3);
		assertEquals(mySequence.myValues.length, 5);
		assertEquals(mySequence.myValues[0],1);
		assertEquals(mySequence.myValues[1],2);
		assertEquals(mySequence.myValues[2],3);
		mySequence.add(4);
		mySequence.add(5);										//mySequence.myValues = {1,2,3,4,5} at this point, with mySequence.myCount = 5
		mySequence.remove(2);
		assertEquals(mySequence.myCount, 4);
		assertEquals(mySequence.myValues.length, 5);
		assertEquals(mySequence.myValues[0],1);
		assertEquals(mySequence.myValues[1],2);
		assertEquals(mySequence.myValues[2],4);
		assertEquals(mySequence.myValues[3],5);	
		mySequence.remove(0);
		mySequence.remove(1);
		assertEquals(mySequence.myCount, 2);
		assertEquals(mySequence.myValues.length,4);
		assertEquals(mySequence.myValues[0],2);
		assertEquals(mySequence.myValues[1],5);
		mySequence.insert(3, 1);
		mySequence.insert(1, 0);
		mySequence.insert(4, 3);
		mySequence.add(6); 									//mySequence.myValues = {1,2,3,4,5,6} at this point, with mySequence.myCount = 6  and mySequence.myValues.length = 6
		mySequence.remove(5);
		mySequence.remove(4);
		mySequence.remove(3);
		mySequence.remove(2);
		assertEquals(mySequence.myCount, 2);
		assertEquals(mySequence.myValues.length,4);
		assertEquals(mySequence.myValues[0],1);
		assertEquals(mySequence.myValues[1], 2);
	}

//test other methods in IntSequence
public void testToString() {
	mySequence.add(0);
	mySequence.add(1);
	mySequence.add(2);
	assertEquals(mySequence.toString(), "0 1 2");
	mySequence.myCount = mySequence.myValues.length;
	assertEquals(mySequence.toString(), "0 1 2 0 0");
}

public void testElementAt() {
	mySequence.add(0);
	mySequence.add(1);
	mySequence.add(2);			
	assertEquals(mySequence.elementAt(0), 0);
	assertEquals(mySequence.elementAt(1), 1);
	assertEquals(mySequence.elementAt(2), 2);
	//assertEquals(mySequence.elementAt(3), -1);					//for testing purposes
	//assertEquals(mySequence.elementAt(4),-1);
	//assertEquals(mySequence.elementAt(5),-1);
}

public void testIsEmpty() {
	assertTrue(mySequence.isEmpty());
	mySequence.add(0);
	assertFalse(mySequence.isEmpty());
	mySequence.add(1);
	mySequence.add(2);
	mySequence.add(3);	
	mySequence.add(4);
	assertFalse(mySequence.isEmpty());
}

public void testSize() {
	assertEquals(mySequence.size(),0);
	mySequence.add(0);
	mySequence.add(1);
	mySequence.add(2);	
	assertEquals(mySequence.size(), 3);
	mySequence.add(3);
	mySequence.add(4);
	assertEquals(mySequence.size(), 5);	
}

public void testContains() {
	mySequence.add(0);
	mySequence.add(1);
	mySequence.add(2);	
	mySequence.myValues[3] = 3;
	
	assertTrue(mySequence.contains(0));
	assertFalse(mySequence.contains(3));
	mySequence.myCount = 4;
	assertTrue(mySequence.contains(3));
	mySequence.add(0);
	assertTrue(mySequence.contains(0));
	mySequence.insert(5,4);
	assertTrue(mySequence.contains(2));
	assertTrue(mySequence.contains(5));
}
}
