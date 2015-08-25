import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		/* check that balance of a newly created account is
		 * the amount provided by the constructor 
		 */
		Account kara = new Account(600);
		assertTrue (kara.balance() == 600);
	}
	
	public void testInvalidArgs() {
		/* check that the result of supplying a negative number
		 * to deposit and withdraw doesn't change the account's
		 * balance
		 */
		Account kevin = new Account(1000);
		kevin.withdraw(-50);
		assertTrue (kevin.balance() == 1000);
		kevin.deposit(-18);
		assertTrue (kevin.balance() == 1000);
	}
	
	public void testOverdraft() {
		/* check that an attempt to withdraw more money that the
		 * account contains doesn't change the account's balance
		 */
		Account poorMan = new Account(10);
		poorMan.withdraw(30);
		assertTrue (poorMan.balance() == 10);
	}
	
	public void testDeposit() {
		/* check that the account balance reflects the result
		 * of a legal call to deposit
		 */
		Account notSoPoor = new Account(1300);
		notSoPoor.deposit(235);
		assertTrue (notSoPoor.balance() == 1535);
	}
	
	public void testWithdraw() {
		/* check that the account balance reflects the result
		 * of a legal call to withdraw
		 */
		Account richMan = new Account(100000);
		richMan.withdraw(954);
		assertTrue (richMan.balance() == 99046);
	}
	
}
