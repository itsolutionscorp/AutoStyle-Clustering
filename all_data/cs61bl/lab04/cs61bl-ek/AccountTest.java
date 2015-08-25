import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
		Account costumer = new Account(1000);
		assertTrue (costumer.balance() == 1000 );
	}
	public void testInvalidargs() {
		Account invalAcc = new Account(1000);
		invalAcc.withdraw(-100);
		assertTrue (invalAcc.balance() == 1000);
		invalAcc.deposit(-100);
		assertTrue (invalAcc.balance() == 1000);
	}
	public void testOverdraft() {
		Account noMoney = new Account(1);
		noMoney.withdraw(99);
		assertTrue (noMoney.balance() == 1);
	}
	public void testDeposit() {
		Account legalDep = new Account(1);
		legalDep.deposit(9000);
		assertTrue (legalDep.balance() == 9001);
	}
	public void testWithdraw() {
		Account legalWit = new Account(9000);
		legalWit.withdraw(1);
		assertTrue (legalWit.balance() == 8999);
	}

}
