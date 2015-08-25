import java.util.Random;

import junit.framework.TestCase;

public class AccountTest extends TestCase {
	Account c = new Account(500, null);
	
	public void testInit() {
		int beforeBal = c.balance();
        assertTrue (c.balance() == beforeBal);
    }

    public void testInvalidArgs() {
        int beforeBal = c.balance();
        c.deposit(-100);
        assertTrue (c.balance() == beforeBal);
    	c.withdraw(-100);
        assertTrue (c.balance() == beforeBal);
    }

    public void testOverdraft() {
    	int beforeBal = c.balance();
        c.withdraw(beforeBal + 100);
        assertTrue (c.balance() == beforeBal);
    }

    public void testDeposit(){
    	int beforeBal = c.balance();
    	c.deposit(100);
    	assertTrue (c.balance() == beforeBal + 100);
    }
    
    public void testWithdraw(){
    	int beforeBal = c.balance();
    	Random rand = new Random();
    	int randomNum = rand.nextInt(beforeBal);
    	c.withdraw(randomNum);
    	assertTrue (c.balance() == beforeBal - randomNum);
    }
}
