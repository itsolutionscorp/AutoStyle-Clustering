import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account a = new Account(100);
		assertTrue(a.balance() == 100);
	}
	public void testInvalidArgs() {
		Account a = new Account(100);
		a.withdraw(-10);
		assertTrue(a.balance() == 100);
	}
	
	public void testOverdraft() {
		Account a = new Account(100);
		a.withdraw(200);
		assertTrue(a.balance() == 100);
	}
	
	public void testDeposit() {
		Account a = new Account(100);
		a.deposit(10);
		assertTrue(a.balance() == 110);
	}
	
	public void testWithdraw() {
		Account a = new Account(100);
		a.withdraw(10);
		assertTrue(a.balance() == 90);
	}
}
