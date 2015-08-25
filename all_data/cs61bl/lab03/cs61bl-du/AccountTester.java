public class AccountTester {

	public static void main(String[] args) {
		System.out.println("MIKE AND BIKE");
		Account mike;
		mike = new Account(1000);
		Account bike;
		bike = new Account(500);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(-100);
		System.out.println(mike.balance());
		mike.withdraw(2000);
		System.out.println(mike.balance());
		System.out.println(bike.balance());
		mike.merge(bike);
		System.out.println(mike.balance());
		System.out.println(bike.balance());
		
		System.out.println( );
		System.out.println("MEGAN AND KATHY AND KAGAN");
		Account megan;
		Account kathy;
		Account kagan;
		kagan = new Account(20, null);
		kathy = new Account(500, kagan);
		megan = new Account(100, kathy);
		System.out.println("megan " + megan.balance());
		System.out.println("kathy " + kathy.balance());
		System.out.println("kagan " + kagan.balance() + " ***");
		megan.withdraw(50);
		System.out.println("megan " + megan.balance());
		System.out.println("kathy " + kathy.balance());
		System.out.println("kagan " + kagan.balance() + " ***");
		megan.withdraw(100);
		System.out.println("megan " + megan.balance());
		System.out.println("kathy " + kathy.balance());
		System.out.println("kagan " + kagan.balance() + " ***");
		megan.withdraw(455);
		System.out.println("megan " + megan.balance());
		System.out.println("kathy " + kathy.balance());
		System.out.println("kagan " + kagan.balance() + " ***");
		megan.withdraw(50);
		System.out.println("megan " + megan.balance());
		System.out.println("kathy " + kathy.balance());
		System.out.println("kagan " + kagan.balance() + " ***");
	}

}