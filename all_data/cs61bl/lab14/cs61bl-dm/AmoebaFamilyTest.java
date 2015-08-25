import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
	public void testHeight() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		assertEquals (1, family.height());
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "auntie");
		family.addChild("mom/dad", "me");
		assertEquals (3, family.height());
		family.addChild("mom/dad", "Fred");
		family.addChild("mom/dad", "Wilma");
		family.addChild("me", "Mike");
		family.addChild("me", "Homer");
		family.addChild("me", "Marge");
		family.addChild("Mike", "Bart");
		family.addChild("Mike", "Lisa");
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hilary");
		family.addChild("Hilary", "Hilary II");
		assertEquals (6, family.height());
	}
}
