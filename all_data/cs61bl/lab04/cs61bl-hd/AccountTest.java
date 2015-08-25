import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account a = new Account(200);
		assertTrue (a.balance() == 200);
	}
	
	public void testInvalidArgs() {
		Account a = new Account(200);
		a.deposit(-100);
		assertTrue (a.balance() == 200);
		a.withdraw(-100);
		assertTrue (a.balance() == 200);
	}
	
	public void testOverdraft() {
		Account a = new Account(200);
		a.withdraw(300);
		assertTrue (a.balance() == 200);
	}
	
	public void testDeposit() {
		Account a = new Account(200);
		a.deposit(100);
		assertTrue (a.balance() == 300);
	}
	
	public void testWithdraw() {
		Account a = new Account(200);
		a.withdraw(100);
		assertTrue (a.balance() == 100);
	}

}
