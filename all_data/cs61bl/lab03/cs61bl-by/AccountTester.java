public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance()); //900
		System.out.println(mike.withdraw(20000)); //print Insufficient funds then false
		System.out.println(mike.withdraw(50)); //should print true
		System.out.println(mike.balance()); //should be 850
		
		Account tom;
		tom = new Account(1000);
		System.out.println(tom.balance()); //print 1000
		mike.merge(tom);  //mike now has 1850, tom has 0
		System.out.println(mike.balance()); //print 1850
		System.out.println(tom.balance()); //print 0
		
		
		Account megan1;
		Account kathy1 = new Account(500);
		megan1 = new Account(100, kathy1);		
		megan1.withdraw(50);
		System.out.println(megan1.balance()); //50
		System.out.println(kathy1.balance()); //500
		
		Account megan2;
		Account kathy2 = new Account(500);
		megan2= new Account(100,kathy2);
		megan2.withdraw(200);
		System.out.println(megan2.balance()); //0
		System.out.println(kathy2.balance()); //400
		
		Account megan3;
		Account kathy3 = new Account(500);
		megan3= new Account(100,kathy3);
		megan3.withdraw(700); //return false print insufficient funds
		System.out.println(megan3.balance()); //100
		System.out.println(kathy3.balance()); //500
		
	}

}