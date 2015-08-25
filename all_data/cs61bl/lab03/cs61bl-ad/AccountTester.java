public class AccountTester {
	
	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		Account steve;
		steve = new Account(100);
		mike.merge(steve);
		System.out.println(mike.balance());
		System.out.println(steve.balance());
		
		Account kathy;
		Account megan;
		kathy = new Account(500);
		megan = new Account(100);
		megan.myParent = kathy;
		System.out.println("Megan + Kathy Practice");
		megan.withdraw(50);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.deposit(50);
		megan.withdraw(200);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.deposit(100);
		kathy.deposit(100);
		megan.withdraw(700);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		
		Account ethan;
		ethan = new Account(300);
		kathy.myParent = ethan;
		System.out.println("Ethan is Megan's Grandfather");
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		System.out.println(ethan.balance());
		System.out.println("withdrawal now");
		megan.withdraw(700);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		System.out.println(ethan.balance());
		
		Account jamie;
		jamie = new Account(100);
		ethan.myParent = jamie;
		System.out.println("Jamie is Ethan's mother");
		megan.withdraw(250);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		System.out.println(ethan.balance());
		System.out.println(jamie.balance());
		
	}

}