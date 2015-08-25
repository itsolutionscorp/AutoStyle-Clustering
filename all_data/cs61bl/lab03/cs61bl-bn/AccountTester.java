public class AccountTester {

	public static void main(String[] args) {
//		Account mike;
//		mike = new Account(1000);
//		System.out.println(mike.balance());
//		mike.deposit(100);
//		System.out.println(mike.balance());
//		mike.withdraw(200);
//		System.out.println(mike.balance());
		
//		Account anotherAccount = new Account(500);
//		System.out.println("Another account balance before " + anotherAccount.balance());
//		mike.merge(anotherAccount);
//		System.out.println("Mike's Balance merged " + mike.balance());
//		System.out.println("Another account balance merged " + anotherAccount.balance());
//		
		Account mikesgrandparents = new Account (15000);
		Account mikesparents = new Account(10000, mikesgrandparents);
		Account mike2 = new Account(2000, mikesparents);
		System.out.println("Mike's account before " + mike2.balance());
		System.out.println("Parent's account before " + mikesparents.balance());
		System.out.println("Grandparent's account before " + mikesgrandparents.balance());
		mike2.withdraw(15000);
		System.out.println("Mike's account after " + mike2.balance());
		System.out.println("Parent's account after " + mikesparents.balance());
		System.out.println("Grandparent's account after " + mikesgrandparents.balance());
		
	}

}