public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(-100);
		System.out.println(mike.balance());
		mike.withdraw(2000);
		System.out.println(mike.balance());
		Account bob = new Account(100);
		mike.merge(bob);
		System.out.println(mike.balance());
		System.out.println(bob.balance());
		Account kathy = new Account(500);
		Account megan = new Account(100, kathy);
		megan.withdraw(50);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.withdraw(500);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.withdraw(100);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		
	}

}