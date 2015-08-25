public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		if (mike.withdraw(200) == true) {
			System.out.println(mike.balance());
			System.out.println("Withdrawn!");
		}
		// Test for merge
		System.out.println("Testing merge");
		Account Bob;
		Bob = new Account(1000);
		System.out.println(mike.balance());
		System.out.println(Bob.balance());
		mike.merge(Bob); 
		System.out.println(mike.balance());
		System.out.println(Bob.balance());
		// Test for overdraft protection
		System.out.println("Testing overdraft protection");
		Account megan;
		Account kathy;
		Account tom;
		tom = new Account(1000);
		kathy = new Account(500, tom);
		megan = new Account(100, kathy);
		megan.withdraw(100);
		System.out.println("megan's balance ="+ megan.balance());
		System.out.println("kathy's balance =" + kathy.balance());
		System.out.println("tom's balance =" + tom.balance());
		megan.withdraw(500);
		System.out.println("megan's balance ="+ megan.balance());
		System.out.println("kathy's balance =" + kathy.balance());
		System.out.println("tom's balance =" + tom.balance());
		kathy.withdraw(600);
		System.out.println("megan's balance ="+ megan.balance());
		System.out.println("kathy's balance =" + kathy.balance());
		System.out.println("tom's balance =" + tom.balance());
	}

}