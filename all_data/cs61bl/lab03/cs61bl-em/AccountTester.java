public class AccountTester {

	public static void main(String[] args) {
		System.out.println("***new account, deposit, withdraw test***");
		Account mike;
		mike = new Account(1000);
		System.out.println("Mike has $" + mike.balance());
		mike.deposit(100);
		System.out.println("Mike deposits $100 and now has $" + mike.balance());
		mike.withdraw(200);
		System.out.println("Mike tries to withdraw $2000.");
		mike.withdraw(2000);
		System.out.println("Mike withdraws $200 and now has $" + mike.balance());
		mike.withdraw(-100);
		System.out.println();
		
		System.out.println("***merge test***");
		Account lucy = new Account(1400);
		System.out.println("Lucy has $" + lucy.balance());
		System.out.println("Merging Lucy and Mike accounts:");
		lucy.merge(mike);
		System.out.println("Lucy's new balance:" + lucy.balance());
		System.out.println("Mike's new balance:"+mike.balance());
		System.out.println();
		
		System.out.println("***withdraw tests***");
		Account frank = new Account(500);
		
		System.out.println("***case 1: parent is able to cover overdraft");
		System.out.println("Frank has $" + frank.balance());
		Account frankDad = new Account (300);
		frank.parentAccount = frankDad;
		System.out.println("Frank's Dad will cover his overdrafts. He has $" + frankDad.balance());
		Account frankDadDad = new Account (1000);
		frankDad.parentAccount = frankDadDad;
		System.out.println("Frank's grandpa will cover Frank's Dad's overdrafts. He has $" + frankDadDad.balance());
		System.out.println("Frank wants $1200.");
		frank.withdraw(1200);
		System.out.println("Frank now has $" + frank.balance());
		System.out.println("Frank's dad now has $" + frankDad.balance());
		System.out.println("Frank's grandpa now has $" + frankDadDad.balance());
		
		
		System.out.println("***case 2: parent is not able to cover overdraft");
		System.out.println("(reset values)");
		frank.deposit (500);
		frankDad.deposit (300);
		frankDadDad.deposit(400);
		System.out.println("Frank has $" + frank.balance());
		System.out.println("Frank's dad has $" + frankDad.balance());
		System.out.println("Frank's grandpa has $" + frankDadDad.balance());
		System.out.println("Frank wants $2000.");
		frank.withdraw(2000);
		System.out.println("Frank now has $" + frank.balance());
		System.out.println("Frank's dad now has $" + frankDad.balance());
		System.out.println("Frank's grandpa now has $" + frankDadDad.balance());
		
	
	}

}