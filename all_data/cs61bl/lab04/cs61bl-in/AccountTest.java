package lab04;

import junit.framework.TestCase;

public class AccountTest extends TestCase {
	public void testInit() {
		Account mike = new Account(1000);
		assertTrue (mike.balance() == 1000);
	}
	
	public void testInvalidArgs() {
		Account mike = new Account(500);
		assertFalse (mike.withdraw(-1000));
		assertTrue (mike.balance() == 500);
		mike.deposit(-500);
		assertTrue (mike.balance() == 500);
	}
	
	public void testOverdraft() {
		Account mike = new Account(500);
		mike.withdraw(1000);
		assertTrue (mike.balance() == 500);
	}
	
	public void testDeposit() {
		Account mike = new Account(500);
		mike.deposit(500);
		assertTrue (mike.balance() == 1000);
	}
	
	public void testWithdraw() {
		Account mike = new Account(500);
		mike.withdraw(200);
		assertTrue (mike.balance() == 300);
		Account john = new Account(100,mike);
		john.withdraw(200);
		assertTrue (john.balance() == 0);
		assertTrue (mike.balance() == 200);
	}

}
