import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testConstructor() {
        Account mike = new Account(200);
        assertTrue (mike.balance() == 200);
    }

    public void testDeposit() {
    	Account mike = new Account(200);
		mike.deposit(100);
		assertTrue (mike.balance() == 300);
    }
    
    public void testWithdraw() {
    	Account mike = new Account(300);
		mike.withdraw(200);
		assertTrue (mike.balance() == 100);
    }
}
