import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit(){
		Account CS = new Account(1000);
		assertTrue(CS.balance() == 1000);
		
	}
	public void testInvalidArgs(){
		Account Test = new Account(1000);
		Test.deposit(-100);
		assertTrue(Test.balance() == 1000);
		Test.withdraw(-100);
		assertTrue(Test.balance() == 1000);
	}
	public void testOverdraft(){
		Account Test = new Account(1000);
		Test.withdraw(9001);
		assertTrue(Test.balance() == 1000);
	}
	public void testDeposit(){
		Account Test = new Account(1000);
		Test.deposit(200);
		assertTrue(Test.balance() == 1200);
	}
	public void testWithdraw(){
		Account Test = new Account(1000);
		Test.withdraw(200);
		assertTrue(Test.balance() == 800);
	}

}
