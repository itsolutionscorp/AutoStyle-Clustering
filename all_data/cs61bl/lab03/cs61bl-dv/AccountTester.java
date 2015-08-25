public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(-100));
		System.out.println(mike.withdraw(1000));
		System.out.println(mike.withdraw(500));
		System.out.println(mike.balance());
		Account john = new Account(1600);
		mike.merge(john);
		System.out.println("Mike's balance is now " + mike.balance());
		System.out.println("John's balance is now " + john.balance());
		Account kathy = new Account(500);
		Account megan = new Account(100);
		megan.parentAccount = kathy;
		//megan.withdraw(50);
		//System.out.println("Megan has " + megan.balance() + ", " + "Kathy has " + kathy.balance());
		//megan.withdraw(200);
		//System.out.println("Megan has " + megan.balance() + ", " + "Kathy has " + kathy.balance());
		System.out.println(megan.withdraw(700));
		System.out.println("Megan's balance: " + megan.balance());
		System.out.println("Kathy's balance: " + kathy.balance());
		
	}

}