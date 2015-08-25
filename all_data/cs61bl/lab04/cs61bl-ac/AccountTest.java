import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
	 Account myAccount = new Account(100);
	 assertTrue (myAccount.balance() == 100);
	}
	
	public void testInvalidArgs() {
		Account myAccount = new Account(100);
		myAccount.deposit(-100);
		assertTrue("Cannot deposit negative funds", myAccount.balance() == 100);
		myAccount.withdraw(-100);
		assertTrue("Cannot withdraw negative funds", myAccount.balance() == 100);
	}
	
	 public void testOverdraft() {
		 Account myAccount = new Account(100);
		 myAccount.withdraw(150);
		 assertTrue(myAccount.balance() == 100);
	 }
	 
	public void testDeposit() {
		Account myAccount = new Account(100);
		myAccount.deposit(100);
		assertTrue(myAccount.balance() == 200);
	}

	public void testWithdraw() {
		Account myAccount = new Account(100);
		myAccount.withdraw(25);
		assertTrue(myAccount.balance() == 75);
	}
}
