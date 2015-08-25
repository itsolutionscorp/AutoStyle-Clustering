import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account grandparent = new Account(500);
		Account parent = new Account(500, grandparent);
		Account mike = new Account(1000, parent);
		assertTrue(mike.balance() == 1000);
		assertTrue(grandparent.balance() == 500);
		
	}
	
	public void testInvalidArgs() {
		Account mike = new Account(1000);
		int Bal = mike.balance();
		mike.deposit(-5);
		assertTrue(Bal == mike.balance());
		mike.withdraw(-5);
		assertTrue(Bal == mike.balance());
	}
	
	public void testOverdraft() {
		Account parent = new Account(500);
		Account mike = new Account(1000, parent);
		mike.withdraw(1600);
		assertTrue(mike.balance() == 1000);
		assertTrue(parent.balance() == 500);
	}
	
	public void testDeposit() {
		Account mike = new Account(1000);
		mike.deposit(100);
		assertTrue(mike.balance() == 1100);
	}
	public void testWithdraw() {
		Account mike = new Account(1000);
		mike.withdraw(100);
		assertTrue(mike.balance() == 900);
	}
}
