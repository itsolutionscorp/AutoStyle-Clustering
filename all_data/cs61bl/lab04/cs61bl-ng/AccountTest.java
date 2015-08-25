import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit () {
		Account c = new Account(1000);
		assertTrue (c.balance() == 1000);
	}
	public void testInvalidArgs() {
		Account c = new Account(1000);
		c.withdraw(-100);
		assertTrue (c.balance() == 1000);
		c.deposit(-100);
		assertTrue (c.balance() == 1000);
	}
	public void testOverdraft() {
		Account c = new Account(1000);
		c.withdraw(1001);
		assertTrue (c.balance() == 1000);
	}
	public void testDeposit() {
		Account c = new Account(1000);
		c.deposit(90);
		assertTrue (c.balance() == 1090);
	}
	public void testWithdraw() {
		Account c = new Account(1000);
		c.withdraw(90);
		assertTrue (c.balance() == 910);
	}

}
