import junit.framework.TestCase;


public class AmoebaFamilyTest extends TestCase {
	public void testHeight() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		assertEquals(1, family.height());
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
		assertEquals(5, family.height());
		family.addChild("Bill", "a");
		assertEquals(6, family.height());
		family.addChild("a", "Peter");
		assertEquals(7, family.height());
		family.addChild("auntie", "P");
		assertEquals(7, family.height());
		
		AmoebaFamily family1 = new AmoebaFamily(null);
		assertEquals(0, family1.height());
	}
}
