import junit.framework.TestCase;


public class AccountTest extends TestCase {
	
	public void testInit(){
		Account mike = new Account(50);
		assertTrue (mike.balance() == 50);
		Account lynda = new Account(1000);
		assertTrue (lynda.balance() == 1000);
		
	}
	
	public void testInvalidArgs(){
		Account mike = new Account(50);
		mike.withdraw(-10);
		assertTrue (mike.balance() == 50);
		mike.deposit(-30);
		assertTrue (mike.balance() == 50);

	}
	
	public void testOverdraft(){
		Account mike = new Account(50);
		Account lynda = new Account(1000);
		mike.withdraw(100);
		assertTrue (mike.balance() == 50);
		mike.parentAcct(lynda);
		mike.withdraw(100);
		assertTrue (mike.balance() == 0);
		assertTrue (lynda.balance() == 950);
	}
	
	public void testDeposit(){
		Account lynda = new Account(1000);
		lynda.deposit(500);
		assertTrue (lynda.balance() == 1500);
		
	}
	
	public void testWithdraw(){
		Account mike = new Account(50);
		mike.withdraw(49);
		assertTrue (mike.balance() == 1);
	}

}
