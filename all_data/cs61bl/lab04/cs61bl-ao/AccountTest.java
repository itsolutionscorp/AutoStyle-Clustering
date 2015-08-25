import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account acct1 = new Account(500);
		assertTrue (acct1.balance() == 500);
	}
	public void testInvalidArgs() {
		Account acct2 = new Account(1000);
		acct2.deposit(-50);
		assertTrue (acct2.balance() == 1000);
		acct2.withdraw(-100);
		assertTrue (acct2.balance() == 1000);
	}
	public void testOverdraft() {
		Account acct3 = new Account(500);
		acct3.withdraw(1000);
		assertTrue (acct3.balance() == 500);
	}
	public void testDeposit() {
		Account acct4 = new Account(1000);
		acct4.deposit(1000);
		assertTrue (acct4.balance() == 2000);
	}
	public void testWithdraw() {
		Account acct5 = new Account(1000);
		acct5.withdraw(500);
		assertTrue (acct5.balance() == 500);
	}
}
