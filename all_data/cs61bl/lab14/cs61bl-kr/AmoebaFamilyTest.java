import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	
	public void test() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		family.addChild("Amos McCoy", "MOM/dad");
		family.addChild("Amos McCoy", "AUNTIE");
		family.addChild("MOM/dad", "me");
		family.addChild("MOM/dad", "Fred");
		family.addChild("MOM/dad", "Wilma");
		family.addChild("me", "Mike");
		family.addChild("me", "Homer");
		family.addChild("me", "Marge");
		family.addChild("Mike", "Bart");
		family.addChild("Mike", "Lisa");
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hilary");
		family.addChild("Marge", "Hilaryy");
		System.out.println("Here's the family:");
		
		family.makeNamesLowercase();
		family.print();
		System.out.println(family.size());
		System.out.println(family.longestNameLength());
		System.out.println(family.longestName());
		
	}
	@Test
	public void testheight() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		family.addChild("Amos McCoy", "MOM/dad");
		family.addChild("Amos McCoy", "AUNTIE");
		family.addChild("MOM/dad", "me");
		family.addChild("MOM/dad", "Fred");
		family.addChild("MOM/dad", "Wilma");
		family.addChild("me", "Mike");
		family.addChild("me", "Homer");
		family.addChild("me", "Marge");
//		family.addChild("Mike", "Bart");
//		family.addChild("Mike", "Lisa");
		
		System.out.println(family.height());
		
	}

}
