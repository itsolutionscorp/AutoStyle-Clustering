import junit.framework.TestCase;


public class AccountTest extends TestCase {
	
	public void testInit(){
		Account myAccount = new Account(500);
		assertTrue (myAccount.balance() == 500);		
	}
	
	public void testInvalidArgs(){
		Account myAccount = new Account(500);
		myAccount.withdraw(-1);
		assertTrue (myAccount.balance()==500);
		myAccount.deposit(-1);
		assertTrue (myAccount.balance()==500);
	}
	
	public void testOverdraft(){
		Account myAccount = new Account(500);
		myAccount.withdraw(600);
		assertTrue (myAccount.balance()==500);
	}
	
	public void testDeposit(){
		Account myAccount = new Account(500);
		myAccount.deposit(100);
		assertTrue (myAccount.balance()==600);
	}
	
	public void testWithdraw(){
		Account myAccount = new Account(500);
		myAccount.withdraw(100);
		assertTrue (myAccount.balance()==400);
	}
}
