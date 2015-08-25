import junit.framework.TestCase;


public class AccountTest extends TestCase {

	public void testDeposit(){
		Account fakeacc = new Account(200);
		fakeacc.deposit(300);
		assertEquals(500, fakeacc.balance());
	}
	
	public void testWithdraw(){
		Account fakeacc = new Account(200);
		fakeacc.withdraw(200);
		assertTrue(0 ==  fakeacc.balance());
	}
	public void testOverdraft(){
		Account fakeaccgrandmom = new Account(10000);
		Account fakeaccmom = new Account(1000, fakeaccgrandmom);
		Account fakeacc = new Account(200, fakeaccmom);
		fakeacc.withdraw(100000);
		
		if (fakeacc.balance() != 200 || fakeaccmom.balance() != 1000 || fakeaccgrandmom.balance() != 10000) {
			fail();
		}
	}
	
	public void testInvalidArgs() {
		Account fakeacc = new  Account(200);
		fakeacc.deposit(-100);
		assertEquals(200, fakeacc.balance());
		fakeacc.withdraw(-100);
		assertEquals(200, fakeacc.balance());
		
		
	}
	
	public void testInit() {
		Account fakeacc = new Account(200);
		assertEquals(200, fakeacc.balance());
		
	}
	
}
