import junit.framework.TestCase;


public class AccountTest extends TestCase {

 public void testInit(){
		Account Bob;
		Bob = new Account(10);
		assertTrue(Bob.balance() == 10);
	}
	public void testInvalidArgs(){
		Account Pip;
		Pip = new Account(20);
		Pip.withdraw(-30);
		assertTrue(Pip.balance() == 20);
		Pip.deposit(-15);
		assertTrue(Pip.balance() == 20);
	}
	 public void testOverdraft(){
		Account omg;
		omg = new Account(30);
		omg.withdraw(31);
		assertTrue(omg.balance() == 30);
	}  
	public void testDeposit(){
		Account pop;
		pop = new Account(50);
		pop.deposit(20);
		assertTrue(pop.balance() == 70);
	}
	public void testWithdraw(){
		Account lol;
		lol = new Account(100);
		lol.withdraw(10);
		assertTrue(lol.balance() == 90);
	}
}
