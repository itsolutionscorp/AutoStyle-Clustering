import junit.framework.TestCase;


public class AmoebaTest extends TestCase {
	public void testHeightForZero(){
		AmoebaFamily nonexistent = new AmoebaFamily();
		System.out.println(nonexistent.height());
		assertTrue(nonexistent.height() == 0);
		
	}
	
	public void testHeightForOne(){
		AmoebaFamily leaf = new AmoebaFamily("Bob");
		System.out.println(leaf.height());
		assertTrue(leaf.height() == 1);
	}
	
	public void testHeightForLong(){
		AmoebaFamily longAssTree = new AmoebaFamily("Tom");
		longAssTree.addChild("Tom", "Bob");
		longAssTree.addChild("Bob", "Jerry");
		longAssTree.addChild("Bob", "Spongebob");
		longAssTree.addChild("Spongebob", "Patrick");
		longAssTree.addChild("Spongebob", "Gary");
		longAssTree.addChild("Spongebob", "Sandy");
		longAssTree.addChild("Spongebob", "Mr.Krabs");
		longAssTree.addChild("Mr.Krabs", "Squidward");
		longAssTree.addChild("Squidward", "Pearl");
		longAssTree.addChild("Pearl", "Thomas the Tank Engine");
		longAssTree.addChild("Thomas the Tank Engine", "Childish Gambino");
		
		assertTrue(longAssTree.height() == 8);
		
	}
	
	public void testFakeAssHeightMethod(){ //Makes the given method fail!
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
		assertTrue(family.longestName().equals("Amos McCoy"));
	}
	
	public void testSize(){
		AmoebaFamily longAssTree = new AmoebaFamily("Tom");
		longAssTree.addChild("Tom", "Bob");
		longAssTree.addChild("Bob", "Jerry");
		longAssTree.addChild("Bob", "Spongebob");
		longAssTree.addChild("Spongebob", "Patrick");
		longAssTree.addChild("Spongebob", "Gary");
		longAssTree.addChild("Spongebob", "Sandy");
		longAssTree.addChild("Spongebob", "Mr.Krabs");
		longAssTree.addChild("Mr.Krabs", "Squidward");
		longAssTree.addChild("Squidward", "Pearl");
		longAssTree.addChild("Pearl", "Thomas the Tank Engine");
		longAssTree.addChild("Thomas the Tank Engine", "Childish Gambino");
		
		assertTrue(longAssTree.size() == 12);
	}
	
	
	

}
