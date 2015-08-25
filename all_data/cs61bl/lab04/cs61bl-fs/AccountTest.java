import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		int balance = 1000;
		Account testAccount = new Account(balance);
		assertEquals(balance, testAccount.balance());
	}
	public void testInvalidArgs() {
		Account testAccount = new Account(1000);
		testAccount.withdraw(-100);
		assertEquals(testAccount.balance(), 1000);
	}
	public void testOverdraft() {
		Account testAccount = new Account(1000);
		testAccount.withdraw(2000);
		assertEquals(testAccount.balance(), 1000);
	}
	public void testDeposit() {
		Account testAccount = new Account(1000);
		testAccount.deposit(20);
		assertEquals(testAccount.balance(), 1020);
	}
	public void testWithdraw() {
		Account testAccount = new Account(1000);
		testAccount.withdraw(20);
		assertEquals(testAccount.balance(), 980);
	}
	
}
