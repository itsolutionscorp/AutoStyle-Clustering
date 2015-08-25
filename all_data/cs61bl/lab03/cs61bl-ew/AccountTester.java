public class AccountTester {

	public static void main(String[] args) {
		Account Franifer;
		Franifer = new Account(1500);
		Account mike;
		mike = new Account(1000, Franifer);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(1000);
		System.out.println(Franifer.balance());
		mike.deposit(100);
		mike.withdraw(1600);
	}

}
