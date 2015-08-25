package lab04;

import junit.framework.TestCase;

public class AccountTest extends TestCase {
	
	public void testInit() {
		Account a = new Account(500);
		assertTrue(a.balance() == 500);
	}
	
	public void testInvalidArgs() {
		Account a = new Account(500);
		a.deposit(-100);
		assertTrue(a.balance() == 500);
		a.withdraw(-100);
		assertTrue(a.balance() == 500);
	}
	/*
	 * testOverdraft should make sure that an attempt to withdraw more
	 * money than the account contains doesn't change the account's balance.
	 */
	public void testOverdraft() {
		Account a = new Account(500);
		a.withdraw(1000);
		assertTrue(a.balance() == 500);
	}
	/*
	 * testDeposit should make sure that the account balance reflects the result of a legal call to deposit.
	 */
	public void testDeposit() {
		Account a = new Account(500);
		a.deposit(100);
		assertTrue(a.balance() == 600);
	}
	/*
	 * testWithdraw should make sure that the account balance reflects the result of a legal call to withdraw.
	 */
	public void testWithdraw() {
		Account a = new Account(500);
		a.withdraw(100);
		assertTrue(a.balance() == 400);
		
	}
}
