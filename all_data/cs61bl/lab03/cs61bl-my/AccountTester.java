public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		Account parent = new Account(1200);
		mike = new Account(1000, parent);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(3000);
		System.out.println(mike.balance());
		System.out.println(parent.balance());
		mike.withdraw(1500);
		System.out.println(mike.balance());
		System.out.println(parent.balance());
		mike.withdraw(500);
		System.out.println(mike.balance());
		System.out.println(parent.balance());
		Account Emily = new Account (5000);
		mike.merge(Emily);
		System.out.println(mike.balance());
	}

}