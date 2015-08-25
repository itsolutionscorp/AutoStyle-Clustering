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
		Account eliza;
		eliza = new Account (1000);
		mike.merge(eliza);
		System.out.println(mike.balance());
		System.out.println(eliza.balance());
		Account seth;
		seth = new Account (1000, mike);
		seth.withdraw(900);
		System.out.println(seth.balance());
		seth.withdraw(2100);
		System.out.println(seth.balance());
		System.out.println(mike.balance());
	}

}