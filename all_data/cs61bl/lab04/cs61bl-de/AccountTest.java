
import junit.framework.TestCase;


public class AccountTest extends TestCase {
	
	Account m = new Account(1000);
	public void testInit () {	
		assertTrue(m.balance() == 1000);	
	}
	
	public void testInvalidArgs() {
		m.deposit(-10);
		assertTrue(m.balance() == 1000);
		m.withdraw(-10);
		assertTrue(m.balance() == 1000);
		assertEquals(false, m.withdraw(-10));
		
	}
	
	public void testOverdraft() {
		Account mary = new Account(1000, m);
		mary.withdraw(5000);
		assertTrue(mary.balance()==1000);
		assertTrue(m.balance()==1000);
				
	}
	
	public void testDeposit() {
		m.deposit(1000);
		assertTrue(m.balance()==2000);
		m.deposit(1);
		assertTrue(m.balance()==2001);
		
	}
	
	public void testWithdraw() {
		m.withdraw(1000);
		assertTrue(m.balance() == 0);
		
		
	}
	
	
}