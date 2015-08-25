public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		Account grandparent = new Account(500);
		Account parent = new Account(500, grandparent);
		mike = new Account(1000, parent);
		mike.withdraw(-10);
		System.out.println(mike.balance());
		System.out.println(parent.balance());
		System.out.println(grandparent.balance());
		mike.withdraw(1500);
		System.out.println(mike.balance());
		System.out.println(parent.balance());
		System.out.println(grandparent.balance());
		mike.withdraw(500);
		System.out.println(mike.balance());
		System.out.println(parent.balance());
		System.out.println(grandparent.balance());
		mike.withdraw(500);
	}

}