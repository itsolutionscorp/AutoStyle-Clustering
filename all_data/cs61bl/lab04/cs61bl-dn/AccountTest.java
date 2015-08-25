import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit(){
		Account a = new Account(100);
		assertEquals(100 , a.balance());
		
	}
	public void testInvalidArgs(){
		Account a = new Account(100);
		int before = a.balance();
		a.deposit(-5);
		assertEquals( before, a.balance());
	}
	
	public void testOverdraft(){
		Account a = new Account(100);
		int before = a.balance();
		a.withdraw(200);
		assertEquals(before, a.balance());
		
	}
	public void testDeposit(){
		Account a = new Account(100);
		int newBalance = a.balance();
		a.deposit(100);
		assertEquals(a.balance(), newBalance + 100);
	}
	public void testWithdraw(){
		Account a = new Account(100);
		int newBalance = a.balance();
		a.withdraw(50);
		assertEquals( a.balance(), newBalance - 50);
	}
}