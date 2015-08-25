/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {
	Account parentAccount;
	int myFunds = 0; 
	Account curr;    
	/**
	 * Initialize an account with the given balance.
	 */
	public Account(int balance) {
		this.myBalance = balance;
		this.parentAccount = null; 
	}

	public Account(int balance, Account parentAccount) {
		this.myBalance = balance; 
		this.parentAccount = parentAccount; 
	}
	public void deposit(int amount) {
		if (amount < 0) {
			System.out.println("Cannot deposit negative amount.");
		} else {
			this.myBalance = this.myBalance + amount;
		}
	}
	/**
 * Merge account "anotherAcct" into this one by
 * removing all the money from anotherAcct and
 * adding that amount to this one.
 */
public void merge (Account anotherAcct) {
    this.myBalance = this.myBalance + anotherAcct.myBalance; 
    anotherAcct.myBalance = 0; 
}

	/**
	 * Subtract the given amount from the account if possible. If the amount
	 * would leave a negative balance, print an error message and leave the
	 * balance unchanged.
	 */
	public void withdraw(int amount) { 
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
		} else if (this.myBalance < amount) {
			if (this.withdraw_helper(amount)) {
				if (amount > this.myBalance) { 
			  this.parentAccount.withdraw(amount - this.myBalance);
			  this.myBalance = 0;
			  }
			 else if (this.myBalance > amount) {
			 	this.myBalance = this.myBalance - amount; 
			 	return; 
			 }}
			 else {
			 	System.out.println("Insufficient funds");
			 }

			
		} else {
			this.myBalance = this.myBalance - amount;
		}
	}
    public boolean withdraw_helper(int amount) {
    	curr = this; 
    	int myFunds = 0; 
    	while (curr != null) {
    		myFunds = myFunds + curr.myBalance; 
    		curr = curr.parentAccount; 
    	}
    	if (myFunds > amount) {
    		return true; 
    	}
    	else {
    		return false; 
    	}
    }
	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}

	// instance variables
	private int myBalance;

}
