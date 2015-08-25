import junit.framework.TestCase;


public class AccountTest extends TestCase {
	
	public void testInit () {
		Account sam = new Account( 400);
		assertTrue( sam.balance() == 400);
		
	}
	
	public void testInvalidArgs (){
		Account sam = new Account( 400);
		sam.withdraw(-100);
		assertTrue( sam.balance() == 400);
		sam.deposit(-100);
		assertTrue( sam.balance() == 400);
		
		
	}
	
	public void testOverdraft() {
		Account sam = new Account( 400);
		sam.withdraw(500);
		assertTrue( sam.balance() == 400);
		
	} 
	
	
	public void testDeposit() {
		Account sam = new Account( 400);
		sam.deposit(500);
		assertTrue( sam.balance() == 900);
	
	}
	
	public void testWithdraw() {
		Account sam = new Account( 400);
		sam.withdraw(100);
		assertTrue( sam.balance() == 300);
		
	}

}
