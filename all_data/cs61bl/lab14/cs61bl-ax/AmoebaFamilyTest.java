import junit.framework.TestCase;


public class AmoebaFamilyTest extends TestCase {
	
	public void testHeight1() {
		AmoebaFamily family = new AmoebaFamily("US");
		family.addChild("US", "CALIFORNIA");
		family.addChild("US", "NEW YORK");
		family.addChild("US", "MASSACHUSETTS");
		family.addChild("CALIFORNIA", "FREMONT");
		family.addChild("CALIFORNIA", "BERKELEY");
		family.addChild("BERKELEY", "CAL BEARS");
		assertTrue(family.height() == 4);
	}
	
	public void testHeight2() {
		AmoebaFamily family = new AmoebaFamily("US");
		family.addChild("US", "CALIFORNIA");
		family.addChild("CALIFORNIA", "BERKELEY");
		family.addChild("BERKELEY", "CAL BEARS");
		
		assertTrue(family.height() == 4);
		
	}

	public void testLowercase() {
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
//		family.printFlat();
	}
	
	public void testLongestNameLength() {
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
		assertTrue(family.longestNameLength() == "Amos McCoy".length());
	}
	
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
		assertEquals(family.longestName(), "Amos McCoy");
	}

	public void testSize() {
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
		assertTrue(family.size() == 13);
	}
	
	public void testReplaceName() {
		AmoebaFamily family = new AmoebaFamily("US");
		family.addChild("US", "CALIFORNIA");
		family.addChild("US", "NEW YORK");
		family.addChild("US", "MASSACHUSETTS");
		family.addChild("CALIFORNIA", "FREMONT");
		family.addChild("CALIFORNIA", "BERKELEY");
		family.addChild("BERKELEY", "CAL BEARS");
		family.replaceName("MASSACHUSETTS", "TEXAS");
//		family.print();
	}
}
