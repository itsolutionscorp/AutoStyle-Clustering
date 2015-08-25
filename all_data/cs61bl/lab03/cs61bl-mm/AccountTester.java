public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		// Has $1000
		mike.deposit(100);
		System.out.println(mike.balance());
		// Has $1100
		mike.withdraw(200);
		// Returns true
		System.out.println(mike.balance());
		// Has $900
		mike.withdraw(1000);
		// Returns false, "Insufficient funds"
		mike.withdraw(-1);
		// Returns false, "Cannot withdraw negative amount."
		Account bob = new Account(500);
		System.out.println(bob.balance());
		// Has $500
		mike.merge(bob);
		// Mike takes Bob's money
		System.out.println(bob.balance());
		// Has $0
		System.out.println(mike.balance());
		// Has $1400
		Account kathy = new Account(500);
		Account megan = new Account(100, kathy);
		megan.withdraw(50);
		// Return true
		System.out.println(megan.balance());
		// Megan has $50
		megan.withdraw(100);
		// Return true
		System.out.println(megan.balance());
		// Megan has $0
		System.out.println(kathy.balance());
		// Kathy has $450
		megan.deposit(50);
		System.out.println(megan.balance());
		// Megan has $50
		megan.withdraw(600);
		// Return false, "Insufficient funds"
		System.out.println(megan.balance());
		// Megan has $50
		System.out.println(kathy.balance());
		// Kathy has $450
	}

}