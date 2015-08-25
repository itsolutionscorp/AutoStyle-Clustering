import junit.framework.TestCase;


public class AccountTest extends TestCase {

	public void testInit(){
		Account dog = new Account(10);
		assertTrue (dog.balance() == 10);
	}
	
	public void testInvalidArgs(){
		Account cat = new Account (10);
		cat.deposit(-10);
		assertTrue (cat.balance() == 10);
		assertTrue (!cat.withdraw(-10));
		assertTrue (cat.balance() == 10);
	}
	
	public void testOverdraft(){
		Account panda = new Account (100);
		assertTrue (!panda.withdraw(1283081));
		assertTrue (panda.balance() == 100);
	}
	
	public void testDeposit(){
		Account dodo = new Account (1);
		dodo.deposit(1);
		assertTrue (dodo.balance() == 2);
		
	}

	public void testWithdraw(){
		Account sloth = new Account (99999999);
		assertTrue (sloth.withdraw(800));
		assertTrue (sloth.balance() == (99999999 - 800));
	}
}
