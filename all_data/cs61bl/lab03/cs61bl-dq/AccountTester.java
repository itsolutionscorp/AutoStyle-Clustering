public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(200));
		System.out.println(mike.balance());
		Account steve = new Account(500);
		mike.merge(steve);
		System.out.println(mike.balance());
		System.out.println(steve.balance());
		Account susy = new Account(100, mike);
		System.out.println(susy.withdraw(200));
		System.out.println(mike.balance());
		System.out.println(susy.balance());
		System.out.println(susy.withdraw(1500));
		System.out.println(mike.balance());
		System.out.println(susy.balance());
	}

}