import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		int balance = 1000;
		Account one = new Account(balance);
		assertEquals(balance, one.balance());
	}
	
	public void testInvalidArgs() {
		int balance = 1000;
		int deposit = -100;
		int withdraw = -100;
		Account two = new Account(balance);
		two.deposit(deposit);
		assertEquals(balance, two.balance());
		two.withdraw(withdraw);
		assertEquals(balance, two.balance());
		
	}
	
	
	public void testOverdraft() {
		int balance = 1000;
		int withdraw = 2000;
		Account three = new Account(balance);
		three.withdraw(withdraw);
		assertEquals(balance, three.balance());
		
	}
	
	
	public void testDeposit() {
		int deposit = 100;
		int balance = 1000;
		Account four = new Account(balance);
		four.deposit(deposit);
		assertEquals(four.balance(), balance + deposit);
	}
	
	
	public void testWithdraw() {
		int withdraw = 100;
		int balance = 1000;
		Account five = new Account(balance);
		five.withdraw(withdraw);
		assertEquals(five.balance(), balance - withdraw);
	}

}
