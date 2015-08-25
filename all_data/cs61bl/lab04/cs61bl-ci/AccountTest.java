import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit(){
		Account mike = new Account(1000);
		assertTrue(mike.balance() == 1000);
	}
	
	public void testInvalidArgs(){
		Account mike = new Account(1000);
		mike.deposit(-100);
		assertTrue(mike.balance() == 1000);
		mike.withdraw(-100);
		assertTrue(mike.balance() == 1000);
	}
	
	public void testOverdraft(){
		Account mike = new Account(1000);
		mike.withdraw(1500);
		assertTrue(mike.balance() == 1000);
	}
	
	public void testDeposit(){
		Account mike = new Account(1000);
		mike.deposit(100);
		assertTrue(mike.balance() == 1100);
	}
	
	public void testWithdraw(){
		//megan's parent is kathy, kathy's parent is denil
		Account denil = new Account(1000);
		Account kathy = new Account(500, denil);
		Account megan = new Account(100, kathy);

		//try withdraw 50 on megan
		assertTrue(megan.withdraw(50));
		assertTrue(megan.balance() == 50);
		assertTrue(kathy.balance() == 500);
		assertTrue(denil.balance() == 1000);
		megan.deposit(50);
		
		//try withdraw 200 on megan
		assertTrue(megan.withdraw(200));
		assertTrue(megan.balance() == 0);
		assertTrue(kathy.balance() == 400);
		assertTrue(denil.balance() == 1000);
		
		//try withdraw 700 on megan
		assertTrue(megan.withdraw(700));
		assertTrue(megan.balance() == 0);
		assertTrue(kathy.balance() == 0);
		assertTrue(denil.balance() == 700);
	
		//try withdraw 1000 on megan
		assertFalse(megan.withdraw(1000));
		assertTrue(megan.balance() == 0);
		assertTrue(kathy.balance() == 0);
		assertTrue(denil.balance() == 700);
	}
	
}
