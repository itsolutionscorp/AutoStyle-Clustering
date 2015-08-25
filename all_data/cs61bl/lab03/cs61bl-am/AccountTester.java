package lab03;

public class AccountTester {

	public static void main(String[] args) {
//		Account mike;
//		mike = new Account(1000);
//		System.out.println(mike.balance());
//		mike.deposit(100);
//		System.out.println(mike.balance());
//		mike.withdraw(200);
//		System.out.println(mike.balance());
		
		// withdraw tests
//		Account bob;
//		bob = new Account(1000);
//		bob.withdraw(-100);
//		bob.withdraw(2000);
//		bob.withdraw(500);
//		System.out.println(bob.balance());
		
//		// overdraft
		Account kate, megan;
		kate = new Account(500);
		megan = new Account(100, kate);
		megan.withdraw(50);
		System.out.println("kate: " + kate.balance());
		System.out.println("megan: " + megan.balance());
		megan.deposit(50);
		megan.withdraw(200);
		System.out.println("kate: " + kate.balance());
		System.out.println("megan: " + megan.balance());
		megan.withdraw(700);
		System.out.println("kate: " + kate.balance());
		System.out.println("megan: " + megan.balance());
		
		
	}

}