public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		boolean c = mike.withdraw(999999);
		System.out.println(c);
		c = mike.withdraw(-999999);
		System.out.println(c);
		
		mike.deposit(1);
		Account mike2 = new Account(2);
		System.out.println("Before merge -");
		System.out.println("Mike balance: " + mike.balance());
		System.out.println("Mike2 balance: " + mike2.balance());
		mike.merge(mike2);
		System.out.println("After merge -");
		System.out.println("Mike balance: " + mike.balance());
		System.out.println("Mike2 balance: " + mike2.balance());
		
		Account kathy = new Account(500);
		Account megan = new Account(100, kathy);
		megan.withdraw(50);
		System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
		
		kathy = new Account(500);
		megan = new Account(100, kathy);
		megan.withdraw(200);
		System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
		
		kathy = new Account(500);
		megan = new Account(100, kathy);
		megan.withdraw(700);
		System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
		
		
		
		
	}

}