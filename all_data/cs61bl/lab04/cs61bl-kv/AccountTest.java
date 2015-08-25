import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account acct = new Account(1000);
		assertTrue (acct.balance()== 1000);
	}

	public void testInvalidArgs() {
		Account acct = new Account(1000);
		acct.deposit(-200);
		assertTrue (acct.balance()== 1000);
		acct.withdraw(-200);
		assertTrue (acct.balance()== 1000);
	}
	
	public void testOverdraft() {
		Account acct = new Account(1000);
		acct.withdraw(1200);
		assertTrue (acct.balance()== 1000);
	}
	
	public void testDeposit(){
		Account acct = new Account(1000);
		acct.deposit(200);
		assertTrue (acct.balance()== 1200);
	}
	
	public void testWithdraw() {
		Account acct = new Account(1000);
		acct.withdraw(200);
		assertTrue (acct.balance()== 800);
	}
	
}


