import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account bob;
		bob = new Account(100);
		assertTrue (bob.balance() == 100);
	}
	
	public void testInvalidArgs() {
		Account bob;
		bob = new Account(100);
		bob.withdraw(-100);
		assertTrue(bob.balance() == 100);
		bob.deposit(-100);
		assertTrue(bob.balance() == 100);
	}
	
	public void testOverdraft() {
		Account bob;
		bob = new Account(100);
		bob.withdraw(1000);
		assertTrue(bob.balance() == 100);
	}
	
	public void testDeposit() {
		Account bob;
		bob = new Account(100);
		bob.deposit(100);
		assertTrue(bob.balance() == 200);
	}
	
	public void testWithdraw() {
		Account bob;
		bob = new Account(100);
		bob.withdraw(100);
		assertTrue(bob.balance() == 0);
	}
}
