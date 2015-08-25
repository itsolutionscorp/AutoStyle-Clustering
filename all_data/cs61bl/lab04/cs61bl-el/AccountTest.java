import junit.framework.TestCase;

public class AccountTest extends TestCase {
	/*
	 * testInit should check that the balance of a newly created account is the
	 * amount provided to the constructor./
	 */

	public void testInit() {
		Account eclipse = new Account(100);
		assertEquals(100, eclipse.balance());
	}

	/*
	 * testInvalidArgs should make sure that the result of supplying a negative
	 * number to deposit and withdraw doesn't change the account's balance.
	 */

	public void testInvalidArgs() {
		Account eclipse = new Account(100);
		eclipse.deposit(-10);
		assertEquals(100, eclipse.balance());
		eclipse.withdraw(-100);
		assertEquals(100, eclipse.balance());
	}

	/*
	 * testOverdraft should make sure that an attempt to withdraw more money
	 * than the account contains doesn't change the account's balance.
	 */

	public void testOverdraft() {
		Account eclipse = new Account(100);
		Account star = new Account(10, eclipse);
		star.withdraw(1000);
		assertEquals(10, star.balance());
		assertEquals(100, eclipse.balance());
		eclipse.withdraw(10000);
		assertEquals(100, eclipse.balance());
	}

	/*
	 * testDeposit should make sure that the account balance reflects the result
	 * of a legal call to deposit.
	 */
	public void testDeposit() {
		Account eclipse = new Account(100);
		eclipse.deposit(10);
		assertEquals(110, eclipse.balance());
		
	}

	/*
	 * testWithdraw should make sure that the account balance reflects the
	 * result of a legal call to withdraw.
	 */
	public void testWithdraw() {
		Account eclipse = new Account(100);
		eclipse.withdraw(10);
		assertEquals(90, eclipse.balance());
	}
}
