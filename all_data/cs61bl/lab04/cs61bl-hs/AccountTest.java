import junit.framework.TestCase;


public class AccountTest extends TestCase {

	public void testInit() {
		Account alan = new Account(500);
		assertTrue (alan.balance() == 500);
	}
	
	public void testInvalidArgs() {
		Account alan = new Account(500);
		alan.deposit(-100);
		assertTrue(alan.balance() == 500);
		alan.withdraw(-100);
		assertTrue(alan.balance() == 500);
	}
	
	public void testOverdraft() {
		Account alan = new Account(500);
		alan.withdraw(1000);
		assertTrue(alan.balance() == 500);
	}
	
	public void testDeposit() {
		Account alan = new Account(500);
		alan.deposit(500);
		assertTrue(alan.balance() == 1000);
	}
	
	public void testWithdraw() {
		Account alan = new Account(500);
		alan.withdraw(200);
		assertTrue(alan.balance() == 300);
	}
}
