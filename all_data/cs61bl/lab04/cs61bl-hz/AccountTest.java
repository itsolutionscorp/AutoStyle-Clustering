import junit.framework.TestCase;

public class AccountTest extends TestCase {
	
	 public void testInit() {
	     Account c = new Account(500);
	     assertTrue (c.balance() == 500);
	 }
	 
	 public void testInvalidArgs() {
		 Account c = new Account(500);
		 assertTrue(c.deposit(-1) == false);
		 assertTrue(c.withdraw(-1) == false); 
	 }
	 
	 public void testOverdraft() {
		Account kathy, megan;
		kathy = new Account(500);
		megan = new Account(100, kathy);
		assertTrue(megan.withdraw(700) == false);
	 }
	 
	 public void testDeposit() {
		 Account c = new Account(500);
		 c.deposit(500);
		 assertTrue(c.balance() == 1000);
	 }
	 
	 public void testWithdraw() {
		 Account kathy, megan, mike, mark;
		 kathy = new Account(500);
		 megan = new Account(100, kathy);
		 megan.withdraw(50);
		 assertTrue(megan.balance() == 50 && kathy.balance() == 500);
		 mark = new Account(500);
		 mike = new Account(100, mark);
		 mike.withdraw(200);
		 assertTrue(mike.balance() == 0 && mark.balance() == 400);
		 
	 }


}
