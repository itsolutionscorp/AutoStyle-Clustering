import junit.framework.TestCase;
//import org.junit.Test;

public class AccountTest extends TestCase {
	
	//@Test(expected = IllegalArgumentException.class)
	public void testInit(){
		Account test = new Account(100);
		assertTrue (test.balance() == 100);
		
	}
	
	public void testInvalidArgs(){
		Account test = new Account(100);
		test.deposit(-10);		
		assertTrue (test.balance() == 100);
		
		test.withdraw(-10);
		assertTrue (test.balance() == 100);		
	}
	
	public void testOverdraft(){
		Account test_parent = new Account(200);
		Account test = new Account(100, test_parent);		
		test.withdraw(400);
		
		assertTrue (test.balance() == 100);
		assertTrue (test_parent.balance() == 200);
	}
	
	public void testDeposit(){
		Account test = new Account(500);		
		test.deposit(200);
		
		assertEquals(test.balance(), 700);		
		
	}
	
	
	public void testWithdraw(){
		Account test = new Account(1000);
		
		assertTrue(test.withdraw(400)==true);
		assertTrue(test.withdraw(1000)==false);
	}
	
}
