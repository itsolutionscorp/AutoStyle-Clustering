public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		
//		/****Testing for withdraw****/
//		Account jim = new Account(10);
//		if (false == jim.withdraw(-1)) {
//			System.out.println("Test for withdraw: PASS!");
//		} else {
//			System.out.println("Test for withdraw: FAIL!");
//		}
//		
//		System.out.println("Jim's balance: " + jim.balance());
//		
//		if (true == jim.withdraw(1)) {
//			System.out.println("Test for withdraw: PASS!");
//		} else {
//			System.out.println("Test for withdraw: FAIL!");
//		}
//		
//		System.out.println("Jim's balance: " + jim.balance());
//		if (false == jim.withdraw(11)) {
//			System.out.println("Test for withdraw: PASS!");
//		} else {
//			System.out.println("Test for withdraw: FAIL!");
//		}
//		System.out.println("Jim's balance: " + jim.balance());
//		/****Test End****/
		
		
		/****Test for Merge****/ 
		Account peter = new Account(500);
		Account jason = new Account(200);
		peter.merge(jason);
		
		if (peter.balance() == 700) {
			System.out.println("Test for Merge: PASS!");
		} else {
			System.out.println("Test for Merge: FAIL!");
		}
		
		if (jason.balance() == 0) {
			System.out.println("Test for Merge: PASS!");
		} else {
			System.out.println("Test for Merge: FAIL!");
		}
		
		Account peter1 = new Account(400, peter);
		Account jason1 = new Account(300, jason);
		peter1.merge(jason1);
		
		if (peter1.balance() == 700) {
			System.out.println("Test for Merge: PASS!");
		} else {
			System.out.println("Test for Merge: FAIL!");
		}
		
		if (jason1.balance() == 0) {
			System.out.println("Test for Merge: PASS!");
		} else {
			System.out.println("Test for Merge: FAIL!");
		}
		/*****Test for protection*****/
		Account grandpa = new Account(100);
		Account father = new Account(50, grandpa);
		Account myself = new Account(10, father);
		System.out.println("====================================");
		System.out.println("Protection testing start: ");
		System.out.println("Myself's balance is $" + myself.balance() + ".");
		System.out.println("Father's balance is $" + father.balance() + ".");
		System.out.println("Father's balance is $" + grandpa.balance() + ".");
		if (myself.withdraw(160) == true) {
			System.out.println("Test for withdraw PASS!");
		} else {
			System.out.println("Test for withdraw FAIL!");
		}
		
		System.out.println("After withdrawing $160: ");
		
		if (myself.balance() == 0) {
			System.out.println("Myself's balance is $" + myself.balance() + ".");
		} 
		
		if (father.balance() == 0) {
			System.out.println("Father's balance is $" + father.balance() + ".");
		}
		
		if (grandpa.balance() == 0) {
			System.out.println("Grandpa's balance is $" + grandpa.balance() + ".");
		}
		
		Account grandpa1 = new Account(100);
		Account father1 = new Account(50, grandpa1);
		Account myself1 = new Account(10, father1);
		System.out.println("====================================");
		System.out.println("Protection testing start: ");
		System.out.println("Myself's balance is $" + myself1.balance() + ".");
		System.out.println("Father's balance is $" + father1.balance() + ".");
		System.out.println("Father's balance is $" + grandpa1.balance() + ".");
		if (myself1.withdraw(16) == true) {
			System.out.println("Test for withdraw PASS!");
		} else {
			System.out.println("Test for withdraw FAIL!");
		}
		
		System.out.println("After withdrawing $16: ");
		
		if (myself1.balance() == 0) {
			System.out.println("Myself1's balance is $" + myself1.balance() + ".");
		} 
		
		if (father1.balance() == 44) {
			System.out.println("Father1's balance is $" + father1.balance() + ".");
		}
		
		if (grandpa1.balance() == 100) {
			System.out.println("Grandpa1's balance is $" + grandpa1.balance() + ".");
		}
		
		Account grandpa2 = new Account(100);
		Account father2 = new Account(50, grandpa2);
		Account myself2 = new Account(10, father2);
		System.out.println("====================================");
		System.out.println("Protection testing start: ");
		System.out.println("Myself2's balance is $" + myself2.balance() + ".");
		System.out.println("Father2's balance is $" + father2.balance() + ".");
		System.out.println("Grandpa2's balance is $" + grandpa2.balance() + ".");
		if (myself2.withdraw(65) == true) {
			System.out.println("Test for withdraw PASS!");
		} else {
			System.out.println("Test for withdraw FAIL!");
		}
		
		System.out.println("After withdrawing $65: ");
		
		if (myself2.balance() == 0) {
			System.out.println("Myself2's balance is $" + myself2.balance() + ".");
		} 
		
		if (father2.balance() == 0) {
			System.out.println("Father2's balance is $" + father2.balance() + ".");
		}
		
		if (grandpa2.balance() == 95) {
			System.out.println("Grandpa2's balance is $" + grandpa2.balance() + ".");
		}
		
//		Account grandpa3 = new Account(100);
//		Account father3 = new Account(50, grandpa3);
//		Account myself3 = new Account(10, father3);
//		myself3.withdraw(114);
//		System.out.println("Myself3's balance is $" + myself3.balance() + ".");
//		System.out.println("Father3's balance is $" + father3.balance() + ".");
//		System.out.println("Grandpa3's balance is $" + grandpa3.balance() + ".");
	}

}