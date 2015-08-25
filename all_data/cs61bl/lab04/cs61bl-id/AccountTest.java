import junit.framework.TestCase;

public class AccountTest extends TestCase{
	public void testInit() {
		Account eric = new Account(100);
		assertTrue (eric.balance() == 100);
	}
	
	public void testInvalidArgs() {
		Account eric = new Account(100);
		eric.withdraw(-100);
		assertTrue (eric.balance() == 100);
		eric.deposit(-100);
		assertTrue (eric.balance() == 100);
	}
	
	public void testOverdraft() {
		Account eric = new Account(100);
		eric.withdraw(200);
		assertTrue (eric.balance() == 100);
	}
	
	public void testDeposit() {
		Account eric = new Account(100);
		eric.deposit(100);
		assertTrue (eric.balance() == 200);
	}
	
	public void testWithdraw() {
		Account eric = new Account(100);
		eric.withdraw(50);
		assertTrue (eric.balance() == 50);
	}
	
}
