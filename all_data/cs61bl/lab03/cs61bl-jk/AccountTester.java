public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(1000);
		System.out.println(mike.balance());
		Account anotherAcct = new Account(100);
		mike.merge(anotherAcct);
		System.out.println(mike.balance());
		System.out.println(anotherAcct.balance());
		Account overdraft2 = new Account(500);
		Account overdraft1 = new Account(500, overdraft2);
		overdraft1.withdraw(2000);
		System.out.println(overdraft1.balance());
		System.out.println(overdraft2.balance());
		overdraft1.withdraw(600);
		System.out.println(overdraft1.balance());
		System.out.println(overdraft2.balance());
	}

}