import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account account = new Account(400);
		assertTrue(account.balance() == 400);
	}

	public void testIvalidArgs() {
		Account account = new Account(400);
		account.withdraw(-100);
		assertTrue(account.balance() == 400);
		account.deposit(-100);
		assertTrue(account.balance() == 400);
	}

	public void testOverdraft() {
		Account account = new Account(400);
		account.withdraw(500);
		assertTrue(account.balance() == 400);
	}

	public void testDeposit() {
		Account account = new Account(400);
		account.deposit(100);
		assertTrue(account.balance() == 500);
	}

	public void testWithdraw() {
		Account account = new Account(400);
		account.withdraw(100);
		assertTrue(account.balance() == 300);
		
	}
	
}
