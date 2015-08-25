import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account a = new Account(500);
		assertTrue (a.balance() == 500);
		
	}
	
	public void testInvalidArgs(){
		Account a = new Account (500);
		a.withdraw(-10);
		assertTrue (a.balance() == 500);
		a.deposit(-20);
		assertTrue (a.balance() == 500);
	}
	public void testOverdraft() {
		Account a = new Account(100);
		a.withdraw(200);
		assertTrue(a.balance()== 100);
	}
	
	public void testDeposit(){
		Account a = new Account (500);
		a.deposit(10);
		assertTrue(a.balance() == 510);
	}
	
	public void testWithdraw() {
		Account a = new Account(200);
		a.withdraw(100);
		assertTrue(a.balance()== 100);
	}

}
