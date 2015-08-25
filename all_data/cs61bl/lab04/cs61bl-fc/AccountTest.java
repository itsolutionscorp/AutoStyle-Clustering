import junit.framework.TestCase;

public class AccountTest extends TestCase {
	public void testInit() {
		Account mike = new Account(100);
		assertTrue(mike.balance() == 100);
	}
	
	public void testInvalidArgs() {
		Account mike = new Account(100);
		mike.withdraw(-100);
		assertTrue(mike.balance() == 100);
		mike.deposit(-100);
		assertTrue(mike.balance() == 100);
	}
	
	public void testOverdraft() {
		Account mike = new Account(100);
		mike.withdraw(1000);
		assertTrue(mike.balance() == 100);
		Account bob = new Account(1, mike);
		bob.withdraw(102);
		assertTrue(bob.balance() == 1);
		assertTrue(mike.balance() == 100);
	}
	
	public void testDeposit() {
		Account mike = new Account(100);
		mike.deposit(300);
		assertTrue(mike.balance() == 400);
	}
	
	public void testWithdraw() {
		Account mike = new Account(100);
		mike.withdraw(50);
		assertTrue(mike.balance() == 50);
		Account bob = new Account(49, mike);
		bob.withdraw(50);
		assertTrue(bob.balance() == 0);
		assertTrue(mike.balance() == 49);
	}
}
