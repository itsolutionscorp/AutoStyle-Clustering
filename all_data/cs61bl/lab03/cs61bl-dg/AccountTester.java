public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(200));
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(-10));
		System.out.println(mike.withdraw(5000));
		System.out.println(mike.balance());
		Account bob = new Account(1000, mike);
		mike.merge(bob);
		System.out.println(mike.balance());
		System.out.println(bob.balance());
		bob.deposit(10);
		System.out.println(bob.withdraw(20));
		System.out.println(bob.balance());
		System.out.println(mike.balance());
		bob.deposit(10);
		System.out.println(bob.withdraw(100000));
		System.out.println(bob.balance());
		System.out.println(mike.balance());
	}

}