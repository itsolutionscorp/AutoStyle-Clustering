import junit.framework.TestCase;


public class AccountTest extends TestCase {
	
	public void testInit() {
		Account mike;
		mike = new Account(1000);
		assertTrue (mike.balance()  == 1000);
	}
	
	public void testInvalidArgs() {
		Account mike;
		mike = new Account(1000);
		mike.deposit(-200);
		assertTrue (mike.balance() == 1000);
		mike.withdraw(-200);
		assertTrue (mike.balance() == 1000);
	}
	
	public void testOverdraft() {
		Account mike;
		mike = new Account(1000);
		mike.withdraw(2000);
		assertTrue (mike.balance() == 1000);
	}
	
	public void testDeposit() {
		Account mike;
		mike = new Account(1000);
		mike.deposit(100);
		assertTrue (mike.balance() == 1100);
	}
	
	public void WithDraw() {
		Account mike;
		mike = new Account(1000);
		mike.withdraw(100);
		assertTrue (mike.balance() == 900);
	}

}
