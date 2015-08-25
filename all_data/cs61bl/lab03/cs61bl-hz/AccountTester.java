public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		Account mark;
		mark = new Account(1000);
		mike.merge(mark);
		System.out.println(mike.balance() + " mike");
		System.out.println(mark.balance() + " mark");
		Account kathy, megan;
		kathy = new Account(500);
		megan = new Account(100, kathy);
		System.out.println("Megan and Kathy");
		System.out.println(megan.balance() + " Meg");
		System.out.println(kathy.balance() + " Kathy");
		megan.withdraw(700);
		System.out.println(megan.balance() + " Meg");
		System.out.println(kathy.balance() + " Kathy");
	}

}