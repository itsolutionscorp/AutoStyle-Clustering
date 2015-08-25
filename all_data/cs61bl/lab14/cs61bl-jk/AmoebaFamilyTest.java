import junit.framework.TestCase;

public class AmoebaFamilyTest extends TestCase {
	public void testHeight() {
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
		family.addChild("Mike", "2");
		family.addChild("Mike", "3");
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hilary");
		assertEquals(5, family.height());
		
		AmoebaFamily family2 = new AmoebaFamily("1");
		family2.addChild("1", "2");
		family2.addChild("1", "3");
		family2.addChild("1", "4");
		family2.addChild("2", "5");
		family2.addChild("2", "6");
		family2.addChild("3", "7");
		assertEquals(3, family2.height());
	}
}
