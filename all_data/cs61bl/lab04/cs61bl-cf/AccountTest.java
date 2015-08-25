import junit.framework.TestCase;


public class AccountTest extends TestCase {
	
	public void testInit() {
		Account dad = new Account(1000);
		Account son = new Account(500, dad);
		assertTrue(dad.balance() == 1000);
		assertTrue(son.parentAccount == dad);
	}
	
	public void testInvalidArgs() {
		Account son = new Account(500);
		son.deposit(-500);
		assertTrue(son.balance() == 500);
		son.withdraw(-500);
		assertTrue(son.balance() == 500);
	}
	
	public void testOverdraft() {
		Account dad = new Account(1000);
		Account son = new Account(500, dad);
		assertTrue(dad.withdraw(1001) == false);
		son.withdraw(1000);
		assertTrue(son.balance() == 0);
		assertTrue(dad.balance() == 500);
	}
	
	public void testDeposit() {
		Account a = new Account(0);
		a.deposit(1000);
		assertTrue(a.balance() == 1000);
	}
	
	public void testWithdraw() {
		Account a = new Account(1000);
		a.withdraw(1000);
		assertTrue(a.balance() == 0);
	}
}
