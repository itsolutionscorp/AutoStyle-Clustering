import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit(){
		Account a = new Account(500);
		assertTrue(a.balance()==500);
		
	}
	public void testInvalidArgs(){
		Account c = new Account(1000);
		c.withdraw(-1);
		assertTrue(c.balance()==1000);
	}
	public void testOverdraft(){
		Account b = new Account(1000);
		b.withdraw(1100);
		assertTrue(b.balance()==1000);
		
	}
	public void testDeposit(){
		Account d = new Account(900);
		d.deposit(100);
		assertTrue(d.balance()==1000);
	}
	public void testWithdraw(){
		Account e = new Account(900);
		e.withdraw(900);
		assertTrue(e.balance()==0);
		
	}

}
