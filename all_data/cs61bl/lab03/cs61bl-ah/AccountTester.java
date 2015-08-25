public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		boolean overdrawn = mike.withdraw(200);
		System.out.println(overdrawn);
		System.out.println(mike.balance());
		Account steve = new Account(500);
		mike.merge(steve);
		System.out.println(mike.balance());
		Account kathy = new Account(500);
		Account megan = new Account(100, kathy);
		megan.withdraw(50);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.deposit(50);
		megan.withdraw(200);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.deposit(100);
		kathy.deposit(100);
		megan.withdraw(700);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
	}

}