public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(100);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(2000);
		System.out.println(mike.balance());
		mike.withdraw(-200);
		System.out.println(mike.balance());
		Account tommy; 
		tommy = new Account(500);
		mike.merge(tommy);
		System.out.println(mike.balance());
		System.out.println(tommy.balance());
		
		Account megan;
		megan = new Account (100);
		//mike.parentAccount = new Account (100); 
		megan.parentAccount = mike; 
		megan.withdraw(300);
		System.out.println(megan.balance());
		System.out.println(mike.balance());
		
	}

}