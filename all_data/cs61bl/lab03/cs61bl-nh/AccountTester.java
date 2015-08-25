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
		System.out.println(mike.withdraw(-5));

		Account joe = new Account(1000);
		mike.merge(joe);
		System.out.println(joe.balance());
		System.out.println(mike.balance());

		Account kathy1 = new Account(500);
		Account megan1 = new Account(100, kathy1);
		System.out.println(megan1.withdraw(50));
		System.out.println(megan1.balance());
		System.out.println(kathy1.balance());

		Account kathy2 = new Account(500);
		Account megan2 = new Account(100, kathy2);
		System.out.println(megan2.withdraw(200));
		System.out.println(megan2.balance());
		System.out.println(kathy2.balance());

		Account kathy3 = new Account(500);
		Account megan3 = new Account(100, kathy3);
		System.out.println(megan3.withdraw(700));
		System.out.println(megan3.balance());
		System.out.println(kathy3.balance());

		Account mark1 = new Account(150);
		Account joseph1 = new Account(100, mark1);
		Account philip1 = new Account(50, joseph1);
		System.out.println(philip1.withdraw(100));
		System.out.println(philip1.balance());
		System.out.println(joseph1.balance());
		System.out.println(mark1.balance());

		Account mark2 = new Account(150);
		Account joseph2 = new Account(100, mark2);
		Account philip2 = new Account(50, joseph2);
		System.out.println(philip2.withdraw(200));
		System.out.println(philip2.balance());
		System.out.println(joseph2.balance());
		System.out.println(mark2.balance());

		Account mark3 = new Account(150);
		Account joseph3 = new Account(100, mark3);
		Account philip3 = new Account(50, joseph3);
		System.out.println(philip3.withdraw(300));
		System.out.println(philip3.balance());
		System.out.println(joseph3.balance());
		System.out.println(mark3.balance());

		Account mark4 = new Account(150);
		Account joseph4 = new Account(100, mark4);
		Account philip4 = new Account(50, joseph4);
		System.out.println(philip4.withdraw(400));
		System.out.println(philip4.balance());
		System.out.println(joseph4.balance());
		System.out.println(mark4.balance());
	}

}