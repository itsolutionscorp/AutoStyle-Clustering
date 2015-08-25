public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		Account jim;
		jim = new Account (10);
		System.out.println(false == jim.withdraw(100));
		mike.merge(jim);
		System.out.println(mike.balance());
		System.out.println(jim.balance());
		System.out.println("new Test");
		Account kathy;
		Account megan;
		kathy = new Account(500);
		megan = new Account(100, kathy);
		megan.withdraw(50);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.withdraw(200);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.withdraw(700);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.withdraw(300);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
	}

}
