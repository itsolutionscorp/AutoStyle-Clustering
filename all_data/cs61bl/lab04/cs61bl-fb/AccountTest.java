import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account mike = new Account(300);
		assertTrue (mike.balance() == 300);
	}
	
	public void testInvalidArgs() {
		Account mike = new Account(300);
		mike.withdraw(-1);
		assertTrue (mike.balance() == 300);
		mike.deposit(-1);
		assertTrue (mike.balance() == 300);
	
	}
	
	public void testOverdraft() {
		Account mike = new Account(300);
		mike.withdraw(400);
		assertTrue (mike.balance() == 300);
	}
	
	public void testDeposit() {
		Account mike = new Account(300);
		mike.deposit(100);
		assertTrue (mike.balance() == 400);
	}
	
	public void testWithdraw() {
		Account mike = new Account(300);
		mike.withdraw(100);
		assertTrue (mike.balance() == 200);
		
	}
	

}
