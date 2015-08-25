public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(300));
		System.out.println(mike.withdraw(10000));
		
		Account anotherAcct = new Account(3000);
		mike.merge(anotherAcct);
		System.out.println("mike's acct balance " + mike.balance());
		System.out.println("another acct balance " + anotherAcct.balance());
		
		Account kathy = new Account(500);
		Account megan = new Account(100,kathy);
		megan.withdraw(50);
		System.out.println("megan's acct balance " + megan.balance());
		System.out.println("kathy's acct balance " + kathy.balance());
		megan.withdraw(150);
		System.out.println("megan's acct balance " + megan.balance());
		System.out.println("kathy's acct balance " + kathy.balance());
		megan.withdraw(700);
		System.out.println("megan's acct balance " + megan.balance());
		System.out.println("kathy's acct balance " + kathy.balance());
		
	}

}