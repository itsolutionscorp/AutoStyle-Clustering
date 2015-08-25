import junit.framework.TestCase;


public class AmoebaFamilyTest extends TestCase {
	public void testHeight() {	
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		assertTrue(family.height() == 1);
		
		family.addChild("Amos McCoy", "mom/dad");
		assertTrue(family.height() == 2);

		family.addChild("Amos McCoy", "auntie");
		assertTrue(family.height() == 2);

		family.addChild("mom/dad", "me");
		assertTrue(family.height() == 3);

		family.addChild("mom/dad", "Fred");
		assertTrue(family.height() == 3);

		family.addChild("mom/dad", "Wilma");
		assertTrue(family.height() == 3);

		family.addChild("me", "Mike");
		assertTrue(family.height() == 4);

		family.addChild("me", "Homer");
		assertTrue(family.height() == 4);

		family.addChild("me", "Marge");
		assertTrue(family.height() == 4);

		family.addChild("Mike", "Bart");
		assertTrue(family.height() == 5);

		family.addChild("Mike", "Lisa");
		assertTrue(family.height() == 5);

		family.addChild("Marge", "Bill");
		assertTrue(family.height() == 5);

		family.addChild("Marge", "Hilary");
		assertTrue(family.height() == 5);

	}

}
