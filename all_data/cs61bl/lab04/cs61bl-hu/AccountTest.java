import junit.framework.TestCase;


public class AccountTest extends TestCase {
	 public void testInit() {
		Account c;
		c = new Account(1000); 
		assertTrue (c.balance() == 1000);
	 }

     public void testInvalidArgs () {
    	Account c;
 		c = new Account(1000); 
 		c.withdraw(-200);
 		assertTrue (c.balance() == 1000);
     }
 
     public void testOverdraft () {
    	Account c;
    	Account A;
  		c = new Account(1000);
  		A = new Account(500, c);
  		A.withdraw (2000);
  		assertTrue (c.balance() == 1000);
  		assertTrue (A.balance() == 500);
     } 
     
     public void testWithdraw() {
    	Account c;
     	Account A;
   		c = new Account(1000);
   		A = new Account(500, c);
   		A.withdraw (200);
   		assertTrue (A.balance() == 300);
   		A.withdraw (500);
   		assertTrue (A.balance() == 0);
   		assertTrue (c.balance() == 800);
     }

     public void testDeposit() {
    	 Account c;
    	 c = new Account(1000);
    	 c.deposit(500);
    	 assertTrue (c.balance() == 1500);
     }
}




