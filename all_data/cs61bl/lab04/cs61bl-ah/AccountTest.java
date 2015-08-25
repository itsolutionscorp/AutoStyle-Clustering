import junit.framework.TestCase;


public class AccountTest extends TestCase {

	public void testInit() {
		Account Jake = new Account(2000);
		Account Sarah = new Account(1000, Jake);
		assertTrue(Sarah.balance() == 1000);
		assertTrue(Sarah.parentAccount == Jake);
		assertTrue(Jake.balance() == 2000);
	}
	
	public void testInvalidArgs(){
		Account Jake = new Account(2000);
		Account Sarah = new Account(1000, Jake);
		Sarah.deposit(-50);
		assertTrue(Sarah.balance() == 1000);
		Sarah.withdraw(-100);
		assertTrue(Sarah.balance() == 1000);	
	}
	
	public void testOverdraft(){
		Account Jake = new Account(2000);
		Account Sarah = new Account(1000, Jake);
		Sarah.withdraw(3500);
		assertTrue(Sarah.balance() == 1000);
		assertTrue(Jake.balance() == 2000);	
	}
	
	public void testDeposit(){
		Account Jake = new Account(2000);
		Account Sarah = new Account(1000, Jake);
		Sarah.deposit(500);
		assertTrue(Sarah.balance() == 1500);
	}

	public void testWithdraw(){
		Account Jake = new Account(2000);
		Account Sarah = new Account(1000, Jake);
		Sarah.withdraw(500);
		assertTrue(Sarah.balance() == 500);
		Sarah.withdraw(1000);
		assertTrue(Sarah.balance() == 0);	
		assertTrue(Jake.balance() == 1500);
	}
	
}
