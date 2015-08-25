public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(10000);
		System.out.println(mike.balance());
		
		Account Joanna = new Account(1000);
		Account Omid = new Account(100);
		Omid.merge(Joanna);
		System.out.println(Omid.balance());
		
		Account Bob = new Account(100, Omid);
		Bob.withdraw(2000);
		System.out.println(Bob.balance());
		System.out.println(Omid.balance());
		Bob.withdraw(200);
		System.out.println(Bob.balance());
		System.out.println(Omid.balance());
		
		// recursive case, checking parent of the parent
		Account bill = new Account(2000);
		Account jane = new Account(200, bill);
		Account alexa = new Account(50, jane);
		alexa.withdraw(500);
		System.out.println(bill.balance()); // expect bill to have 1750
		System.out.println(jane.balance()); // expect 0
		System.out.println(alexa.balance()); // expect 0
		
		// if total sum of balances is not enough
		Account millie = new Account(1);
		Account burt = new Account(1, millie);
		Account jack = new Account(20, burt);
		jack.withdraw(23);
		// all balances should be unchanged
		System.out.println(millie.balance());
		System.out.println(burt.balance());
		System.out.println(jack.balance());
	}

}