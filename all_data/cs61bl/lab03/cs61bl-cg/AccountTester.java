public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		System.out.print("Withdrawl should be true: ");
		System.out.println(mike.withdraw(200) == true);

		System.out.print("Withdrawl should be false: ");
		System.out.println(mike.withdraw(1000) == true);
		
		
		Account magic;
		magic = new Account(800);
		magic.merge(mike);
		
		Account kathy;
		kathy = new Account(500);
		Account megan;
		megan = new Account(100, kathy);
		
		megan.withdraw(50);
		System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
		
		megan.deposit(50);
		megan.withdraw(200);
		System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
		
		megan.deposit(100);
		kathy.deposit(100);
		System.out.println(megan.withdraw(700));
	}

}