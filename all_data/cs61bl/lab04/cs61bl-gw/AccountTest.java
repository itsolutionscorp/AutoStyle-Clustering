import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
        Account c = new Account(1000);
        assertTrue (c.balance() == 1000);
        Account protect = new Account(500);
        Account p = new Account(1000, protect);       
        assertTrue (p.balance() == 1000);
    }
	public void testInvalidArgs() {
        Account c = new Account(1000);
        c.deposit(-500);
        assertTrue (c.balance() == 1000);
        c.withdraw(-500);
        assertTrue (c.balance() == 1000);
    }
	public void testOverdraft() {
        Account c = new Account(1000);
        c.withdraw(1500);
        assertTrue (c.balance() == 1000);
        Account protect = new Account(500);
        Account p = new Account(1000, protect); 
        p.withdraw(1500);
        assertTrue (p.balance() == 0);
    }
	public void testDeposit() {
		Account c = new Account(1000);
		c.deposit(1500);
		assertTrue (c.balance() == 2500);
    }
	public void testWithdraw() {
		Account c = new Account(1000);
		c.withdraw(800);
		assertTrue (c.balance() == 200);
		Account protect = new Account(500);
        Account p = new Account(1000, protect); 
        p.withdraw(1300);
        assertTrue (p.balance() == 0);
		
    }

	

}
