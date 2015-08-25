import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
	public void heightTest() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		assertTrue(family.height() == 1);
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "auntie");
		assertTrue(family.height() == 2);
		family.addChild("mom/dad", "me");
		family.addChild("mom/dad", "Fred");
		family.addChild("mom/dad", "Wilma");
		assertTrue(family.height() == 3);
		family.addChild("me", "Mike");
		family.addChild("me", "Homer");
		family.addChild("me", "Marge");
		assertTrue(family.height() == 4);
		family.addChild("Mike", "Bart");
		family.addChild("Mike", "Lisa");
		assertTrue(family.height() == 5);
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hilary");
		assertTrue(family.height() == 5);
		
	}

}
