import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account testAccount = new Account(5000);
		assertEquals(5000,testAccount.balance());
		
	}
	
	public void testInvalidArgs() {
		Account testAccount = new Account(500);
		assertTrue(testAccount.balance() == 500);
		testAccount.deposit(-500);
		assertTrue(testAccount.balance() == 500);
		
	}
	
	public void testOverdraft() {
		Account testAccount = new Account(500);
		testAccount.withdraw(1000);
		assertTrue(testAccount.balance() == 500);
	}
	
	public void testDeposits() {
		Account testAccount = new Account(500);
		testAccount.deposit(500);
		assertTrue(testAccount.balance() == 1000);
	}
	
	public void testWithdraw() {
		Account testAccount = new Account(500);
		testAccount.withdraw(250);
		assertTrue(testAccount.balance() == 250);
	}
}
