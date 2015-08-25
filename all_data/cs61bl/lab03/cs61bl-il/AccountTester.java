public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());

		System.out.println(mike.withdraw(200));
		System.out.println(mike.balance());

		System.out.println(mike.withdraw(1000));
		System.out.println(mike.balance());

		System.out.println(mike.withdraw(-1000));
		System.out.println(mike.balance());

		Account another = new Account(500);

		mike.merge(another);

		System.out.println(mike.balance());
		System.out.println(another.balance());

		Account parent = new Account(2000);
		Account son = new Account(400, parent);

		System.out.println(son.withdraw(200));
		System.out.println(son.balance());

		System.out.println(son.withdraw(600));
		System.out.println(son.balance());
		System.out.println(parent.balance());

		System.out.println(son.withdraw(2000));
	}

}