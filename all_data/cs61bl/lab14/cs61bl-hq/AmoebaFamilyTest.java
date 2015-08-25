import junit.framework.TestCase;


public class AmoebaFamilyTest extends TestCase {
	public void testHeight() {
		AmoebaFamily none = new AmoebaFamily("null");
		none.myRoot = null;
		assertTrue (none.height() == 0);
		
		AmoebaFamily single = new AmoebaFamily("Bob");
		assertTrue (single.height() == 1);
		
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
		assertTrue (family.height() == 5);
	}
}
