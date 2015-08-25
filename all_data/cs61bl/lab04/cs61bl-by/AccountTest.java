import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account kathy = new Account(500);
		Account megan = new Account(100);
		Account sally = new Account(200, kathy);
		assertTrue(megan.balance() == 100);
		assertTrue(kathy.balance()==500);
		assertTrue(sally.balance()==200);

	}
	
	public void testInvalidArgs() {
		Account kathy = new Account(500);
		kathy.deposit(-100);
		assertTrue(kathy.balance()==500);
		kathy.withdraw(-100000);
		assertTrue(kathy.balance()==500);
	}
	
	public void testOverDraft() {
		Account kathy = new Account(500);
		Account megan = new Account(100, kathy);
		megan.withdraw(700);
		assertTrue(megan.balance() == 100);
		assertTrue(kathy.balance()==500);
	}
	
	public void testDeposit() {
		Account kathy = new Account(500);
		kathy.deposit(1);
		assertTrue(kathy.balance()==501);
	}
	
	public void testWithdraw(){
		Account kathy = new Account(500);
		kathy.withdraw(1);
		assertTrue(kathy.balance()==499);
	}
}