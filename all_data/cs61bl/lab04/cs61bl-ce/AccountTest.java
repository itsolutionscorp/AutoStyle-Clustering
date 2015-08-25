import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account bob = new Account(1000);
		assertTrue (bob.balance() == 1000);
	}
	
	public void testInvalidArgs() {
		//can't withdraw negative amount
		Account bob = new Account(1000);
		bob.withdraw(-100);
		assertTrue (bob.balance() == 1000);
		
	}
	
	public void testOverdraft() {
		//make sure can't withdraw more than balance
		Account bob = new Account(1000);
		bob.withdraw(2000);
		assertTrue (bob.balance() == 1000);
	}
	
	public void testDeposit() {
		//make sure deposit works
		Account bob = new Account(1000);
		bob.deposit(200);
		assertTrue (bob.balance() == 1200);
	}
	
	public void testWithdraw() {
		//make sure withdraw works
		Account bob = new Account(1000);
		bob.withdraw(800);
		assertTrue (bob.balance() == 200);
	}

}
