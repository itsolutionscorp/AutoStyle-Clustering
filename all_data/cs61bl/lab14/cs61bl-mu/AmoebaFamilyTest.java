import junit.framework.TestCase;


public class AmoebaFamilyTest extends TestCase {
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
		assertEquals(13, family.size());
		
		AmoebaFamily families = new AmoebaFamily("AB");
		families.addChild("AB", "AC");
		families.addChild("AB", "RA");
		families.addChild("AC", "RT");
		families.addChild("RA", "RB");
		families.addChild("RB", "RC");
		families.addChild("RB", "RS");
		families.addChild("RS", "SR");
		families.addChild("SR", "ST");
		families.addChild("RA", "RZ");
		assertEquals(10, families.size());
	}
	
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
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hilary");
		assertEquals(5, family.height());
		
		AmoebaFamily familys = new AmoebaFamily("hi");
		assertEquals(1, familys.height());
		
		AmoebaFamily families = new AmoebaFamily("AB");
		families.addChild("AB", "AC");
		families.addChild("AB", "RA");
		families.addChild("AC", "RT");
		families.addChild("RA", "RB");
		families.addChild("RB", "RC");
		
		assertEquals(4, families.height());
		
		
	}
}
