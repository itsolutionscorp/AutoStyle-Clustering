import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
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
		assertEquals(5, family.height());

		AmoebaFamily family2 = new AmoebaFamily("Amos McCoy");
		family2.addChild("Amos McCoy", "mom/dad");
		assertEquals(2, family2.height());
		
	}	
}
