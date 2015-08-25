import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account myAcc = new Account(500);
		assertTrue (myAcc.balance() == 500);
	}
	public void testInvalidArgs() {
		Account myAcc = new Account(300);
		myAcc.withdraw(-100);
		assertTrue (myAcc.balance() == 300);
		myAcc.deposit(-100);
		assertTrue (myAcc.balance() == 300);
	}
	public void testOverdraft() {
		Account myAcc = new Account(300);
		myAcc.withdraw(301);
		assertTrue (myAcc.balance() == 300);
	}
	public void testDeposit() {
		Account myAcc = new Account(300);
		myAcc.deposit(100);
		assertTrue (myAcc.balance() == 400);
	}
	public void testWithdraw() {
		Account myAcc = new Account(300);
		myAcc.withdraw(100);
		assertTrue (myAcc.balance() == 200);
	}
}
