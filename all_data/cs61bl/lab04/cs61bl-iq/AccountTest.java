import junit.framework.TestCase;


public class AccountTest extends TestCase {

	public void testInit() {
		Account newAccount = new Account(400);
		assertTrue (newAccount.balance() == 400);
	}
	
	public void testIvalidArgs() {
		Account newAccount = new Account(400);
		newAccount.deposit(-5);
		assertTrue (newAccount.balance() == 400);
		newAccount.withdraw(-5);
		assertTrue (newAccount.balance() == 400);
	}
	
	public void testOverdraft() {
		Account newAccount = new Account(400);
		newAccount.withdraw(500);
		assertTrue (newAccount.balance() == 400);
	}

	public void testDeposit() {
		Account newAccount = new Account(400);
		newAccount.deposit(300);
		assertTrue (newAccount.balance() == 700);
	}

	public void testWithdraw() {
		Account newAccount = new Account(400);
		newAccount.withdraw(300);
		assertTrue (newAccount.balance() == 100);
	}
}
