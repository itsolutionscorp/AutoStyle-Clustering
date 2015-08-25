import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;

public class AccountTest extends TestCase {
	

	@Test
	public void testConstructor(int n) {
		Account j = new Account(30);
		
	
	}
	public void testInit() {
		Account j = new Account(100);
		assertTrue (j.balance() == 100);

	}
	public void testInvalidArgs() {
		Account j = new Account(100);
		j.deposit(-1);
		j.withdraw(-2);
		assertTrue (j.balance() == 100);
}
	public void testOverdraft() {
		Account j = new Account(100);
		j.withdraw(800);
		assertTrue (j.balance() == 100);
	}
	public void testDeposit() {
		Account j = new Account(100);
		j.deposit(900);
		assertTrue (j.balance() == 1000);
	}
	public void testWithdraw() {
		Account j = new Account(100);
		j.withdraw(100);
		assertTrue (j.balance() == 0);
	}
}



/*
testInit should check that the balance of a newly created account is the amount provided to the constructor.

testInvalidArgs should make sure that the result of supplying a negative number to deposit and withdraw doesn't change the account's balance.

testOverdraft should make sure that an attempt to withdraw more money than the account contains doesn't change the account's balance.

testDeposit should make sure that the account balance reflects the result of a legal call to deposit.

testWithdraw should make sure that the account balance reflects the result of a legal call to withdraw

*/