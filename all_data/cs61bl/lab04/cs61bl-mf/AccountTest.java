import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void TestInit() {
		Account test;
		test = new Account(100);
		assertTrue("Wrong init value", test.myBalance == 100);
	}
	
	public void testInvalidArgs() {
		Account test;
		test = new Account(100);
		test.withdraw(-50);
		assertTrue("Can withdraw negative values.", test.myBalance == 100);
	}
	
	public void testOverdraft() {
		Account test;
		test = new Account(100);
		test.withdraw(9999999);
		assertTrue("Withdrew overdrawn amount.", test.myBalance == 100);
	}
	
	public void testDeposit() {
		Account test;
		test = new Account(100);
		test.deposit(50);
		assertTrue("Deposit is broken.", test.myBalance == 150);
	}

	public void testWithdraw() {
		Account test;
		test = new Account(100);
		test.withdraw(50);
		assertTrue("Withdraw is broken.", test.myBalance == 50);
	}
}
