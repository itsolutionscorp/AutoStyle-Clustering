public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		boolean two = mike.withdraw(200);
		System.out.println(two + ": " + mike.balance());
		boolean one = mike.withdraw(2000);
		System.out.println(one + ": " + mike.balance());

		Account tim = new Account(1000);
		System.out.println(tim.balance());
		mike.merge(tim);
		System.out.println(mike.balance());
		System.out.println(tim.balance());

        Account kathy = new Account(500);
        Account megan = new Account(100, kathy);
        megan.withdraw(50);
        System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
        megan.deposit(50);
        megan.withdraw(200);
        System.out.println("megan has " + megan.balance() + ", kathy has " + kathy.balance());
        megan.withdraw(500);

	}

}