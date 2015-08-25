import junit.framework.TestCase;


public class AccountTest extends TestCase {
	 public void testConstructor() {
	        Account Megan = new Account(500);
	        assertTrue (Megan.balance() == 500);
	        Account Kathy = new Account(200,Megan);
	        assertTrue (Kathy.balance() == 200);
	 }
	 public void testInit() {
		 	Account Megan = new Account(500);
		 	assertTrue (Megan.balance() == 500);
		 	Account Kathy = new Account(200,Megan);
		 	assertTrue (Kathy.balance() == 200);
	 }
	 public void testInvalidArgs() {
		 	Account Megan = new Account(500);
		 	Megan.deposit(-200);
		 	assertTrue (Megan.balance() == 500);
		 	Megan.withdraw(-200);
		 	assertTrue (Megan.balance() == 500);
	 }
	 public void testOverdraft() {
		 Account Megan = new Account(500);
		 Megan.withdraw(1000);
		 assertTrue (Megan.balance() == 500);
	 }
	 public void testDeposit() {
		 Account Megan = new Account(500);
		 Megan.deposit(300);
		 assertTrue (Megan.balance() == 800);
		 Megan.deposit(200);
		 assertTrue (Megan.balance() == 1000);
	 }
	 public void testWithdraw() {
		 Account Megan = new Account(500);
		 Megan.withdraw(200);
		 assertTrue (Megan.balance() == 300);
		 Account Kathy = new Account(200,Megan);
		 assertTrue (Kathy.balance() == 200);
		 Kathy.withdraw(300);
		 assertTrue (Kathy.balance() == 0);
		 assertTrue (Megan.balance() == 200);	 
	 }

}
