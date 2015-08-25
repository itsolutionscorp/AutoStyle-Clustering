import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
	public void testMakeNamesLowercase() {
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
		//family.print();
	}

	@Test
	public void testReplaceName() {
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
		family.replaceName("Amos McCoy", "AMOOOSSSSSSS MCCOOOOYYYY");
		family.replaceName("mom/dad", "MOMMMYYYY DADDDDYYYYYY");
		family.replaceName("me", "MEE EM EME MEEMEMEE EMEMEMEE");
		family.replaceName("Mike", "MIKEEEYYY MAHH BOYYY");
		family.replaceName("Homer", "HOOMEEER HOME BOY HOMERRRRR");
		family.replaceName("Marge", "MARGE MERGE MARGE MERGE");
		family.replaceName("Bill", "BILLY BOY BILLY BOY BILLLYYY");
		family.replaceName("Hilary", "HILLLRRYYYYY");
		//family.print();
	}

	@Test
	public void testLongestName() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "123456789012345");
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
		assertEquals(family.longestName(), "123456789012345");
	}

	@Test
	public void testSize() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "123456789012345");
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
		family.addChild("Marge", "d");
		family.addChild("Marge", "c");
		family.addChild("Marge", "e");
		family.addChild("Marge", "g");
		assertEquals(family.size(), 17);
	}

	@Test
	public void testHeight() {		
		AmoebaFamily oneFam = new AmoebaFamily("onemanstanding");
		assertEquals(oneFam.height(), 0);
		
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "123456789012345");
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
		family.addChild("Hilary", "d");
		family.addChild("Marge", "c");
		family.addChild("Marge", "e");
		family.addChild("Marge", "g");
		//family.print();
		assertEquals(family.height(), 5);
	}

	@Test
	public void testIterator() {
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
		family.print();
		
		Iterator iter = family.iterator();
		while (iter.hasNext())
			System.out.println(iter.next().toString());
	}
}
