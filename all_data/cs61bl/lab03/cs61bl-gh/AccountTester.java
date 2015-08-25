public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		if(mike.withdraw(2000)){
			System.out.println("true");
		}
		else{
			System.out.println("false");
		}
		mike.withdraw(100);
		mike.withdraw(-39);
		Account chris;
		chris = new Account(2000);
		mike.merge(chris);
		System.out.println(mike.balance());
		System.out.println(chris.balance());
		Account kathy;
		kathy = new Account(100, mike);
		kathy.withdraw(1000);
		kathy.withdraw(200);
		System.out.println(kathy.balance());
		System.out.println(mike.balance());
	}

}