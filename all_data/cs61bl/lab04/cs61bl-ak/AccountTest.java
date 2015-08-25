import junit.framework.TestCase;


public class AccountTest extends TestCase {
 
	public void testInit(){
		//same as testInit//
		Account albert = new Account(1000000);
		assertTrue (albert.balance() == 1000000);
		Account anuraag = new Account(100, albert); 
		assertTrue (anuraag.parentAccount == albert);
	}
	
	public void testInvalidArgs(){
		Account anuraag = new Account(100);
		anuraag.deposit(-50);
		assertTrue (anuraag.balance() == 100);
		anuraag.withdraw(-50);
		assertTrue (anuraag.balance() == 100);
	}
	
	public void testOverdraft(){
		Account alan = new Account(5);
		alan.withdraw(10);
		assertTrue (alan.balance() == 5);
		Account stephen = new Account(20, alan);
		stephen.withdraw(30);
		assertTrue (alan.balance() == 5);
		assertTrue (stephen.balance() == 20);
	}
	
	public void testDeposit(){
		Account alan = new Account(5);
		alan.deposit(100);
		assertTrue (alan.balance() == 105);
	}
	
	public void testWithdraw(){
		Account alan = new Account(5);
		Account stephen = new Account(20, alan);
		Account albert = new Account(10, stephen);
		Account roger = new Account(5, albert);
		roger.withdraw(37);
		assertTrue(alan.balance() == 3 &&
				stephen.balance() == 0 &&
				albert.balance() == 0 &&
				roger.balance() == 0);
		
	}
}
	
	
