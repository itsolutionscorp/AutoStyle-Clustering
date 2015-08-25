import junit.framework.TestCase;


public class AccountTest extends TestCase {

	public void testInit() {
		Account a = new Account(2000);
		assertTrue(a.balance() == 2000);
	}
	
	public void testInvalidArgs() {
		Account a = new Account(2000);
		a.withdraw(-200);
		assertTrue(a.balance() == 2000);
		a.deposit(-200);
		assertTrue(a.balance() == 2000);
	}
	
	public void testOverdraft() {
		Account a = new Account(2000);
		a.withdraw(5000);
		assertTrue(a.balance() == 2000);
	}
	 
	public void testDeposit() {
		Account a = new Account(2000);
		a.deposit(5000);
		assertTrue(a.balance() == 7000);
	}
	
	public void testWithdraw() {
		Account a = new Account(2000);
		a.withdraw(1000);
		assertTrue(a.balance() == 1000);
	}
	
	
}
