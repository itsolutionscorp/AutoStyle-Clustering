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
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hilary");

		assertEquals(family.height(), 5);

		AmoebaFamily happyFamily = new AmoebaFamily("Great Grandpa");
		assertEquals(happyFamily.height(), 1);
		happyFamily.addChild("Great Grandpa", "Grandpa");
		happyFamily.addChild("Great Grandpa", "Granduncle");
		happyFamily.addChild("Great Grandpa", "Grandma");
		happyFamily.addChild("Great Grandpa", "Grandaunt");
		assertEquals(happyFamily.height(), 2);
		happyFamily.addChild("Grandma", "Papa");
		happyFamily.addChild("Grandma", "Mama");
		assertEquals(happyFamily.height(), 3);
		happyFamily.addChild("Papa", "me");
		happyFamily.addChild("Papa", "bro");
		happyFamily.addChild("Mama", "sis");
		assertEquals(happyFamily.height(), 4);
	}
}
