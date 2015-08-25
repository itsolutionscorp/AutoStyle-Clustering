import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account myAccount = new Account(100);
		assertTrue(myAccount.balance() == 100);
	}
	public void testInvalidArgs() {
		Account myAccount = new Account(100);
		myAccount.deposit(-100);
		assertTrue(myAccount.balance() == 100);
		myAccount.withdraw(-100);
		assertTrue(myAccount.balance() == 100);
	}
	public void testOverdraft() {
		Account myAccount = new Account(100);
		myAccount.withdraw(200);
		assertTrue(myAccount.balance() == 100);
	}
	public void testDeposit() {
		Account myAccount = new Account(100);
		myAccount.deposit(1000);
		assertTrue(myAccount.balance() == 1100);
	}
	public void testWithdraw() {
		Account myAccount = new Account(100);
		myAccount.withdraw(50);
		assertTrue(myAccount.balance() == 50);
	}
}
