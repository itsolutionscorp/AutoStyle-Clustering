import junit.framework.TestCase;


public class AmoebaFamilyTest extends TestCase {
	
	AmoebaFamily onePerson;
	AmoebaFamily fourPerson;
	AmoebaFamily thirteenPerson;
	
	private void init () {
		onePerson = new AmoebaFamily("The Amoeba");
		fourPerson = new AmoebaFamily("The Amoeba");
		fourPerson.addChild("The Amoeba", "apple");
		fourPerson.addChild("The Amoeba", "ReallyLongNameAmoeba");
		fourPerson.addChild("The Amoeba", "Candy");
		thirteenPerson = new AmoebaFamily("The Amoeba");
		thirteenPerson.addChild("The Amoeba", "apple");
		thirteenPerson.addChild("The Amoeba", "ReallyLongNameAmoeba");
		thirteenPerson.addChild("The Amoeba", "Candy");
		thirteenPerson.addChild("apple", "a");
		thirteenPerson.addChild("apple", "b");
		thirteenPerson.addChild("apple", "c");
		thirteenPerson.addChild("ReallyLongNameAmoeba", "EvenLongerNamedAmoeba");
		thirteenPerson.addChild("ReallyLongNameAmoeba", "EvenLongerNamedThanEvenLongerNamedAmoeba");
		thirteenPerson.addChild("ReallyLongNameAmoeba", "ShortName");
		thirteenPerson.addChild("Candy", "chocolate");
		thirteenPerson.addChild("Candy", "lemondrops");
		thirteenPerson.addChild("Candy", "gobstoppers");
	}
	
	public void testSize() {
		init();
		assertTrue(onePerson.size() == 1);
		assertTrue(fourPerson.size() == 4);
		assertTrue(thirteenPerson.size() == 13);
	}
	
	public void testLongestName() {
		init();
		assertTrue(onePerson.longestName().equals("The Amoeba"));
		assertTrue(fourPerson.longestName().equals("ReallyLongNameAmoeba"));
		System.out.println(thirteenPerson.longestName());
		thirteenPerson.print();
		System.out.println(thirteenPerson.longestNameLength());
		assertTrue(thirteenPerson.longestName().equals("EvenLongerNamedThanEvenLongerNamedAmoeba"));
	}
	
	public void testReplaceName() {
		init();
		thirteenPerson.replaceName("a", "apple2");
		thirteenPerson.print();
	}
	
	public void testHeight() {
		init();
		assertTrue(onePerson.height() == 1);
		assertTrue(fourPerson.height() == 2);
		assertTrue(thirteenPerson.height() == 3);
	}
}
