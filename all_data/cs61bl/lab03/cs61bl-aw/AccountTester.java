public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		Account Tom;
		Account Jerry;
		mike = new Account(1000);
		System.out.println(mike.balance()); //1000
		mike.deposit(100);
		System.out.println(mike.balance()); //1100
		// test case when withdraw amount is less than 0
		mike.withdraw(-200); //Cannot withdraw negative amount.
		System.out.println(mike.balance());	
        // test case when withdraw amount is not more than own balance
		mike.withdraw(200);
		System.out.println(mike.balance()); //900
		// test case when withdraw amount is more than own balance and parent account is null 
		mike.withdraw(1000);
		System.out.println(mike.balance()); //900
	    // test case when original account has only parent but no grandparent and withdraw amount is not more than the sum of the balances but more than original account balance
		Tom = new Account(500, mike); // Tom.myBalance = 500, mike.myBalance = 900
		Tom.withdraw(1000);
		System.out.println(Tom.balance()); //0
		System.out.println(mike.balance()); //400
		// test case when original account has only parent but no grandparent and withdraw amount is more than the sum of the balances 
		Tom.deposit(100); // Tom.myBalance = 100 mike.myBalance = 400
		Tom.withdraw(1000);
		System.out.println(Tom.balance()); //100
		System.out.println(mike.balance()); //400
		// test case when original account has parent and grandparent and withdraw amount is not more than the sum of the balances but more than original account balance
		Jerry = new Account(800); // Jerry.myBalance = 800
		mike.parentAccount = Jerry; 
		Tom.deposit(200); // Tom.myBalance = 300 mike.myBalance = 400
		Tom.withdraw(1200);
		System.out.println(Tom.balance()); //0
		System.out.println(mike.balance()); //0
		System.out.println(Jerry.balance()); //300
		
		Tom.deposit(500); // Tom = 500
		mike.deposit(600); // mike = 600
		Tom.withdraw(1000);
	    System.out.println(Tom.balance()); //0
		System.out.println(mike.balance()); //100
		System.out.println(Jerry.balance()); //300
		
		Tom.deposit(500); // Tom.myBalance = 500
		mike.deposit(600); // mike.myBalance = 700 Jerry.myBalance = 300
		Tom.withdraw(1600);
	    System.out.println(Tom.balance()); //500
		System.out.println(mike.balance()); //700
		System.out.println(Jerry.balance()); //300
	}

}