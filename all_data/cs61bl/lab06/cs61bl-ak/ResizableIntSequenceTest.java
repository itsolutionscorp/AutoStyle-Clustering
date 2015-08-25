import junit.framework.TestCase;

public class ResizeableIntSequenceTest extends TestCase {

	//Tests for constructor// 
	public void testIntSequenceTest() {
		ResizeableIntSequence alanGrades = new ResizeableIntSequence(10); 
		assertEquals(alanGrades.myCount, 0);
		}
	//Tests for add method// 
	public void testadd () {
		ResizeableIntSequence alanGrades = new ResizeableIntSequence(10);
		alanGrades.add(90);
		alanGrades.add(94);
		alanGrades.add(95);
		alanGrades.add(92);
		alanGrades.add(25);
		alanGrades.add(100);
		assertTrue(alanGrades.myValues.length == 10); 
		assertTrue(alanGrades.myValues[0] == 90); 
		assertTrue(alanGrades.myValues[1] == 94); 
		assertTrue(alanGrades.myValues[2] == 95); 
		assertTrue(alanGrades.myValues[3] == 92); 
		assertTrue(alanGrades.myValues[4] == 25); 
		assertTrue(alanGrades.myValues[5] == 100);
		alanGrades.add(90);
		alanGrades.add(94);
		alanGrades.add(95);
		alanGrades.add(92);
		alanGrades.add(25);
		alanGrades.add(100);
		assertTrue(alanGrades.myValues.length == 20);
		assertTrue(alanGrades.myValues[0] == 90); 
		assertTrue(alanGrades.myValues[1] == 94); 
		assertTrue(alanGrades.myValues[2] == 95); 
		assertTrue(alanGrades.myValues[3] == 92); 
		assertTrue(alanGrades.myValues[4] == 25); 
		assertTrue(alanGrades.myValues[5] == 100);
		for (int i = 0; i < 88; i++) {
			alanGrades.add(0);
		}
		assertTrue(alanGrades.myValues.length == 100);
		alanGrades.add(0);
		assertTrue(alanGrades.myValues.length == 110);
	}
	
	public void testAdd2() {
		ResizeableIntSequence alanGrades = new ResizeableIntSequence(8);
		alanGrades.add(90);
		alanGrades.add(94);
		alanGrades.add(95);
		alanGrades.add(92);
		alanGrades.add(25);
		alanGrades.add(100);
		alanGrades.add(99); 
		alanGrades.add(21); 
		assertTrue(alanGrades.myValues.length == 8); 
		assertTrue(alanGrades.myValues[0] == 90); 
		assertTrue(alanGrades.myValues[1] == 94); 
		assertTrue(alanGrades.myValues[2] == 95); 
		assertTrue(alanGrades.myValues[3] == 92); 
		assertTrue(alanGrades.myValues[4] == 25); 
		assertTrue(alanGrades.myValues[5] == 100); 
		assertTrue(alanGrades.myValues[6] == 99); 
		assertTrue(alanGrades.myValues[7] == 21); 
		alanGrades.add(101); 
		assertEquals(alanGrades.myCount, 9); 
		assertTrue(alanGrades.myValues[8] == 101); 
		assertTrue(alanGrades.myValues.length == 18); 

		ResizeableIntSequence emptyList = new ResizeableIntSequence(100); 
		emptyList.myCount = 100; 
		emptyList.add(0); 
		assertEquals(emptyList.myValues[100], 0); 
		assertTrue(emptyList.myValues.length == 110); 
	}
	
	//Test insert method// 
	public void testinsert() {	
		ResizeableIntSequence alanGrades = new ResizeableIntSequence(10);
		alanGrades.add(78);
		alanGrades.add(99); 
		alanGrades.add(100);
		alanGrades.add(101);
		alanGrades.add(102);
		alanGrades.add(103);
		alanGrades.insert(0, 3);
		assertTrue(alanGrades.myValues[3] == 0);
		assertTrue(alanGrades.myValues[1] == 99); 
		assertTrue(alanGrades.myValues[4] == 101);
		assertTrue(alanGrades.myValues[5] == 102); 
		assertTrue(alanGrades.myCount == 7); 
	}
	
