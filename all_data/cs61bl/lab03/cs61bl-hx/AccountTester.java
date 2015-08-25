public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(2000);
		System.out.println(mike.balance());
		mike.withdraw(-2000);
		System.out.println(mike.balance());
		Account dave = new Account(1000);
		mike.merge(dave);
		System.out.println(mike.balance());
		System.out.println(dave.balance());
		Account bob = new Account(100, mike);
		Account susan = new Account(300, bob);
		susan.withdraw(600);
		System.out.println(mike.balance());
		System.out.println(bob.balance());
		System.out.println(susan.balance());
	}

}