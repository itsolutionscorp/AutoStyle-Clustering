/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

	/**
	 * Initialize an account with the given balance.
	 */
	
	
	public Account(int balance) {
		this.myBalance = balance;
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
		boolean success = true;
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
			success = false;
			return success;
		} else if (this.myBalance < amount) {
			if (parent){
				amount -= myBalance; 
				myBalance = 0;
				parentAccount.withdraw(amount);
				System.out.println("Pulling funds from parent");;
				success = true;
				return success;
				}
			else{
			System.out.println("Insufficient funds");
			success = false;
			return success; }
		}
		 else {
			this.myBalance = this.myBalance - amount;
			success = true;
			return success;
		}
	}
	public void parentAcct(Account anotherAccount){
		parent = true;
		this.parentAccount = anotherAccount;
		
	}
		
	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}
	
	/**
	 * Merge account "anotherAcct" into this one by
	 * removing all the money from anotherAcct and
	 * adding that amount to this one.
	 */
	public void merge (Account anotherAcct) {
	    // TODO Put your own code here
		this.myBalance += anotherAcct.myBalance;
		anotherAcct.myBalance = 0;
	}

	// instance variables
	private int myBalance;
	private boolean parent = false;
	private Account parentAccount;

}
