public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		
		//withdraw check
		mike.withdraw(2000);
		System.out.println(mike.balance());
		mike.withdraw(-200);
		System.out.println(mike.balance());
		
		//Merge
		Account ryan;
		ryan = new Account(2000);
		ryan.merge(mike);
		System.out.println("mike's balance:" + mike.balance());
		System.out.println("ryan's balance:" + ryan.balance());
		
		//Overdraft Protection
		Account kathy;
		Account megan;
		kathy = new Account(500);
		megan = new Account(100, kathy);
		megan.withdraw(50);
		System.out.println(kathy.balance());
		System.out.println(megan.balance());
		megan.withdraw(200);
		System.out.println(kathy.balance());
		System.out.println(megan.balance());
		megan.withdraw(700);
		System.out.println(kathy.balance());
		System.out.println(megan.balance());
	}

}