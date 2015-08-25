import static org.junit.Assert.*;

import org.junit.Test;

public class AmoebaFamilyTest {

	@Test
	public void size() {
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
		System.out.print("The family has this many amoebas: " + family.size());
		assertEquals(13, family.size()); //should return 13
	}

	@Test
	public void height() {
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
		System.out.println("Here's the family height: " + family.height());
		assertEquals(5, family.height());
		
		AmoebaFamily family2 = new AmoebaFamily("Amos McCoy");
		family2.addChild("Amos McCoy", "mom/dad");
		family2.addChild("Amos McCoy", "auntie");
		family2.addChild("mom/dad", "me");
		
		family2.addChild("me", "Mike");
		family2.addChild("me", "Homer");
		family2.addChild("me", "Marge");
		family2.addChild("Mike", "Bart");
		family2.addChild("Mike", "Lisa");
		
		family2.addChild("Lisa", "Fred");
		family2.addChild("Lisa", "Wilma");
		
		family2.addChild("Marge", "Bill");
		family2.addChild("Marge", "Hilary");
		System.out.println("Here's the family height: " + family2.height());
		assertEquals(6, family2.height());
	}
	
}
