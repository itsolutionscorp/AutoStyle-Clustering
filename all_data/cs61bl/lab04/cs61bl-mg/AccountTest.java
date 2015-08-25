import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account tester = new Account(1000);
		assertTrue(tester.balance() == 1000);
	}
	
	public void testInvalidArgs() {
		Account test2 = new Account(100);
		test2.withdraw(-5);
		test2.deposit(-5);
		assertTrue(test2.balance() == 100);
	}
	
	public void testOverdraft() {
		Account tester = new Account(1);
		tester.withdraw(2);
		assertTrue(tester.balance() == 1);
	}
	
	public void testDeposit() {
		Account tester = new Account(1);
		tester.deposit(2);
		assertTrue(tester.balance() == 3);
	}
	
	public void testWithdraw() {
		Account tester = new Account(2);
		tester.withdraw(2);
		assertTrue(tester.balance() == 0);
	}

}
