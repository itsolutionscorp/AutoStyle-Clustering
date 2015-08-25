import junit.framework.TestCase;


public class AccountTest extends TestCase {
	
	public void testInit() {
		Account a = new Account(1000);
		assertTrue(a.balance() == 1000);
	}	
	public void testInvalidArgs() {
		Account a = new Account(1000);
		a.deposit(-500);
		assertTrue(a.balance() == 1000);
		a.withdraw(-500);
		assertTrue(a.balance() == 1000);
	}
	
	public void testOverdraft() {
		Account a = new Account(1000);
		a.withdraw(2000);
		assertTrue(a.balance() == 1000);
	}
	
	public void testDeposit() {
		Account a = new Account(1000);
		a.deposit(500);
		assertTrue(a.balance() == 1500);
	}
	
	public void testWithdraw () {
		Account a = new Account(1000);
		a.withdraw(500);
		assertTrue(a.balance() == 500);
	}

}
