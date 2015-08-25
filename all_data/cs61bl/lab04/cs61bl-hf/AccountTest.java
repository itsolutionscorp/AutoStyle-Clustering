import junit.framework.TestCase;


public class AccountTest extends TestCase {
	
    public void testConstructor() {
    	Account c = new Account(1000);
    	assertTrue(c.balance() == 1000);
    }
    
    public void testInvalidArgs() {
    	Account c = new Account(1000);
    	c.deposit(-100);
    	assertTrue(c.balance() == 1000);
    	c.withdraw(-100);
    	assertTrue(c.balance() == 1000);
    }
    
    public void testOverdraft() {
    	Account c = new Account(1000);
    	c.withdraw(1500);
    	assertTrue(c.balance() == 1000);
    }
    
    public void testDeposit() {
    	Account c = new Account(1000);
    	c.deposit(500);
    	assertTrue(c.balance() == 1500);
    }
    
    public void testWithdraw() {
    	Account c = new Account(1000);
    	c.withdraw(500);
    	assertTrue(c.balance() == 500);
    }
    
}
