import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
	public void LowerCasetest() {
		AmoebaFamily family = new AmoebaFamily("me");
		family.addChild("me", "Mike");
		family.addChild("me", "Homer");
		family.addChild("me", "Marge");
		family.addChild("Mike", "Bart");
		family.addChild("Mike", "Lisa");
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hilary");
		family.makeNamesLowercase();
		family.print();
		System.out.println("---------------------------");
		
		}
	
	@Test
	public void ReplaceNametest() {
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
		family.replaceName("Mike", "Joanna");
		family.replaceName("Homer", "Stephie");
		family.replaceName("auntie", "uncle");
		family.replaceName("Amos McCoy", "Brian");
		family.print();
		System.out.println("---------------------------");
	}
	@Test
	public void printFlatTest(){
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
		System.out.println("---------------------------");
	}
	@Test
	public void printTest(){
		System.out.println("printtest---------------------------");
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
		System.out.println("---------------------------");
	}
	@Test
	public void heightTest(){
		//3 tests for height
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
		assertEquals(5, family.height());
		
		AmoebaFamily family2 = new AmoebaFamily("Amos McCoy");   //leaf
		assertEquals(1, family2.height());
		
		AmoebaFamily family3 = new AmoebaFamily("Amos McCoy");
		family3.addChild("Amos McCoy", "mom/dad");
		family3.addChild("Amos McCoy", "auntie");
		family3.addChild("mom/dad", "me");
		family3.addChild("mom/dad", "Fred");
		family3.addChild("mom/dad", "Wilma");
		family3.addChild("Fred", "Samantha");
		family3.addChild("Samantha", "Josh");
		family3.addChild("Samantha", "Tyler");
		family3.addChild("Samantha", "Guilliene");
		family3.addChild("Guilliene", "Amanda");
		family3.addChild("Josh", "Cynthia");
		family3.addChild("Cynthia", "Callie");
		family3.addChild("Wilma", "Aiden");
		assertEquals(7, family3.height());
	}
	@Test
	public void longestNameTest(){
		AmoebaFamily family = new AmoebaFamily("me");
		family.addChild("me", "Mike");
		family.addChild("me", "Homer");
		family.addChild("me", "Marge");
		family.addChild("Mike", "Bart");
		family.addChild("Mike", "Lisa");
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hilary");
		assertEquals("Hilary", family.longestName());
		AmoebaFamily family2 = new AmoebaFamily("Amos McCoy");
		family2.addChild("Amos McCoy", "mom/dad");
		family2.addChild("Amos McCoy", "auntie");
		family2.addChild("mom/dad", "me");
		family2.addChild("mom/dad", "Fred");
		family2.addChild("mom/dad", "Wilma");
		assertEquals("Amos McCoy", family2.longestName());
		AmoebaFamily family3 = new AmoebaFamily("me");
		family3.addChild("me", "Mike");
		family3.addChild("me", "Homer");
		family3.addChild("me", "Marge");
		family3.addChild("Mike", "Bart");
		family3.addChild("Mike", "Lisa");
		family3.addChild("Marge", "Bill");
		family3.addChild("Marge", "Hilary");
		family3.replaceName("Marge", "Looonnngggname");
		assertEquals("Looonnngggname", family3.longestName());
	}
	@Test
	public void size(){
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
	}
	}


