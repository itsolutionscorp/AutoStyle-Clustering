import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	IntSequence variable = new IntSequence(100);


public static void testAdd() {
	IntSequence variable = new IntSequence(4);
	variable.add(5);
	variable.add(7);
	variable.add(15);
	variable.add(20);
	assertTrue(variable.myValues[0] == 5);
	assertTrue(variable.myValues[1] == 7);
	assertTrue(variable.myValues[2] == 15);
	assertTrue(variable.myValues[3] == 20);
}


public static void insert() {
	IntSequence variable = new IntSequence(10);
	variable.add(1);
	variable.add(2);
	variable.add(3);
	variable.insert(8, 1);
	assertTrue(variable.myValues[1] == 8);
	assertTrue(variable.myValues[2] == 2);
	variable.insert(5,0);
	assertTrue(variable.myValues[0] == 5);
	assertTrue(variable.myValues[2] == 8);
	variable.insert(9,2);
	assertTrue(variable.myValues[2] == 9);
}


public static void testIsEmpty() {
	IntSequence variable = new IntSequence(5);
	assertTrue(variable.isEmpty());
	variable.add(2);
	assertTrue(variable.isEmpty() == false);
}

public static void testSize() {
	IntSequence variable = new IntSequence(7);
	variable.add(3);
	assertTrue(variable.size() == 1);
	variable.add(9);
	assertTrue(variable.size() == 2);
}

public static void testElementAt() {
	IntSequence variable = new IntSequence(10);
	variable.add(5);
	variable.add(7);
	variable.add(15);
	variable.add(20);
	assertTrue(variable.elementAt(0) == 5);
	assertTrue(variable.elementAt(2) == 15);
	assertTrue(variable.elementAt(3) == 20);
	
}

public static void testToString() {
	IntSequence variable = new IntSequence(12);
	variable.add(1);
	variable.add(3);
	variable.add(6);
	assertTrue(variable.toString().equals("1 3 6"));
	
}
public static void testRemove() {
	IntSequence variable = new IntSequence(10);
	variable.add(3);
	variable.add(-7);
	variable.add(42);
	variable.add(-11);
	variable.add(0);
	variable.add(6);
	variable.add(9);
	variable.remove(2);
	assertTrue(variable.myValues[2] == -11 && variable.myValues[3] == 0 && variable.myValues[4] ==6);
}
public static void testInsert() {
	IntSequence variable = new IntSequence(10);
	variable.add(3);
	variable.add(-7);
	variable.add(42);
	variable.add(-11);
	variable.add(0);
	variable.add(6);
	variable.add(9);
	variable.insert(5, 2);
	assertTrue (variable.myValues[2] == 5);
	assertTrue (variable.myValues[3] == 42);
	assertTrue (variable.myCount == 8);
	assertTrue (variable.myValues[0] == 3);
}
public static void testContains() {
	IntSequence variable = new IntSequence(10);
	variable.add(3);
	variable.add(-7);
	variable.add(42);
	variable.add(-11);
	variable.add(0);
	variable.add(6);
	assertTrue(variable.contains(3));
	assertFalse(variable.contains(99));
	assertTrue(variable.contains(0));
	
}
}