import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit(){
		Account c =new Account(500);
		assertTrue (c.balance() == 500);	
	}
	
	public void testInvalidArgs(){
		Account c =new Account(500);
		c.deposit(-100);
		assertTrue (c.balance() == 500);
		c.withdraw(-100);
		assertTrue (c.balance() == 500);
	}
	
	public void testOverdraft(){
		Account c = new Account(500);
		c.withdraw(1000);
		assertTrue (c.balance() == 500);
	}
	
	public void testDeposit(){
		Account c = new Account(500);
		c.deposit(500);
		assertTrue (c.balance() == 1000);
	}
	
	public void testWithdraw(){
		Account c = new Account(500);
		c.withdraw(100);
		assertTrue (c.balance() == 400);
	}
}
