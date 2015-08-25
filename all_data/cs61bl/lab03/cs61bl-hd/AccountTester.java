public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(200));
		mike.merge(new Account(100));
		System.out.println(mike.balance());
		Account jake;
		jake = new Account(1000, mike);
		jake.withdraw(1500);
		System.out.println(jake.balance());
		System.out.println(mike.balance());

	}

}