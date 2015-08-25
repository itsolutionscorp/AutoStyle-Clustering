import junit.framework.TestCase;


public class AccountTest extends TestCase {
    public void testInit() {
    	Account c = new Account(700);
        assertTrue (c.balance() == 700);
    }
    
    public void testInvalidArgs() {
    	Account c = new Account(700);
        c.deposit(-5);
        assertTrue (c.balance() == 700);
        c.withdraw(-5);
        assertTrue (c.balance() == 700);
    }
    
    public void testOverdraft() {
    	Account c = new Account(700);	
        c.withdraw(701);
        assertTrue (c.balance() == 700);
    }
    
    public void testDeposit() {
    	Account c = new Account(700);	
        c.deposit(5);
        assertTrue (c.balance() == 705);
    }
    
    public void testWithdraw() {
    	Account c = new Account(700);	
        c.withdraw(5);
        assertTrue (c.balance() == 695);
    }
}
