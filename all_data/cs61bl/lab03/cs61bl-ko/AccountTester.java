public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		boolean a = mike.withdraw(1000);
		System.out.println(a);
		boolean b = mike.withdraw(-200);
		System.out.println(b);
		boolean c = mike.withdraw(400);
		System.out.println(c);
		System.out.println(mike.balance());
		Account other;
		other = new Account (9000);
		System.out.println(other.balance());
		mike.merge(other); // merges the other account's balance into Mike's account, and resets the other account's balance to 0.
		System.out.println("this is Mike's balance: " + mike.balance());
		System.out.println("this is the other account's balance: " + other.balance());
		Account kathy, megan;
		kathy = new Account(500);
		megan = new Account(100, kathy);
		megan.withdraw(50);
		System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
		megan.withdraw(200);
		System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
		megan.withdraw(700);
		System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
		megan.withdraw(350);
		System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
		megan.withdraw(1);
		System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
		megan.withdraw(0);
		System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
	}	

}