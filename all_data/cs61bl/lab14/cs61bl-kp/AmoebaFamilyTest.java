import junit.framework.TestCase;


public class AmoebaFamilyTest extends TestCase {
	public void testAll() {
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
		System.out.println("Here's the family:");
		family.print();
		assertEquals (family.size(), 13);
		assertEquals (family.height(), 5);
		assertEquals (family.longestName(), "Amos McCoy");
		family.makeNamesLowercase();
		family.printFlat();
	}
}