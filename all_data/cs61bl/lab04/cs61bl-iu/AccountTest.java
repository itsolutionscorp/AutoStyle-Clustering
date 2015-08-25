import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testConstructor() {
        Account c = new Account(2000);
        assertTrue (c.balance() == 2000);
	}
	
	public void testInvalidArgs(){
		Account c = new Account(2000);
		c.deposit(-100);
		assertTrue (c.balance() == 2000);
		c.withdraw(-100);
		assertTrue (c.balance() == 2000);
		
	}
	
	public void testOverdraft() {
		Account c = new Account(500);
		c.withdraw(c.balance()+500);
		assertTrue (c.balance() == 500);
		
	}
	
	public void testDeposit() {
		Account c = new Account(500);
		c.deposit(100);
		assertTrue (c.balance() == 600);
	}
	
	public void testWithdraw() {
		Account c = new Account(500);
		c.withdraw(100);
		assertTrue (c.balance() == 400);
	}
}



