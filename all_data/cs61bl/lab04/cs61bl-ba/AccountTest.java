import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit(){
		Account a = new Account(100);
		assertTrue (a.balance() == 100);
		assertTrue (a.parentAccount == null);
		Account b = new Account(200, a);
		assertTrue(b.balance() == 200);
		assertTrue(b.parentAccount==a);
	}
	
	public void testInvalidArgs(){
		Account a = new Account(100);
		a.deposit(-100);
		assertTrue (a.balance() == 100);
		a.withdraw(-100);
		assertTrue (a.balance() == 100);	
	}
	
	public void testOverdraft(){
		Account a = new Account(100);
		a.withdraw(200);
		assertTrue (a.balance() == 100);
		a.withdraw(50);
		assertTrue (a.balance() == 50);
		Account b = new Account(200, a);
		Account c = new Account(200, b);
		c.withdraw(500);
		assertTrue (a.balance() == 50);
		assertTrue (b.balance() == 200);
		assertTrue (c.balance() == 200);
	}
	
	public void testDeposit(){
		Account a = new Account(100);
		a.deposit(200);
		assertTrue (a.balance() == 300);
	}
	
	public void testWithdraw(){
		Account a = new Account(100);
		a.withdraw(200);
		assertTrue (a.balance() == 100);
		a.withdraw(50);
		assertTrue (a.balance() == 50);
		Account b = new Account(200, a);
		Account c = new Account(200, b);
		c.withdraw(420);
		assertTrue (a.balance() == 30);
		assertTrue (b.balance() == 0);
		assertTrue (c.balance() == 0);
	}
}
