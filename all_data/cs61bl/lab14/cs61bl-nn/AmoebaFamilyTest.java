import junit.framework.TestCase;


public class AmoebaFamilyTest extends TestCase {
	AmoebaFamily fam1 =new AmoebaFamily("one");
	AmoebaFamily fam2 =new AmoebaFamily("one member");
	AmoebaFamily family = new AmoebaFamily("Amos McCoy");
	
public void testHeight(){
		
		/**
		 * height() function will fail for the buggy version because this line:
		 *     bestSoFar = 1 + Math.max(a.height(), bestSoFar);
		 *     return bestSoFar;
		 * it adds extra height into the total value
		 * we chang it into this version:
		 *     bestSoFar = Math.max(a.height(), bestSoFar);
		 *     return bestSoFar+1;
		 *Then it passes all the test
		 * 
		 */
		fam1.addChild("one", "two1");
		fam1.addChild("one", "two2");
		fam1.addChild("one", "two3");
		fam1.addChild("one", "two4");
		fam1.addChild("two4", "three1");
		fam1.addChild("two1", "three2");
		fam1.addChild("three1", "four1");		
		assertTrue(fam1.height()==4);
		//edge case, only one member in the family
		assertTrue(fam2.height()==1);
		
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
		assertTrue(family.height()==5);

}

public void testSize(){
	fam1.addChild("one", "two1");
	fam1.addChild("one", "two2");
	fam1.addChild("one", "two3");
	fam1.addChild("one", "two4");
	fam1.addChild("two4", "three1");
	fam1.addChild("two1", "three2");
	fam1.addChild("three1", "four1");		
	assertTrue(fam1.size()==8);
}

}


