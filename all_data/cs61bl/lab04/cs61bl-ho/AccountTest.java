import junit.framework.TestCase;

public class AccountTest extends TestCase {
    public void testInit() {
    	Account mike = new Account(1000);
    	assertEquals (mike.balance(), 1000);
    }

    public void testInvalidArgs() {
    	Account mike = new Account(1000);
    	mike.deposit(-1);
    	assertTrue (mike.balance() == 1000);
    	mike.withdraw(-1);
    	assertTrue (mike.balance() == 1000);
    }

    public void testOverdraft() {
    	Account mike = new Account(1000);
    	Account mary = new Account(1000, mike);
    	mike.withdraw(1001);
    	assertTrue (mike.balance() == 1000);
    	mary.withdraw(2001);
    	assertTrue (mike.balance() == 1000);
    	assertTrue (mary.balance() == 1000);
    	
    }
    public void testDeposit() {
    	Account mike = new Account(1000);
    	mike.deposit(1);
    	assertTrue (mike.balance() == 1001);   	
    }
    public void testWithdraw() {
    	Account mike = new Account(1000);
    	Account mary = new Account(1000, mike);
    	mike.withdraw(100);
    	assertTrue (mike.balance() == 900);
    	mary.withdraw(1001);
    	assertTrue (mike.balance() == 899);
    	assertTrue (mary.balance() == 0);
    }

}
