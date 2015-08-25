import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account philip = new Account(500);
		assertTrue(philip.balance() == 500);
	}
	
	public void testInvalidArgs() {
		Account philip = new Account(500);
		philip.withdraw(-50);
		assertTrue(philip.balance() == 500);
		philip.deposit(-50);
		assertTrue(philip.balance() == 500);
	}
	
	public void testOverdraft() {
		Account philip = new Account(100);
		philip.withdraw(-150);
		assertTrue(philip.balance() == 100);
	}

	public void testDeposit() {
		Account philip = new Account(500);
		philip.deposit(150);
		assertTrue(philip.balance() == 650);
	}
	
	public void testWithdraw() {
		Account philip = new Account(500);
		philip.withdraw(150);
		assertTrue(philip.balance() == 350);
	}
}
