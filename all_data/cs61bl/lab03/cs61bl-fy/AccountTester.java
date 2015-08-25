public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		
		/* Exercise : modifying Withdrawl Behavior */
		boolean a;
		a = mike.withdraw(-200);
		System.out.println("Status : " + a);
		System.out.println(mike.balance());
		a = mike.withdraw(1000);
		System.out.println("Status : " + a);
		System.out.println(mike.balance());
		
		/* Exercise : Merging Accounts*/
		Account susan;
		susan = new Account(1000);
		System.out.println(susan.balance());
		mike.merge(susan);
		System.out.println(susan.balance());
		System.out.println(mike.balance());
		
		/* Exercise : Overdraft Protection*/
		Account kathy, megan;
		kathy = new Account(500);
		megan = new Account(100, kathy);
		System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
		megan.withdraw(50);
		System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
		megan.withdraw(200);
		System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
		megan.withdraw(700);
		System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
	}

}