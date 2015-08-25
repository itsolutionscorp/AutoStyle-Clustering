import junit.framework.TestCase;

public class AccountTest extends TestCase {
	public void testInit() {
		Account testAccount = new Account(2000);
		assertTrue(testAccount.balance() == 2000);
	}

	public void testInvalidArgs() {
		Account testAccount = new Account(2000);
		testAccount.deposit(-500);
		assertTrue(testAccount.balance() == 2000);
		testAccount.withdraw(-500);
		assertTrue(testAccount.balance() == 2000);
	}

	public void testOverdraft() {
		Account testAccount = new Account(2000);
		testAccount.withdraw(5000);
		assertTrue(testAccount.balance() == 2000);
	}

	public void testDeposit() {
		Account testAccount = new Account(2000);
		testAccount.deposit(500);
		assertTrue(testAccount.balance() == 2500);
	}

	public void testWithdraw() {
		Account testAccount = new Account(2000);
		testAccount.withdraw(500);
		assertTrue(testAccount.balance() == 1500);
	}
}
