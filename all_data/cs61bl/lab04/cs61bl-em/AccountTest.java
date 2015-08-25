import junit.framework.TestCase;

public class AccountTest extends TestCase {

	public void testInit () {
		Account a = new Account(50);
	     assertTrue (a.balance() == 50);
	}
	
	public void testInvalidArgs() {
		Account a = new Account(50);
		a.deposit(-10);
		assertTrue (a.balance() == 50);
		a.withdraw(-10);
		assertTrue (a.balance() == 50);
	}
	
	public void testOverdraft() {
		Account a = new Account(50);
		a.withdraw(51);
		assertTrue (a.balance() == 50);
	}
	
	public void testDeposit() {
		Account a = new Account(50);
		a.deposit(28);
		assertTrue (a.balance() == 78);
	}
	
	public void testWithdraw() {
		Account a = new Account(50);
		a.withdraw(12);
		assertTrue (a.balance() == 38);
		
	}
	
	
}
