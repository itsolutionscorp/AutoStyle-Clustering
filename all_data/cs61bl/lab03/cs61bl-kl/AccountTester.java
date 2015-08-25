public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(200));
		System.out.println(mike.balance());
		
		System.out.println(mike.withdraw(-100));
		System.out.println(mike.withdraw(100000));
		
		/** test merge method
		 * 
		 */
		Account anotherAcct= new Account(500);
		mike.merge(anotherAcct);
		System.out.println(mike.balance());
		System.out.println(anotherAcct.balance());
		
		/**
		 * Overdraw from an Account that has a parentAccount with enough to cover.
		 */
		
		Account father= new Account(500);
		Account irresponsible= new Account(100, father);
	
		
		irresponsible.withdraw(300);
		System.out.println("father balance after withdraw:" + father.balance() );
		System.out.println("irresponsible balance after withdraw:" + irresponsible.balance() );
	
		
		/**
		 * Overdraw from an Account that has a parentAccount without enough to cover.
		 */
		Account poormother= new Account(100);
		Account irresponsible2= new Account(100, poormother);
		irresponsible2.withdraw(500);
		
		
		System.out.println("irresponsible2 balance: " + irresponsible2.balance());
	
		System.out.println("poormother balance: " + poormother.balance());
		
		/**
		 * Overdraw from an Account that has a parentAccount that has a parent acwithout enough to cover.
		 */
		Account thirdlevel= new Account(1000);
		Account secondlevel= new Account(100, thirdlevel);
		Account firstlevel= new Account(0, secondlevel);
		firstlevel.withdraw(500);
		System.out.println("thirdlevel balance: " + thirdlevel.balance());
		System.out.println("secondlevel balance: " + secondlevel.balance());
		
		
		
		
	}

}