import junit.framework.TestCase;


public class AccountTest extends TestCase {
	
	public void testInit(){
		Account c = new Account(200);
		assertTrue (c.balance() == 200);
	}
	
	public void testInvalidArgs(){
		Account c = new Account(200);
		c.deposit(-220);
		assertTrue(c.balance() == 200);
		c.withdraw(-2000);
		assertTrue(c.balance() == 200);
	}
	
	public void testOverdraft(){
		Account mike = new Account(200);
		Account kathy = new Account(400, mike);
		Account perseas = new Account(900, kathy);
		perseas.withdraw(20000);
		assertTrue(perseas.balance() == 900);
		assertTrue(kathy.balance() == 400);
		assertTrue(mike.balance() ==200);
		kathy.withdraw(20000);
		assertTrue(kathy.balance() == 400);
		assertTrue(mike.balance() == 200);
		assertTrue(perseas.balance() == 900);
		mike.withdraw(20000);
		assertTrue(kathy.balance() == 400);
		assertTrue(mike.balance() == 200);
		assertTrue(perseas.balance() == 900);	
	}
	
	public void testDeposit(){
		Account mike = new Account(200);
		Account kathy = new Account(400, mike);
		Account perseas = new Account(900, kathy);
		kathy.deposit(300);
		assertTrue(kathy.balance() == 700);
		assertTrue(mike.balance() == 200);
		assertTrue(perseas.balance() == 900);	
		perseas.deposit(100);
		assertTrue(kathy.balance() == 700);
		assertTrue(mike.balance() == 200);
		assertTrue(perseas.balance() == 1000);
		mike.deposit(100);
		assertTrue(kathy.balance() == 700);
		assertTrue(mike.balance() == 300);
		assertTrue(perseas.balance() == 1000);
	}
	
	public void testWithdraw(){
		Account mike = new Account(200);
		Account kathy = new Account(400, mike);
		Account perseas = new Account(900, kathy);
		kathy.withdraw(500);
		assertTrue(kathy.balance() == 0);
		assertTrue(mike.balance() == 100);
		assertTrue(perseas.balance() == 900);
		perseas.withdraw(920);
		assertTrue(kathy.balance() == 0);
		assertTrue(mike.balance() == 80);
		assertTrue(perseas.balance() == 0);
		mike.withdraw(10);
		assertTrue(kathy.balance() == 0);
		assertTrue(mike.balance() == 70);
		assertTrue(perseas.balance() == 0);

	}
}
