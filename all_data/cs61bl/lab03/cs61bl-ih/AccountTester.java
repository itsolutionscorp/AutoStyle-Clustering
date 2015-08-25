public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(200));
		System.out.println(mike.withdraw(1000));
        System.out.println(mike.balance());
        Account eric;
        eric = new Account(400);
        mike.merge(eric);
        System.out.println(mike.balance());
        System.out.println(eric.balance());
        Account kathy = new Account(500);
        Account megan = new Account(100, kathy);
        megan.withdraw(700);
        System.out.println(megan.balance());
        System.out.println(kathy.balance());
        Account noah = new Account(200);
        Account justin = new Account(600, noah);
        Account adam = new Account(500, justin);
        adam.withdraw(1200);
        System.out.println(adam.balance());
        System.out.println(justin.balance());
        System.out.println(noah.balance());
        System.out.println(mike.balance());
	}

}