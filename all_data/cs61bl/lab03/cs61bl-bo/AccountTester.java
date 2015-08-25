public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		
		//Test modified withdrawal.
		System.out.println(mike.withdraw(-10)); //false
		System.out.println(mike.withdraw(10000)); //false
		System.out.println(mike.withdraw(1)); //true
		
		// Test merge method.
		
		Account Cathy = new Account(2000);
		System.out.println("mike: " + mike.balance());
		System.out.println("Cathy: " + Cathy.balance());
		
		mike.merge(Cathy);
		System.out.println("mike: " + mike.balance());
		System.out.println("Cathy: " + Cathy.balance());
		
		//Test overdraft protection.
		Account kathy = new Account(500);
		Account megan = new Account(100,kathy);
		System.out.println(megan.withdraw(50)); //true
		System.out.println("megan has " + megan.balance());
		System.out.println("kathy has " + kathy.balance());
		System.out.println();
		System.out.println(megan.withdraw(200)); //true with overdraft protection
		System.out.println("megan has " + megan.balance());
		System.out.println("kathy has " + kathy.balance());
		System.out.println();
		System.out.println(megan.withdraw(700)); //false insufficient funds
		System.out.println("megan has " + megan.balance());
		System.out.println("kathy has " + kathy.balance());
	}
}