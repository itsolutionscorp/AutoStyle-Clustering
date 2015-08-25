import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account grandpa = new Account(100);
		assertTrue(grandpa.balance() == 100);
		Account dad = new Account(50);
		assertTrue(dad.balance() == 50);
	}
	public void testInvalidArgs() {
		Account dad = new Account(50);
		dad.withdraw(-10);
		assertTrue(dad.balance() == 50);
		dad.deposit(-10);
		assertTrue(dad.balance() == 50);
		dad.deposit(0);
		assertTrue(dad.balance() == 50);
		
	}
	public void testOverdraft() {
		Account grandpa = new Account(100);
		assertTrue(grandpa.balance() == 100);
		grandpa.withdraw(101);
		assertTrue(grandpa.balance() == 100);
		grandpa.withdraw(10001);
		assertTrue(grandpa.balance() == 100);
		
		
		Account dad = new Account(50, grandpa);
		dad.withdraw(151);
		assertTrue(dad.balance() == 50);
		assertTrue(grandpa.balance() == 100);
	}
	public void testDeposit() {
		Account dad = new Account(50);
	
		dad.deposit(10);
		assertTrue(dad.balance() == 60);
		dad.deposit(1);
		assertTrue(dad.balance() == 61);
	}
	public void testWithdraw() {
		Account dad = new Account(50);
		dad.withdraw(10);
		assertTrue(dad.balance() == 40);
		dad.withdraw(40);
		assertTrue(dad.balance() == 0);
	}
}
