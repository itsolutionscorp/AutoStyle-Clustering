import junit.framework.TestCase;


public class AccountTest extends TestCase {

	public void testInit() {
		Account kevin = new Account(100);
		assertTrue (kevin.balance() == 100);
	}
	public void testInvalidArgs() {
		Account kevin = new Account(100);
		kevin.withdraw(-100);
		assertTrue (kevin.balance() == 100);
	}
	public void testOverdraft() {
		Account kevin = new Account(100);
		Account kevin_mom = new Account(100);
		kevin_mom.withdraw(400);
		assertEquals(kevin.balance(), kevin_mom.balance());
	}
	public void testDeposit() {
		Account kevin = new Account(100);
		kevin.deposit(50);
		assertTrue (kevin.balance() == 150);
}
	public void testWithdraw() {
		Account kevin = new Account(100);
		kevin.withdraw(50);
		assertTrue (kevin.balance() == 50);
}
}
