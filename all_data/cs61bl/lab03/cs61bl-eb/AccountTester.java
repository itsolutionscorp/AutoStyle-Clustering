public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance()); // output should be 1000 
		mike.deposit(100); 
		System.out.println(mike.balance()); // output should be 1100
		System.out.println(mike.withdraw(200)); // should print true 
		System.out.println(mike.balance()); // output should be 900
		System.out.println(mike.withdraw(-100)); // should print false
		System.out.println(mike.balance()); //output should be 900
		System.out.println(mike.withdraw(901)); // should print false
		System.out.println(mike.balance()); //output should be 900
		
		// Test merge method 
		Account alice; 
		alice = new Account(500); 
		System.out.println(alice.balance()); // output should be 500 
		mike.merge(alice); 
		System.out.println(mike.balance()); // output should be 1400
		System.out.println(alice.balance()); // output should be 0
		
		// Test overdraft method 
		Account kathy; 
		Account megan;  
		kathy = new Account(500, null); 
		megan = new Account(100, kathy); 
		System.out.println("*****1st overdraft test *****");
		System.out.println(megan.withdraw(50)); // should print true 
		System.out.println("megan "+ megan.balance()); // output should be 50
		System.out.println("kathy " + kathy.balance()); // output should be 500
		//
		megan.deposit(50); // restore original funds  
		System.out.println("*****2nd overdraft test *****");
		System.out.println(megan.withdraw(200)); // should print true 
		System.out.println("megan "+ megan.balance()); // output should be 0
		System.out.println("kathy " + kathy.balance()); // output should be 400
		
		megan.deposit(100); // restore original funds  
		kathy.deposit(100); // restore original funds  
		System.out.println("*****3rd overdraft test *****");
		System.out.println(megan.withdraw(700)); // should print false 
		System.out.println("megan "+ megan.balance()); // output should be 100
		System.out.println("kathy " + kathy.balance()); // output should be 500


	}

}