import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit(){
		Account Test_Account = new Account(100);
		assertTrue(Test_Account.balance() == 100);			
	}
	public void testInvalidArgs(){
		Account Test_Account = new Account(100);
		Test_Account.deposit(-100);
		assertTrue(Test_Account.balance() == 100);
		Test_Account.withdraw(-100);
		assertTrue(Test_Account.balance() == 100);
		
		
	}
	public void testOverdraft(){
		Account Test_Account = new Account(100);
		Test_Account.withdraw(1000);
		assertTrue(Test_Account.balance() == 100);		
	}
	public void testDeposit(){
		Account Test_Account = new Account(100);
		Test_Account.deposit(1000);
		assertTrue(Test_Account.balance() == 1100);	
		
	}
	public void withdraw(){
		Account Test_Account = new Account(100);
		Test_Account.withdraw(90);
		assertTrue(Test_Account.balance() == 10);	
	

}
}