public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		Account original;
		Account to_be_merged;
		Account mike_parent1;
		Account mike_parent2;
		Account mike_parent3;
		Account can_you_withdraw;
	    
		to_be_merged = new Account(1000);
		boolean successtrial = false;
		
		System.out.println("Merging test");
		System.out.println("merging $200 account on original account of $300 ");
		System.out.println("-------------------------------------------");
		original = new Account(300);
		to_be_merged = new Account(200);
		original.merge(to_be_merged);
		System.out.println("balance of added account: " + to_be_merged.balance());
		System.out.println("balance of original account: " + original.balance());
		System.out.println("-------------------------------------------");
		
		System.out.println("parentAccount Overdrafting test1");
		System.out.println("withdrawing $700 from $300,$200,$100,$150 account");
		System.out.println("-------------------------------------------");
		System.out.println("");
		mike_parent3 = new Account(150);
		mike_parent2 = new Account(100,mike_parent3);
		mike_parent1 = new Account(200,mike_parent2);
		mike = new Account(300,mike_parent1);
		mike.withdraw(700);
		System.out.println("Current balance: " + mike_parent3.balance() + " / $50 leftover from gross total of 750");
		System.out.println("-------------------------------------------");
		System.out.println("");
		System.out.println("parentAccount Overdrafting test2");
		System.out.println("withdrawing $700 from $300,$200,$100,$50 account");
		mike_parent3 = new Account(50);
		mike_parent2 = new Account(100,mike_parent3);
		mike_parent1 = new Account(200,mike_parent2);
		mike = new Account(300,mike_parent1);
		System.out.println("-------------------------------------------");
		mike.withdraw(700);
		System.out.println("-------------------------------------------");
		System.out.println("");
		
		System.out.println("can you withdraw more money than you have when you dont have overdraft account?");
		System.out.println("you have 50, trying to withdraw 100");
		System.out.println("-------------------------------------------");
		can_you_withdraw = new Account(50);
		successtrial = can_you_withdraw.withdraw(100);
		System.out.println("able to withdraw: " + successtrial);
		System.out.println("Current balance: " + can_you_withdraw.balance());
		System.out.println("-------------------------------------------");
		System.out.println("");
		System.out.println("deposit test");
		mike.deposit(100);
		System.out.println("Current balance: " + mike.balance() + " / Trying to deposit 100");
		System.out.println("-------------------------------------------");
		mike.deposit(100);
		System.out.println("Current balance: " + mike.balance());
		System.out.println("-------------------------------------------");
		
		
	    
	}

}