import junit.framework.TestCase;


public class AccountTest extends TestCase {

	public void testInit() {
		Account acct= new Account(500);
		assertTrue (acct.balance() == 500);
	}
	
	public void testInvalidArgs() {
		
		Account acct= new Account(500);
		acct.withdraw(-100);
		assertTrue (acct.balance() == 500);
		acct.deposit(-100);
		assertTrue (acct.balance() == 500);
		
	}
	public void testOverdraft(){
		Account acct= new Account(500);
		acct.withdraw(600);
		assertTrue (acct.balance() == 500);
		
	}
	
	public void testDeposit(){
		Account acct= new Account(500);
		acct.deposit(100);
		assertTrue (acct.balance() == 600);
	}
	
	public void testWithdraw(){
		Account acct= new Account(500);
		acct.withdraw(100);
		assertTrue (acct.balance() == 400);
		
	}
}
