public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(-500); // withdrawing a negative amount
		mike.withdraw(1000); // over-withdrawing
		
		// checking the merge account method.
		Account lara = new Account(6000);
		System.out.println("Lara balance:" + " " + lara.balance());
		mike.merge(lara);
		System.out.println("Mike balance:" + " " + mike.balance()); // should be 6900
		System.out.println("Lara balance:" + " " + lara.balance()); // should be 0
		
		// checking the parent account overdraft protection.
		Account kathy = new Account(500);
		Account megan = new Account(100, kathy);
		megan.withdraw(50);
		System.out.println("Megan balance:" + " " + megan.balance());
		System.out.println("Kathy balance:" + " " + kathy.balance());
		
		megan.deposit(50);
		megan.withdraw(200);
		System.out.println("Megan balance:" + " " + megan.balance());
		System.out.println("Kathy balance:" + " " + kathy.balance());
		
		megan.deposit(100);
		kathy.deposit(100);
		megan.withdraw(700);
		System.out.println("Megan balance:" + " " + megan.balance());
		System.out.println("Kathy balance:" + " " + kathy.balance());
		
	}

}