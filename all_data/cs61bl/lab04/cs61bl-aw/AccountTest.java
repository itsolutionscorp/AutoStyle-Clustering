package lab03;

import junit.framework.TestCase;

public class AccountTest extends TestCase {
	public void testconstructor(){
		Account mike = new Account(1000);
		assertTrue (mike.balance()==1000);
	}
	public void testdeposit(){
		Account a = new Account(1000);
		a.deposit(100);
		assertTrue(a.balance() ==1000);
	}
	public void testwithdraw(){
		Account a = new Account(1000);
		a.deposit(100);
		a.withdraw(200);
		assertTrue(a.balance()==800);
	}
	
	public void testmerge(){
		Account a = new Account(100);
		Account b = new Account(200);
		a.merge(b);
		assertTrue(a.balance() == 300);
		assertTrue(b.balance() ==0);
	}
	
	public void testwithdraw2(){
		Account Tom = new Account(900);
		Account mike = new Account (500,a);
		
		Tom.withdraw(1000);
		assertTrue(Tom.balance()==0);
		assertTrue(mike.balance() ==400);
		
		
		
	}

}


