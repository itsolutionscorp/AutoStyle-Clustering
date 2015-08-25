public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		Account steve;
		mike = new Account(1000);
		steve = new Account(100);
		System.out.println(mike.balance());
		mike.deposit(100); //mike should have 1100
		System.out.println(mike.balance());
		mike.withdraw(200); //mike should have 900
		System.out.println(mike.balance());
		
		
		System.out.println(steve.balance()); //steve should start with 100
		steve.deposit(2000);  //steve should have 2100
		System.out.println(steve.balance());
		steve.deposit(400); //steve should have 2500
		System.out.println(steve.balance());
		steve.withdraw(3000);  //withdraw fail and return false;
		steve.withdraw(-10);
		steve.withdraw(2500);
		steve.deposit(700); //steve should have 700
		
		
		//test merge
		steve.merge(mike);
		System.out.println(steve.balance());  //steve should have 1600
		System.out.println(mike.balance()); //mike should 0
		
		//Testing Overdraft
		System.out.println("Testing Overdraft Protection");

		Account parent3 = new Account(5);
		Account parent2 = new Account(1000, parent3);
		Account parent1 = new Account(1000, parent2);
		Account poorChild = new Account(20, parent1);

		
		
		
		
		System.out.println("Case 1");
		poorChild.withdraw(5000);
		System.out.println(poorChild.balance());
		System.out.println(parent1.balance());
		System.out.println(parent2.balance());
		System.out.println(parent3.balance());
		//none of the parents have enough money, should print insufficient funds.
		//all numbers should be the same
		
		System.out.println("Case 2");
		poorChild.withdraw(500);
		System.out.println(poorChild.balance());
		System.out.println(parent1.balance()); //should print 520 since parent has enough money
		
		
		
		
	}

}