	public void testinsert2() {
		ResizeableIntSequence alanGrades = new ResizeableIntSequence(10);
		alanGrades.add(78);
		alanGrades.add(99); 
		alanGrades.add(100);
		alanGrades.add(101);
		alanGrades.add(102);
		alanGrades.add(103);
		alanGrades.insert(0, 3);
		assertTrue(alanGrades.myValues[3] == 0);
		assertTrue(alanGrades.myValues[1] == 99); 
		assertTrue(alanGrades.myValues[4] == 101);
		assertTrue(alanGrades.myValues[5] == 102); 
		assertTrue(alanGrades.myCount == 7); 
		alanGrades.insert(0,0); 
		assertEquals(alanGrades.myValues[0], 0); 
		assertEquals(alanGrades.myValues[1], 78); 
		assertEquals(alanGrades.myValues[4], 0); 
		alanGrades.insert(10,8); 
		assertEquals(alanGrades.myValues[8], 10); 
		assertEquals(alanGrades.myValues[0], 0); 
		assertEquals(alanGrades.myValues[1], 78); 
		assertEquals(alanGrades.myValues[4], 0);
		alanGrades.add(89); 
		alanGrades.insert(75, 5); 
		assertEquals(alanGrades.myCount, 11); 
		assertTrue(alanGrades.myValues[10] == 89); 
		assertTrue(alanGrades.myValues.length == 20); 
		assertEquals(alanGrades.myValues[8], 103); 
		assertEquals(alanGrades.myValues[0], 0); 
		assertEquals(alanGrades.myValues[1], 78); 
		assertEquals(alanGrades.myValues[4], 0);

		ResizeableIntSequence emptyList = new ResizeableIntSequence(100); 
		emptyList.myCount = 100; 
		emptyList.insert(0,0); 
		assertEquals(emptyList.myValues[100], 0); 
		assertTrue(emptyList.myValues.length == 110); 
	}
	
	//Test isEmpty method// 
	public static void testisEmpty() {
		ResizeableIntSequence alanGrades = new ResizeableIntSequence(10);
		assertTrue(alanGrades.isEmpty() == true); 
		alanGrades.add(3);
		assertTrue(alanGrades.isEmpty() == false);
	}
	
	//Test size method// 
	public void testsize() {
		ResizeableIntSequence alanGrades = new ResizeableIntSequence(10);
		assertTrue(alanGrades.size() == 0);
		alanGrades.add(78);
		alanGrades.add(99); 
		alanGrades.add(100);
		alanGrades.add(101);
		assertTrue(alanGrades.size() == 4);
		alanGrades.add(102);
		alanGrades.add(103);
		assertTrue(alanGrades.size() == 6);
		alanGrades.insert(0, 3);
		assertTrue(alanGrades.size() == 7);
	}
	
	//Test ElementAt method// 
	public void testelementAt() {
		ResizeableIntSequence alanGrades = new ResizeableIntSequence(10);
		alanGrades.add(78);
		alanGrades.add(99); 
		alanGrades.add(100);
		alanGrades.add(101);
		alanGrades.add(102);
		alanGrades.add(103);
		assertTrue(alanGrades.elementAt(0) == 78);
		assertTrue(alanGrades.elementAt(5) == 103);
		assertTrue(alanGrades.elementAt(2) == 100);
	}
	
	//Test toString method// 
	public void testtoString() {
		ResizeableIntSequence alanGrades = new ResizeableIntSequence(10);
		assertTrue(alanGrades.toString().equals(""));
		alanGrades.add(78);
		alanGrades.add(99); 
		alanGrades.add(100);
		alanGrades.add(101);
		alanGrades.add(102);
		alanGrades.add(103);
		assertTrue(alanGrades.toString().equals("78 99 100 101 102 103"));
	}
	
	//Test remove method// 
	public void testremove() {
		ResizeableIntSequence alanGrades = new ResizeableIntSequence(10); 
		alanGrades.add(78);
		alanGrades.add(99); 
		alanGrades.add(100);
		alanGrades.add(101);
		alanGrades.add(102);
		alanGrades.add(103);
		alanGrades.remove(1); 
		assertEquals(alanGrades.myCount, 5); 
		alanGrades.remove(2); 
		assertTrue(alanGrades.myValues[0] == 78);
		assertTrue(alanGrades.myValues[1] == 100);
		assertTrue(alanGrades.myValues[2] == 102);
		assertTrue(alanGrades.myValues[3] == 103);
		assertEquals(alanGrades.myCount, 4); 
		for (int i = 0; i < 94; i++) {
			alanGrades.add(0);
		}
		assertTrue(alanGrades.myValues.length == 100);
		for (int i = 90; i > 20; i--) {
			alanGrades.remove(i);
		}
		assertTrue(alanGrades.myValues.length == 50);
	}	
}
