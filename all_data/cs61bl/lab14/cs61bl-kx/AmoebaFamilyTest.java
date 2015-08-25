import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
	public void testHeight() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy"); //case 1
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
		//System.out.println(family.height());
		assertTrue(family.height() == 5);
		
	}
	public void testHeight2(){ //case 2
		AmoebaFamily family = new AmoebaFamily("Ancestor");
		family.addChild("Ancestor", "monkey");
		family.addChild("Ancestor", "gorilla");
		family.addChild("gorilla", "hamster");
		family.addChild("hamster", "human");
		assertTrue(family.height()==3);
	}

}
