import static org.junit.Assert.*;

import org.junit.Test;

public class AmoebaFamilyTest {
	// the problem with the method is that it does not differentiate between
	// different paths..

	@Test
	public void testheight1() { // when a node has more than one child...
								// problem
		
				
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "auntie");

		assertTrue(family.height() == 2);
		
		AmoebaFamily family2 = new AmoebaFamily("Amos McCoy");
		family2.addChild("Amos McCoy", "mom/dad");
		family2.addChild("mom/dad", "jack");

		family2.addChild("Amos McCoy", "auntie");

		assertTrue(family2.height() == 3);
		
		

	}

	@Test
	public void testheigh2() { // when a node has an uncle/aunt.. problem
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "auntie");
		family.addChild("mom/dad", "me");

		assertTrue(family.height() == 3);
	}

	// this test was for checking my new code works perfectly
	@Test
	public void testheigh3() { // when a node has an uncle/aunt.. problem

		AmoebaFamily family0 = new AmoebaFamily();
		assertTrue(family0.height() == 0);

		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "auntie");
		family.addChild("mom/dad", "me");
		assertTrue(family.height() == 3);
		family.addChild("mom/dad", "Fred");
		assertTrue(family.height() == 3);
		family.addChild("mom/dad", "Wilma");
		assertTrue(family.height() == 3);
		family.addChild("me", "Mike");
		assertTrue(family.height() == 4);
		family.addChild("me", "Homer");
		assertTrue(family.height() == 4);
		family.addChild("me", "Marge");
		assertTrue(family.height() == 4);
		family.addChild("Mike", "Bart");
		assertTrue(family.height() == 5);
		family.addChild("Mike", "Lisa");
		assertTrue(family.height() == 5);
		family.addChild("Marge", "Bill");
		assertTrue(family.height() == 5);
		family.addChild("Marge", "Hilary");
		assertTrue(family.height() == 5);
		

	}
	
	
	
}
