import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;


public class AccountTest extends TestCase {

	@Test
	public void testInit() {
		Account test_account = new Account(0);
		assertEquals(test_account.balance(), 0);	
	}
	
	public void testInvalidArgs(){
		Account test_account2 = new Account(10);
		test_account2.deposit(-10);
		assertEquals(test_account2.balance(), 10);
		test_account2.withdraw(-10);
		assertTrue(test_account2.balance() == 10);
	}
	
	public void testOverdraft(){
		Account test_account3 = new Account(5);
		test_account3.withdraw(10);
		assertTrue(test_account3.balance() == 5);
	}

	public void testDeposit(){
		Account test_account4 = new Account(10);
		test_account4.deposit(10);
		assertEquals(test_account4.balance(), 20);
	}
	
	public void testWithdraw(){
		Account test_account5 = new Account(10);
		test_account5.withdraw(10);
		assertEquals(test_account5.balance(), 0);
	}
} 
