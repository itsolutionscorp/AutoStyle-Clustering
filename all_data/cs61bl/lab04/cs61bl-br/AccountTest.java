import junit.framework.TestCase;


public class AccountTest extends TestCase {

	public void testInit() {
	
		Account Dude = new Account(1000);
		assertTrue (Dude.balance()== 1000);
		
		
	};
	
	public void testInvalidArgs() {
		
		Account Dude = new Account(1000);
		Dude.deposit(-500);
		assertTrue (Dude.balance() == 1000);
		
		Dude.withdraw(-500);
		assertTrue (Dude.balance() == 1000);
		
		
		

		
							
	};
	
	public void testOverdraft() {
		
		Account Dude = new Account(1000);
		assertTrue (Dude.withdraw(1100)== false);
		
		Account Grand = new Account(1500);
		Account Dude1 = new Account(1000,Grand);
		assertTrue (Dude1.withdraw(2600)== false);
		
		
		
	};
	
	public void testDeposit() {
		
		Account Dude = new Account(1000);
		Dude.deposit(500);
		assertTrue (Dude.balance() == 1500);
		
		
	};
	
	public void testWithdraw() {
		
		Account Grand = new Account(1500);
		Account Dude = new Account(1000,Grand);
		
		Dude.withdraw(2000);
		assertTrue(Grand.balance() == 500);
		
		
	};
	
	
	
}
