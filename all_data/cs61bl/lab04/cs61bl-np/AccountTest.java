import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit(){
		int myInt = 0;
		Account myAccount = new Account (myInt);
		assertTrue (myAccount.balance() ==0);
	}
	public void testInvalidArgs(){
		int myInt = 0;
		Account myAccount = new Account (myInt);
		myAccount.deposit(-50);
		assertEquals (myAccount.withdraw(-50),false);
		assertEquals (myAccount.balance(),0);
	}
	public void testOverdraft(){
		int myInt = 0;
		Account myAccount = new Account (myInt);
		Account myAccount2 = new Account (1000, myAccount);
		myAccount2.withdraw(1000000000);
		assertTrue (myAccount2.balance() ==1000);
	}
	public void testDeposit(){
		int myInt = 0;
		Account myAccount = new Account (myInt);
		myAccount.deposit(100);
		assertTrue (myAccount.balance() ==100);
	}
	public void testWithdraw(){
		int myInt = 50;
		Account myAccount = new Account (myInt);
		myAccount.withdraw(50);
		assertTrue (myAccount.balance() ==0);
		
	}
}
