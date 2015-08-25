import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account tony = new Account(1000);
		assertTrue (tony.balance() == 1000);
	}
	
	public void testInvalidArgs() {
		Account steve = new Account(300);
		steve.deposit(-500);
		assertTrue (steve.balance() == 300);
		steve.withdraw(-500);
		assertTrue (steve.balance() == 300);
	}
	
	public void testOverdraft() {
		Account loki = new Account(800);
		loki.withdraw(900);
		assertTrue (loki.balance() == 800);
		Account odin = new Account(5000);
		Account thor = new Account(1200, odin);
		thor.withdraw(1300);
		assertTrue (thor.balance() == 0);
		assertTrue (odin.balance() == 4900);
	}
	
	public void testDeposit() {
		Account bruce = new Account(100);
		bruce.deposit(200);
		assertTrue (bruce.balance() == 300);
	}
	
	public void testWithdraw() {
		Account clint = new Account(500);
		clint.withdraw(300);
		assertTrue (clint.balance() == 200);
	}
}
