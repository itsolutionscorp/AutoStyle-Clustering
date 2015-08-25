import junit.framework.TestCase;


public class AccountTest extends TestCase {

	public void testInit() {
		//check balance of newly created account is amount provided to constructor
		Account nancy = new Account(1500);
		int expectedBalance = 1500;
		int actualBalance = nancy.balance();
		assertEquals(expectedBalance, actualBalance);
	}
	
	public void testInvalidArgs() {
		//make sure result of supplying neg number to deposit and withdraw does not change account's balance
		Account nancy = new Account(2000);
		nancy.deposit(-500);
		int expectedBalance = 2000;
		int actualBalance = nancy.balance();
		assertEquals(expectedBalance, actualBalance);
		
		nancy.withdraw(-789);
		int expectedBal = 2000;
		int actualBal = nancy.balance();
		assertEquals(expectedBal, actualBal);
	}
	
	public void testOverdraft() {
		//make sure attempt to withdraw more money than account contains does not change account's balance
		Account nancy = new Account(100);
		nancy.withdraw(1000);
		int expectedBal = 100;
		int actualBal = nancy.balance();
		assertEquals(expectedBal, actualBal);
	}
	
	public void testDeposit() {
		//make sure account balance reflects result of legal call to deposit
		Account nancy = new Account(500);
		nancy.deposit(500);
		int expectedBal = 1000;
		int actualBal = nancy.balance();
		assertEquals(expectedBal, actualBal);
	}
	
	public void testWithdraw() {
		//make sure account balance reflects result of legal call to withdraw
		Account nancy = new Account(4300);
		nancy.withdraw(300);
		int expectedBal = 4000;
		int actualBal = nancy.balance();
		assertEquals(expectedBal, actualBal);
	}
	
}
