import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	
	@Test
	public void testHeight1() {
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
		assertTrue(family.height() == 5);
		
	}
	
	
	@Test
	public void testHeight2(){
		AmoebaFamily family = new AmoebaFamily("G1");
		family.addChild("G1", "G2A");
		family.addChild("G1", "G2B");
		family.addChild("G2A", "G3A");
		family.addChild("G2A", "G3B");
		assertTrue(family.height() == 3);
	}
	
	@Test
	public void testHeight3(){
		AmoebaFamily family = new AmoebaFamily("G1");
		family.addChild("G1", "G2A");
		family.addChild("G1", "G2B");
		family.addChild("G2A", "G3A");
		family.addChild("G3A", "G4A");
		family.addChild("G3A", "G4B");
		family.addChild("G4A", "G5A");
		family.addChild("G4A", "G5B");
		family.addChild("G5A", "G6A");
		System.out.println(family.height());
		assertTrue(family.height() == 6);
			
	}
	
	
}
