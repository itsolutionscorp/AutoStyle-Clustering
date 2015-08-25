import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit(){
		Account testAcct = new Account(3000);
		assertTrue (testAcct.balance() == 3000);
	}
	
	public void testInvalidArgs() {
		Account testAcct = new Account(3000);
		testAcct.deposit(-1000);
		testAcct.withdraw(-50);
		assertTrue (testAcct.balance() == 3000);
	}
	
	public void testOverdraft() {
		Account testAcct = new Account(3000);
		testAcct.withdraw(4000);
		assertTrue (testAcct.balance() == 3000);
	}
	
	public void testDeposit() {
		Account testAcct = new Account(3000);
		testAcct.deposit(1000);
		assertTrue (testAcct.balance() == 4000);
	}
	
	public void testWithdraw() {
		Account testAcct = new Account(3000);
		testAcct.withdraw(1000);
		assertTrue (testAcct.balance() == 2000);
	}
	
}