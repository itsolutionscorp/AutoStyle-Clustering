/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

	/**
	 * Initialize an account with the given balance.
	 */
	public Account(int balance){
		this(balance,null);
	}
	
	public Account(int balance,Account parentaccount) {
		this.myBalance = balance;
		this.parentAccount = parentaccount;
	}
	
	
	/**
	 * Add the given amount to the account.
	 */
	public void deposit(int amount) {
		if (amount < 0) {
			System.out.println("Cannot deposit negative amount.");
		} else {
			this.myBalance = this.myBalance + amount;
		}
	}

	/**
	 * Subtract the given amount from the account if possible. If the amount
	 * would leave a negative balance, print an error message and leave the
	 * balance unchanged.
	 */
	public boolean withdraw(int amount) {
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
			return false;
			
		
		} else if (this.myBalance < amount) {
			if (this.parentAccount != null) {
				this.parentAccount.withdraw(amount - this.myBalance); 
				this.withdraw(this.myBalance);
				return true;
		} else {
			System.out.println("insuffuicient funds");
			return false;
		}
		}
			this.myBalance = this.myBalance - amount;
			return true;
		
	}
	
	/**
	 * Merge account "anotherAcct" into this one by
	 * removing all the money from anotherAcct and
	 * adding that amount to this one.
	 */
	public void merge (Account anotherAcct) {
	    // TODO Put your own code here
		this.deposit(anotherAcct.myBalance);
		anotherAcct.withdraw(anotherAcct.myBalance);		
		
	}

	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}

	// instance variables
	private int myBalance;
	private Account parentAccount;

}
