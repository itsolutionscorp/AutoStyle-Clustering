import junit.framework.TestCase;


public class AccountTest extends TestCase {
	
	public void testInit(){
		Account bob;
		bob = new Account(500);
		assertTrue (bob.balance() == 500);
	}
	
	public void testInvalidArgs(){
		Account bob;
		bob = new Account(500);
		assertTrue (bob.balance() == 500);
		bob.deposit(-100);
		assertTrue (bob.balance() == 500);
		bob.withdraw(-100);
		assertTrue (bob.balance() == 500);
	}

	public void testOverdraft(){
		Account bob;
		bob = new Account(500);
		assertTrue (bob.balance() == 500);
		bob.withdraw(1000);
		assertTrue (bob.balance() == 500);
	}
	
	public void testDeposit(){
		Account bob;
		bob = new Account(500);
		assertTrue (bob.balance() == 500);
		bob.deposit(100);
		assertTrue (bob.balance() == 600);
	}
	
	public void testWithdraw(){
		Account bob;
		bob = new Account(500);
		assertTrue (bob.balance() == 500);
		bob.withdraw(100);
		assertTrue (bob.balance() == 400);
	}

}
