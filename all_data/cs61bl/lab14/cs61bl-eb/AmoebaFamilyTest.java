import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
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
		System.out.println("Here's the family:");
		assertEquals(family.height(family.myRoot), 5);
	}
	
	@Test
	public void testHeight2() {
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
		family.addChild("Marge", "Hilary");
		family.addChild("Hilary", "Hilary");
		family.addChild("Hilary", "Helen");
		family.addChild("Marge", "Helen");
		family.addChild("Hilary", "Marge");
		System.out.println("Here's the family:");
		family.print(); 
		assertEquals(family.height(family.myRoot), 6);
		assertEquals(family.height(family.myRoot.myChildren.get(1)), 1);
		assertEquals(family.height(family.myRoot.myChildren.get(0).myChildren.get(0)), 4);
		assertEquals(family.height(family.myRoot.myChildren.get(0).myChildren.get(1)), 1);
	}


}
