public class AccountTester {

	public static void main(String[] args) {
//		Account mike;
//		mike = new Account(1000);
//		System.out.println(mike.balance());
//		mike.deposit(100);
//		System.out.println(mike.balance());
//		mike.withdraw(200); // return true
//		System.out.println(mike.balance());
//		mike.withdraw(-4); // return false
//		boolean test = mike.withdraw(1530980435); // return false
//		System.out.println(test);
//		
//		Account charles;
//		charles = new Account(1000);
//		mike.merge(charles);
//		System.out.println(mike.balance());
//		System.out.println(charles.balance());

		Account kathy = new Account(500);
		Account megan = new Account(100, kathy);
		megan.withdraw(50);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		System.out.println();
		
		megan.deposit(50);
		System.out.println(megan.balance());	
		megan.withdraw(200);
		System.out.println(megan.balance()); // should be 0
		System.out.println(kathy.balance()); // should be 400
		System.out.println();
		
		megan.deposit(100);
		kathy.deposit(100);
		megan.withdraw(700);
//		System.out.println(megan.withdraw(700));
//		System.out.println(kathy.withdraw(600));
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		System.out.println();
		
		Account steven = new Account(100);
		steven.withdraw(50);
		System.out.println(steven.balance());
		steven.withdraw(32532);
		System.out.println(steven.balance());
		System.out.println();
		
//		Three linked accounts.
		Account dad = new Account(100);
		Account mom = new Account(50, dad);
		Account harry = new Account(50, mom);
		harry.withdraw(200);
		System.out.println(dad.balance());
		System.out.println(mom.balance());
		System.out.println(harry.balance());
		harry.withdraw(250); // return Insufficient funds.
		
	}

}