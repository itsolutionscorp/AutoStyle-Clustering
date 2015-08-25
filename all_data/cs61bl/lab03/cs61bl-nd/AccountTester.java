public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		/*System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(-123));
		System.out.println(mike.withdraw(100000));
		System.out.println(mike.withdraw(10));*/
		/*Account dick;
		Account andy;
		dick = new Account(50);
		andy = new Account(100);
		andy.merge(dick);
		System.out.println(andy.balance());
		System.out.println(dick.balance());*/ 
		Account sam;
		Account dean;
		dean = new Account (500);
		sam = new Account (10,dean); 
		sam.withdraw(200); 
		System.out.println(sam.balance());
		System.out.println(dean.balance());
		
		
	}

}