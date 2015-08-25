import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	
	@Test
	public void testHeight(){
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
		assertEquals(family.height(), 5);
		
		AmoebaFamily family1 = new AmoebaFamily("Beep");
		assertEquals(family1.height(), 1);
		
		AmoebaFamily family2 = new AmoebaFamily("CalBears");
		family2.addChild("CalBears", "University of California Berkeley");
		assertEquals(family2.height(), 2);
		
		AmoebaFamily family3 = new AmoebaFamily("NullTree");
		family3.myRoot = null;
		assertEquals(family3 .height(), 0);
	}
	
	
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
		assertEquals(family.size(), 13);
		
		AmoebaFamily family1 = new AmoebaFamily("Beep");
		assertEquals(family1.size(), 1);
		
		AmoebaFamily family2 = new AmoebaFamily("CalBears");
		family2.addChild("CalBears", "University of California Berkeley");
		assertEquals(family2.size(), 2);
		
		AmoebaFamily family3 = new AmoebaFamily("NullTree");
		family3.myRoot = null;
		assertEquals(family3 .size(), 0);
	}
	
	
	@Test
	public void testLowerCase() {
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
		family.makeNamesLowercase();
		assertEquals(family.myRoot.toString(), "amos mccoy");
		
		AmoebaFamily family1 = new AmoebaFamily("Lee");
		family1.makeNamesLowercase();
		assertEquals(family1.myRoot.toString(), "lee");
	}
	
	@Test
	public void testReplaceName(){
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
		family.replaceName("Amos McCoy", "Judy");
		assertEquals(family.myRoot.toString(), "Judy");
	}
	
	@Test
	public void testPrintFlat(){
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
		family.printFlat();
	}
	
	@Test
	public void testPrint(){
		AmoebaFamily family1 = new AmoebaFamily("BeepBoop");
		family1.addChild("BeepBoop", "me");
		family1.addChild("BeepBoop", "Fred");
		family1.addChild("Fred", "Gina");
		family1.print();
		
		AmoebaFamily family2 = new AmoebaFamily("CalBears");
		family2.addChild("CalBears", "University of California Berkeley");
		family2.print();
	}
	
	@Test 
	public void testLongestName(){
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
		assertEquals(family.longestNameLength(), 10);
		
		AmoebaFamily family1 = new AmoebaFamily(" ");
		assertEquals(family1.longestNameLength(), 1);
		
		AmoebaFamily family2 = new AmoebaFamily("CalBears");
		family2.addChild("CalBears", "University of California Berkeley");
		assertEquals(family2.longestNameLength(), 33);
	}
	
	@Test
	public void testLongestNameString(){
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
		assertEquals(family.longestName(), "Amos McCoy");
		
		AmoebaFamily family1 = new AmoebaFamily(" ");
		assertEquals(family1.longestName(), " ");
		
		AmoebaFamily family2 = new AmoebaFamily("CalBears");
		family2.addChild("CalBears", "University of California Berkeley");
		assertEquals(family2.longestName(), "University of California Berkeley");
	}

}
