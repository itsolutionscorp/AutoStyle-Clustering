public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		
		Account jeremey = new Account(500);
		mike.merge(jeremey);
		System.out.println(mike.balance());
		System.out.println(jeremey.balance());
		
		Account kathy = new Account(500);
		Account megan = new Account(100,kathy);
		megan.withdraw(50);
		System.out.println("megan has "+megan.balance());
		System.out.println("kathy has "+kathy.balance());
		
		kathy = new Account(500);
		megan = new Account(100,kathy);
		megan.withdraw(200);
		System.out.println("megan has "+megan.balance());
		System.out.println("kathy has "+kathy.balance());
		
		kathy = new Account(500);
		megan = new Account(100,kathy);
		megan.withdraw(700);
		System.out.println("megan has "+megan.balance());
		System.out.println("kathy has "+kathy.balance());
	}

}