import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account bob = new Account(500);
		assertTrue(bob.balance() == 500);
	}
	
	public void testInvalidArgs() {
		Account bob = new Account(500);
		bob.withdraw(-500);
		assertTrue(bob.balance() == 500);
		bob.deposit(-500);
		assertTrue(bob.balance() == 500);
	}
	
	public void testOverdraft() {
		Account bob = new Account(500);
		bob.withdraw(600);
		assertTrue(bob.balance() == 500);
	}
	
	public void testDeposit() {
		Account bob = new Account(500);
		bob.deposit(1);
		assertTrue(bob.balance() == 501);
	}
	
	public void testWithdraw() {
		Account bob = new Account(500);
		bob.withdraw(100);
		assertTrue(bob.balance() == 400);
	}

}
