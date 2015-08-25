import static org.junit.Assert.*;

import org.junit.Test;


public class AccountTest {

	@Test
	public void test() {
		
	}
	
	@Test
	public void testInit() {
		Account myAccount = new Account(1000);
		assertTrue (myAccount.balance() == 1000);
	}
	
	@Test
	
	public void testInvalidArgs() {
		Account myAccount = new Account (900);
		myAccount.deposit(-50);
		myAccount.withdraw(-400);
		assertTrue (myAccount.balance() == 900);
		
	}
	
	@Test
	
	public void testOverdraft() {
		Account myAccount = new Account(400);
		myAccount.withdraw(500);
		assertTrue (myAccount.balance() == 400);
	}
	
	@Test
	public void testDeposit() {
		Account myAccount = new Account(100);
		myAccount.deposit(200);
		assertTrue (myAccount.balance() == 300);
		
	}
	
	@Test
	public void testWithdraw() {
		Account myAccount = new Account(600);
		myAccount.withdraw(200);
		assertTrue (myAccount.balance() == 400);
		
	}
	
	
	
	

}
