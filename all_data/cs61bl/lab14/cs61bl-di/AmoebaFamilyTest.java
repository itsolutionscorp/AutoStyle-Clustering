import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
	public void test() {
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
		//family.replaceName("me", "George");
		//family.makeNamesLowercase();
		//family.printFlat();
		//family.print();
		assertEquals(family.longestNameLength(),10);
		assertEquals(family.longestName(),"Amos McCoy");
		assertEquals(family.size(), 13);
		assertEquals(family.height(), 5);
		assertFalse(family.height() == 11); //makes sure that respective childrens'heights aren't being added together
		AmoebaFamily fam2 = new AmoebaFamily("Mr.Pickles");
		assertTrue(fam2.height() == 1); //makes sure that height is 1 when no children
		
		
	}

}
