import junit.framework.TestCase;


public class AccountTest extends TestCase {

	public void testInit() {
		Account A = new Account(100);
		assertTrue(A.balance() == 100); 
		
	}
	public void testInvalidArg() {
		Account A = new Account(100);
		A.deposit(-20);
		assertTrue(A.balance() == 100);
		A.withdraw(-20);
		assertTrue(A.balance() == 100);
		
	}
	public void testOverdraft() {
		Account A = new Account(100);
		A.withdraw(200);
		assertTrue(A.balance() == 100);
	}
	public void testDeposit() {
		Account A = new Account(100);
		A.deposit(50);
		assertTrue(A.balance() == 150);
	}
	public void testWithdraw() {
		Account A = new Account(100);
		A.withdraw(50);
		assertTrue(A.balance() == 50);
	}
}
