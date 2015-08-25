import junit.framework.TestCase;


public class AccountTest extends TestCase {
	
	public void testInit(){
		Account jeffrey = new Account(9000);
		assertEquals (9000, jeffrey.balance());
	}
	
	public void testInvalidArgs() {
		Account jeffrey = new Account(9000);
		jeffrey.deposit(-1000);
		assertEquals (9000, jeffrey.balance());
		Account david = new Account(9000);
		david.withdraw(-1000);
		assertEquals (9000, david.balance());
	}
	
	public void testOverdraft(){
		Account jeffrey = new Account(9000);
		jeffrey.withdraw(10000);
		assertEquals (9000, jeffrey.balance());
	}
	
	public void testDeposit(){
		Account jeffrey = new Account(9000);
		jeffrey.deposit(1000);
		assertEquals (10000, jeffrey.balance());
	}
	
	public void testWithdraw(){
		Account jeffrey = new Account(9000);
		jeffrey.withdraw(1000);
		assertEquals (8000, jeffrey.balance());
	}
}