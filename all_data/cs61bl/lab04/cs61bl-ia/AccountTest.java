import junit.framework.TestCase;


public class AccountTest extends TestCase {
	 public void testInIt() {
	        Account TPain = new Account(500);
	        assertTrue (TPain.balance() == 500);
	    }

	 public void testInvalidArgs() {
		 	Account TPain = new Account(500);
		 	TPain.withdraw(-10);
		 	assertTrue (TPain.balance() == 500);
		 	TPain.deposit(-10);
		 	assertTrue (TPain.balance() == 500);
	    }
	 
	
	    public void testOverdraft() {
	    	Account TPain = new Account(500);
		 	TPain.withdraw(600);
		 	assertTrue (TPain.balance() == 500);
	    }

	    public void testWithdraw() {
	    	Account TPain = new Account(500);
		 	TPain.withdraw(100);
		 	assertTrue (TPain.balance() == 400);
	    }

	    public void testDeposit() {
	    	Account TPain = new Account(500);
		 	TPain.deposit(100);
		 	assertTrue (TPain.balance() == 600);
	    }
    

}


