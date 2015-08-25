import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
	public void testMakeNamesLowercase() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "auntie");
		family.addChild("mom/dad", "me");
		family.addChild("mom/dad", "Fred");
		family.addChild("mom/dad", "Wilma");
		family.addChild("me", "Mike");
		family.addChild("me", "Homer");
		family.addChild("me", "Marge");
		family.addChild("Mike", "Bart");
		family.addChild("Mike", "Lisa");
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hilary");
		family.makeNamesLowercase();
		family.print();
	}

	@Test
	public void testReplaceName() {
		AmoebaFamily family = new AmoebaFamily("Bob");
		family.addChild("Bob", "Mary");
		family.addChild("Mary", "Lisa");
		
		family.replaceName("Bob", "Larry");
		assertTrue(family.myRoot.myName.equals("Larry"));
		
		family.replaceName("Mary", "Katie");
		family.printFlat(); // should print Larry, Katie, Lisa on separate lines
	}
	
	@Test
	public void testLongestName() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "auntie");
		family.addChild("mom/dad", "me");
		family.addChild("mom/dad", "Fred");
		family.addChild("mom/dad", "Wilma");
		family.addChild("me", "Mike");
		family.addChild("me", "Homer");
		family.addChild("me", "Marge");
		family.addChild("Mike", "Bart");
		family.addChild("Mike", "Lisa");
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hilary");
		assertTrue(family.longestName().equals("Amos McCoy"));
		
		family.addChild("Lisa", "ABCDEFGHIJKLMNOP");
		assertTrue(family.longestName().equals("ABCDEFGHIJKLMNOP"));
	}
	
	@Test
	public void testSize() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		assertTrue(family.size() == 1);
		
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "auntie");
		assertTrue(family.size() == 3);
		
		family.addChild("mom/dad", "me");
		family.addChild("mom/dad", "Fred");
		family.addChild("mom/dad", "Wilma");
		family.addChild("me", "Mike");
		family.addChild("me", "Homer");
		family.addChild("me", "Marge");
		family.addChild("Mike", "Bart");
		family.addChild("Mike", "Lisa");
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hilary");
		assertTrue(family.size() == 13);
	}
	
	@Test
	public void testHeight1() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		assertTrue(family.height() == 1);
		
		family.addChild("Amos McCoy", "mom/dad");
		assertTrue(family.height() == 2);
		
		family.addChild("Amos McCoy", "auntie");
		family.addChild("mom/dad", "me");
		family.addChild("mom/dad", "Fred");
		assertTrue(family.height() == 3);
		
		family.addChild("mom/dad", "Wilma");
		family.addChild("me", "Mike");
		assertTrue(family.height() == 4);
		
		family.addChild("me", "Homer");
		assertTrue(family.height() == 4);
		
		family.addChild("me", "Marge");
		family.addChild("Mike", "Bart");
		family.addChild("Mike", "Lisa");
		assertTrue(family.height() == 5);
		
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hilary");
		assertTrue(family.height() == 5);
	}
	
	@Test
	public void testHeight2() {
		AmoebaFamily family = new AmoebaFamily("Bob");
		assertTrue(family.height() == 1);
		
		family.addChild("Bob", "John");
		assertTrue(family.height() == 2);
		
		family.addChild("John", "Leslie");
		assertTrue(family.height() == 3);
		
		family.addChild("Bob", "Ron");
		assertTrue(family.height() == 3);
		
		family.addChild("Leslie", "Mary");
		assertTrue(family.height() == 4);
		
		family.addChild("Mary", "Peter");
		assertTrue(family.height() == 5);
		
		family.addChild("Bob", "Katie");
		assertTrue(family.height() == 5);
	}
}
