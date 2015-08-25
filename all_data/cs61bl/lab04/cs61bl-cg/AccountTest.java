import junit.framework.TestCase;


public class AccountTest extends TestCase {
	
	public void testInit() {
		Account acc = new Account(1000);
		assertTrue(acc.balance() == 1000);
	}
	
	// check that the result of supplying a negative number to deposit and 
	// withdraw doesn't change the account's balance
	public void testInvalidArgs() {
		Account acc = new Account(1000);
		acc.deposit(-400);
		assertTrue(acc.balance() == 1000);
		acc.withdraw(-1000);
		assertTrue(acc.balance() == 1000);
	}
	
	// check that an attempt to withdraw more money than the account
	// contains doesn't change the account's balance
	public void testOverdraft() {
		Account acc = new Account(1000);
		acc.withdraw(10000);
		assertTrue(acc.balance() == 1000);
	}
	
	// check that the account balance reflects the result of a legal call to deposit
	public void testDeposit() {
		Account acc = new Account(1000);
		acc.deposit(100);
		assertTrue(acc.balance() == 1100);
		
	}
	
	// check the account balance reflects the result of a legal call to withdraw
	public void testWithdraw() {
		Account acc = new Account(1000);
		acc.withdraw(500);
		assertTrue(acc.balance() == 500);
	}
    
}

