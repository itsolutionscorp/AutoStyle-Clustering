public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		// Tests for withdrawl errors:
		System.out.println(mike.balance());
		mike.withdraw(-20);
		System.out.println(mike.balance());
		mike.withdraw(1000);
		// Tests for merging accouts:
		System.out.println(mike.balance());
		Account jimmy = new Account(500);
		System.out.println(jimmy.balance());
		mike.merge(jimmy);
		System.out.println(jimmy.balance());
		System.out.println(mike.balance());
		// Tests for parent accouts:
		Account kathy = new Account(500);
		Account megan = new Account(100, kathy);
		megan.withdraw(50);
		System.out.println("megan balance: " + megan.balance()); // expect 50
		System.out.println("kathy balance: " + kathy.balance()); // expect 500
		megan.withdraw(100);
		System.out.println("megan balance: " + megan.balance()); // expect 0
		System.out.println("kathy balance: " + kathy.balance()); // expect 450
		// Testing parent account tree:
		Account elizabeth = new Account(500);
		Account charles = new Account(200, elizabeth);
		Account william = new Account(50, charles);
		Account george = new Account(10, william);
		george.withdraw(10000); // Too much even for the royal family
		System.out.println("george balance: " + george.balance()); // expect 10
		System.out.println("william balance: " + william.balance()); // ex 50
		System.out.println("charles balance: " + charles.balance()); // ex 200
		System.out.println("elizabeth balance: " + elizabeth.balance()); // 500
		george.withdraw(400);
		System.out.println("george balance: " + george.balance()); // expect 0
		System.out.println("william balance: " + william.balance()); // expect 0
		System.out.println("charles balance: " + charles.balance()); // expect 0
		System.out.println("elizabeth balance: " + elizabeth.balance()); // 360
	}

}
