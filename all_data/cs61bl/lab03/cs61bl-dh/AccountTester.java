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
		System.out.println(mike.withdraw(900));
		System.out.println(mike.withdraw(10));
		System.out.println(mike.balance());
		Account sam = new Account(500);
		mike.deposit(300);
		mike.merge(sam);
		System.out.println(mike.balance());
		System.out.println(sam.balance());
		Account grandma = new Account (300);
		Account mom = new Account(1500, grandma);
		Account dave = new Account(100, mom);
		System.out.println(dave.balance());
		System.out.println(mom.balance());
		System.out.println(grandma.balance());
		dave.withdraw(2200);
		System.out.println(dave.balance());
		System.out.println(mom.balance());
		System.out.println(grandma.balance());
		dave.withdraw(1700);
		System.out.println(dave.balance());
		System.out.println(mom.balance());
		System.out.println(grandma.balance());
		dave.withdraw(500);
		System.out.println(dave.balance());
		System.out.println(mom.balance());
		System.out.println(grandma.balance());
	}

}