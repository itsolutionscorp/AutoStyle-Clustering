import junit.framework.TestCase;


public class AccountTest extends TestCase {
	/**
	 * Checks that the balance of a newly created account is the amount
	 * provided to the constructor.
	 */
	public void testInit() {
		Account c = new Account(100);
		assertTrue(c.balance() == 100);
	}
	
	/**
	 * Makes sure that the result of supplying a negative number to deposit
	 * and withdraw doesn't change the account's balance.
	 */
	public void testInvalidArgs() {
		Account c = new Account(100);
		c.deposit(-1);
		c.withdraw(-1);
		assertTrue(c.balance() == 100);
	}
	
	/**
	 * Makes sure that an attempt to withdraw more money than the account
	 * contains doesn't change the account's balance.
	 */
	public void testOverdraft() {
		Account c = new Account(100);
		c.withdraw(200);
		assertTrue(c.balance() == 100);
	}
	
	/**
	 * Makes sure that the account balance reflects the result of a legal
	 * call to deposit.
	 */
	public void testDeposit() {
		Account c = new Account(100);
		c.deposit(50);
		assertTrue(c.balance() == 150);
	}
	
	/**
	 * Makes sure that the account balance reflects the result of a legal
	 * call to withdraw.
	 */
	public void testWithdraw() {
		Account c = new Account(100);
		c.withdraw(49);
		assertTrue(c.balance() == 51);
	}
}
