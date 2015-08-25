import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit(){
		Account mike = new Account(1000);
		assertTrue(mike.balance() == 1000);
		assertTrue(mike.myPA == null);
		
		Account john = new Account(2000,mike);
		assertTrue(john.myPA == mike);
		assertTrue(john.balance() == 2000);
	}
	
	public void testInvalidArgs(){
		Account mike = new Account(1000);
		mike.deposit(-100);
		assertTrue(mike.balance() == 1000);
		mike.withdraw(-100);
		assertTrue(mike.balance() == 1000);
	}
	
	public void testOverDraft(){
		Account A = new Account(100);
		Account B = new Account(200);
		Account C = new Account(300);
		B.myPA = C;
		A.myPA = B;
		
		A.withdraw(700);
		assertTrue(A.balance() == 100);
		assertTrue(B.balance() == 200);
		assertTrue(C.balance() == 300);
		assertFalse(A.withdraw(700));
		
		A.withdraw(500);
		assertTrue(A.balance() == 0);
		assertTrue(B.balance() == 0);
		assertTrue(C.balance() == 100);
	}
	
	public void testDeposit(){
		Account A = new Account(100);
		A.deposit(200);
		assertTrue(A.balance() == 300);
		
		A.deposit(0);
		assertTrue(A.balance() == 300);
		
	}
	
	public void testWithdraw(){
		Account A = new Account(1000);
		A.withdraw(200);
		assertTrue(A.balance() == 800);
		
		A.withdraw(0);
		assertTrue(A.balance() == 800);
		
		/** test scenarios where the money goes to parent's/grandparent's 
		 *  accounts are tested in the testOverDraft method above.
		 */   
	}

}
