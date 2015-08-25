import junit.framework.TestCase;


public class AccountTest extends TestCase {
//	testInit should check that the balance of a newly created account is the amount 
//	provided to the constructor.
	
	public void testInit(){
		Account acct = new Account(500);
		assertTrue(acct.balance() == 500);
	}
	
//	testInvalidArgs should make sure that the result of supplying a negative number to 
//	deposit and withdraw doesn't change the account's balance.
	
	public void testInvalidArgs(){
		Account acct = new Account(500);
		acct.withdraw(-300);
		assertTrue(acct.balance() == 500);
		acct.deposit(-200);
		assertTrue(acct.balance() == 500);
	}
//	testOverdraft should make sure that an attempt to withdraw more money than the account 
//	contains doesn't change the account's balance.
	
	public void testOverdraft(){
		Account acct = new Account(300);
		acct.withdraw(500);
		assertTrue(acct.balance() == 300);
	}
//	testDeposit should make sure that the account balance reflects the result of a legal 
//	call to deposit.
	
	public void testDeposit(){
		Account acct = new Account(100);
		acct.deposit(300);
		assertTrue(acct.balance() == 400);
	}
//	testWithdraw should make sure that the account balance reflects the result of a legal 
//	call to withdraw.
	public void testWithdraw(){
		Account acct = new Account(300);
		acct.withdraw(100);
		assertTrue(acct.balance() == 200);
	}
}
